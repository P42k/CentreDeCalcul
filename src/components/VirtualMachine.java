package components;

import interfaces.CoreI;
import interfaces.VirtualMachineI;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ports.VirtualMachineInboundPort;
import ports.VirtualMachineOutboundPort;
import ressources.Request;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentShutdownException;
import fr.upmc.components.exceptions.ComponentStartException;
import fr.upmc.components.ports.PortI;

/** VirtualMachine implante un composant qui simule une machine virtuelle */


//TODO : la file d'attente, sa gestion et le shutdown. A voir pour les d�tails dans ServiceProvider.java de 
// SingleServerEventBasedSimulation

public class VirtualMachine extends AbstractComponent {
	/** Liste des ports de la VM connect� aux coeurs						*/
	private ArrayList<VirtualMachineOutboundPort> listeCoeurs;
	/** Identifiant de l'application en cours sur la VM 					*/
	private int idApplication;
	/**Liste des URIs des coeurs avec lesquels la MV va communiquer			*/
	private ArrayList<String> listeURICoeur;
	/** La file des requ�tes en attente de traitement 						*/
	protected BlockingQueue<Request> requestsQueue ;




	/**
	 * Cr�e une machine virtuelle
	 * @param listeURICoeur
	 * @param idApp
	 * @param MVPortURI
	 * @param isDistributed
	 * @throws Exception
	 */
	public VirtualMachine(ArrayList<String> listeURICoeur, int idApp, String MVPortURI,
			boolean isDistributed) throws Exception{
		//ajout de la m�thode offeredInterface, cr�ation port qui fournit la machine virtuelle, addport
		//cr�e des ports qui requiert coeur, de les ajouter au composant puis � listeCoeurs
		super(true);
		this.idApplication=idApp;
		this.listeURICoeur=listeURICoeur;
		this.requestsQueue = new LinkedBlockingQueue<Request>() ;

		this.addOfferedInterface(VirtualMachineI.class);
		PortI p = new VirtualMachineInboundPort(MVPortURI, this);
		this.addPort(p);
		p.localPublishPort();
		this.addRequiredInterface(CoreI.class);
		VirtualMachineOutboundPort q;
		for(int i=0; i<this.listeURICoeur.size();i++){
			//cr�er les ports pour communiquer avec les coeurs et les ajouter au composants ainsi qu'� la liste des ports Coeurs
			q= new VirtualMachineOutboundPort(listeURICoeur.get(i), this);
			this.addPort(q);
			q.localPublishPort();
			this.listeCoeurs.add(q);
		}

	}

	/**
	 * rajoute la requ�te dans la file d'attente et traite la requ�te en faisant appel � un coeur libre
	 * @param requete
	 * @throws Exception
	 */
	public void traitementRequete(Request requete) throws Exception{
		// ajouter dans la file
		this.requestsQueue.add(requete);
		System.out.println("Ajout de la requ�te " + requete + "� la file d'attente.");
		
		// recherche un coeur libre dans la liste des coeurs reli�s
		for(int idCoeur=0;idCoeur<listeCoeurs.size();idCoeur++){
			if(listeCoeurs.get(idCoeur).isFree()){
				listeCoeurs.get(idCoeur).requestTreatment(this.requestsQueue.remove());
				break;
			}
		}
	}


	/**
	 * Sert � effectuer la connexion des ports recquis 
	 * au d�marrage du centre de calcul
	 */
	@Override
	public void start() throws ComponentStartException{
		super.start();
		for (int i=0; i<listeCoeurs.size();i++){
			try{
				this.listeCoeurs.get(i).doConnection(listeURICoeur.get(i),"connectors.VMCoreConnector");
			}catch(Exception e){
				System.err.println("Connection impossible avec le coeur " + listeURICoeur.get(i));
			}

		}
	}

	/**
	 * Arr�te la Machine Virtuelle
	 */
	public void shutdown() throws ComponentShutdownException{
		// voir ce qu'on fait l�
		super.shutdown();
	}


	
}
