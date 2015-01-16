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
	protected String admissionControllerURI;
	protected boolean isFinish;
	private int maxRequest;
	
	public	RequestGenerator(double meanInterArrivalTime, double meanProcessingTime, int requestGeneratorId, String admissionControllerURI ) throws Exception
		{
			super(true, true) ;

			assert	meanInterArrivalTime > 0.0 && meanProcessingTime > 0.0 ;
			this.isFinish = false;
			this.counter = 0 ;
			this.meanInterArrivalTime = meanInterArrivalTime ;
			this.meanProcessingTime = meanProcessingTime ;
			this.rng = new RandomDataGenerator() ;
			this.rng.reSeed() ;
			this.nextRequestTaskFuture = null ;
			this.admissionControllerURI = admissionControllerURI;
			//créer l'id du generateur
			this.requestGeneratorId = requestGeneratorId;
			//on crée le port pour se lier au AdmissionController
			acop =  new RequestGeneratorACOutBoundPort(this);
			addPort(acop);
			acop.localPublishPort();
			//le RG a besoin de RequestRepartitorI pour utiliser le répartiteur de requetes
			this.addRequiredInterface(RequestRepartitorI.class);
			System.out.println("Générateur de requête créé.");
		}
	
	@Override
	public void	start() throws ComponentStartException{
		super.start();
		try {
			//connexion avec le controleur d'admission
			acop.doConnection(admissionControllerURI, AdmissionControllerConnector.class.getCanonicalName());
			//on récupère l'uri de l'application pour se connecter au répartiteur correspondant
			applicationURI = acop.accept(requestGeneratorId);
			System.out.println("Connexion du générateur de requêtes au contrôleur d'admission"+admissionControllerURI+ "effectuée.");
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		try {
			rrop = new RequestGeneratorRROutBoundPort(applicationURI, this);
			addPort(rrop);
			rrop.localPublishPort();
			System.out.println("Connexion du générateur de requêtes à l'application effectuée.");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		try {
			//Connexion avec le répartiteur de requete
			rrop.doConnection(applicationURI, RequestRepartitorConnector.class.getCanonicalName());
			System.out.println("Connexion du générateur de requêtesau répartiteur de requêtes effectuée.");

		} catch (Exception e1) {
			System.err.println("RequestGenerator : Connexion avec le RequestRepartitor impossible !");
			e1.printStackTrace();
		}

		final RequestGenerator generateur = this;
		this.scheduleTask(
				new ComponentTask() {
					@Override
					public void run() {
						try {
							generateur.generateNextRequest();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				},
				1000, TimeUnit.MILLISECONDS);
	}

	
	
	@Override
	public void	shutdown() throws ComponentShutdownException{
		if (this.nextRequestTaskFuture != null &&
				!(this.nextRequestTaskFuture.isCancelled() || this.nextRequestTaskFuture.isDone())
				) {
			this.nextRequestTaskFuture.cancel(true) ;
		}
		super.shutdown();
	}
	
	public void			generateNextRequest() throws Exception
	{
		long processingTime =
					(long) this.rng.nextExponential(this.meanProcessingTime) ;
		if(!isFinish){
		rrop.repartition(new Request(this.counter++, processingTime,applicationURI)) ;
			if (counter >= maxRequest){
				stop();
				isFinish = true;
			}
		}
		final RequestGenerator cg = this ;
		long interArrivalDelay =
				(long) this.rng.nextExponential(this.meanInterArrivalTime) ;
		System.out.println(
			"Scheduling request at " +
					TimeProcessing.toString(System.currentTimeMillis() +
														interArrivalDelay) +
					" with processing time " + processingTime) ;
		this.nextRequestTaskFuture =
			this.scheduleTask(
				new ComponentTask() {
					@Override
					public void run() {
						try {
							cg.generateNextRequest() ;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				},
				interArrivalDelay, TimeUnit.MILLISECONDS) ;
	}
	
	//appeler le controleur d'admission pour arreter l'application dans 
	private void stop() {
		System.out.println("Le RequestGenerator" + requestGeneratorId + "demande la terminaison de l'application");
		try {
			acop.finish(applicationURI);
			rrop.doDisconnection();
			acop.doDisconnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
