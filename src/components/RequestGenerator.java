package components;

import interfaces.RequestRepartitorI;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.random.RandomDataGenerator;

import connectors.AdmissionControllerConnector;
import connectors.RequestRepartitorConnector;
import connectors.VirtualMachineConnector;
import ports.RequestGeneratorACOutBoundPort;
import ports.RequestGeneratorRROutBoundPort;
import ressources.Request;
import ressources.TimeProcessing;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentShutdownException;
import fr.upmc.components.exceptions.ComponentStartException;

/**
 * Composant g�n�rateur de requ�tes 
 * @author Argonautes
 *
 */
public class RequestGenerator extends AbstractComponent {
	
	protected RandomDataGenerator rng;
	protected int counter;
	protected double meanInterArrivalTime;
	protected double meanProcessingTime;
	protected RequestGeneratorRROutBoundPort rrop;
	protected RequestGeneratorACOutBoundPort acop;
	protected Future<?> nextRequestTaskFuture;
	protected String applicationURI;
	protected int requestGeneratorId;
	protected String admissionControllerInboundURI;
	protected String requestGeneratorOutboundURI;
	protected boolean isFinish;
	private int maxRequest;
	/** 
	 * Cr�e un g�n�rateur de requ�tes
	 * 
	 * @param meanInterArrivalTime temps moyen d'arriv�e 
	 * @param meanProcessingTime temps moyen de process
	 * @param requestGeneratorId id du g�n�rateur
	 * @param admissionControllerInboundURI string uri du inbound port de l'admission controller
	 * @param requestGeneratorOutboundURI string  uri de l'outbound port du g�n�rateur
	 * @throws Exception
	 */
	public RequestGenerator(double meanInterArrivalTime,
			double meanProcessingTime, int requestGeneratorId,
			String admissionControllerInboundURI,
			String requestGeneratorOutboundURI) throws Exception {
		super(true, true);
		this.toggleTracing();
		assert meanInterArrivalTime > 0.0 && meanProcessingTime > 0.0;
		this.isFinish = false;
		this.counter = 0;
		this.meanInterArrivalTime = meanInterArrivalTime;
		this.meanProcessingTime = meanProcessingTime;
		this.rng = new RandomDataGenerator();
		this.rng.reSeed();
		this.nextRequestTaskFuture = null;
		this.admissionControllerInboundURI = admissionControllerInboundURI;
		this.requestGeneratorOutboundURI = requestGeneratorOutboundURI;
		// cr�er l'id du generateur
		this.requestGeneratorId = requestGeneratorId;
		// on cr�e le port pour se lier au AdmissionController
		acop = new RequestGeneratorACOutBoundPort(this);
		addPort(acop);
		acop.localPublishPort();
		// le RG a besoin de RequestRepartitorI pour utiliser le r�partiteur de
		// requetes
		this.addRequiredInterface(RequestRepartitorI.class);
		System.out.println("G�n�rateur de requ�te cr��.");
	}

	/**
	 * M�thode start permettant la connexion avec le contr�leur d'admission et le r�partiteur de requ�te
	 */
	@Override
	public void start() throws ComponentStartException {
		super.start();
		try {
			// connexion avec le controleur d'admission
			acop.doConnection(admissionControllerInboundURI,
					"connectors.AdmissionControllerConnector");
			// on r�cup�re l'uri de l'application pour se connecter au
			// r�partiteur correspondant
			applicationURI = acop.accept(requestGeneratorId);
			System.out
					.println("Connexion du g�n�rateur de requ�tes au contr�leur d'admission"
							+ admissionControllerInboundURI + "effectu�e.");
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		try {
			rrop = new RequestGeneratorRROutBoundPort(applicationURI, this);
			addPort(rrop);
			rrop.localPublishPort();
			System.out
					.println("Connexion du g�n�rateur de requ�tes � l'application effectu�e.");
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			// Connexion avec le r�partiteur de requete
			rrop.doConnection(applicationURI,
					"connectors.RequestRepartitorConnector");
			System.out
					.println("Connexion du g�n�rateur de requ�tes au r�partiteur de requ�tes effectu�e.");

		} catch (Exception e1) {
			System.err
					.println("RequestGenerator : Connexion avec le RequestRepartitor impossible !");
			e1.printStackTrace();
		}

		final RequestGenerator generateur = this;
		this.scheduleTask(new ComponentTask() {
			@Override
			public void run() {
				try {
					generateur.generateNextRequest();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 1000, TimeUnit.MILLISECONDS);
	}

	/**
	 * M�thode de shutdown du composant g�n�rateur de requ�tes
	 */
	@Override
	public void shutdown() throws ComponentShutdownException {
		if (this.nextRequestTaskFuture != null
				&& !(this.nextRequestTaskFuture.isCancelled() || this.nextRequestTaskFuture
						.isDone())) {
			this.nextRequestTaskFuture.cancel(true);
		}
		super.shutdown();
	}

	/**
	 * Permet de g�n�rer la prochaine requ�te, fait appel � la r�partition du r�partiteur de requ�tes li�
	 * @throws Exception
	 */
	public void generateNextRequest() throws Exception {
		// System.out.println("G�n�ration d'une nouvelle requ�te!");
		long processingTime = (long) this.rng
				.nextExponential(this.meanProcessingTime);
		if (!isFinish) {
			// System.out.println("Entr�e dans le if?");
			rrop.repartition(new Request(this.counter++, processingTime, applicationURI));
			if (counter >= maxRequest) {
				stop();
				isFinish = true;
			}
		}
		final RequestGenerator cg = this;
		long interArrivalDelay = (long) this.rng
				.nextExponential(this.meanInterArrivalTime);
		System.out.println("Scheduling request at "
				+ TimeProcessing.toString(System.currentTimeMillis()
						+ interArrivalDelay) + " with processing time "
				+ processingTime);
		this.nextRequestTaskFuture = this.scheduleTask(new ComponentTask() {
			@Override
			public void run() {
				try {
					cg.generateNextRequest();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, interArrivalDelay, TimeUnit.MILLISECONDS);
	}

	// appeler le controleur d'admission pour arreter l'application dans
	private void stop() {
		System.out.println("Le RequestGenerator" + requestGeneratorId
				+ "demande la terminaison de l'application");
		try {
			acop.finish(applicationURI);
			rrop.doDisconnection();
			acop.doDisconnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
