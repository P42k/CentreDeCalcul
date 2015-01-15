package components;

import interfaces.CoreI;

import java.rmi.RemoteException;

import ports.CoreInboundPort;
import ressources.Request;
import fr.upmc.components.AbstractComponent;

public class Core extends AbstractComponent implements CoreI{
	// -------------------------------------------------------------------------
	// Constructors and instance variables
	// -------------------------------------------------------------------------
	
	/** fréquence du coeur */
	private double frequence;
	/** Booléen indiquant si le coeur est en train de traiter une requête */
	private boolean isFree;
	/** Booléen indiquant si une machine virtuelle tourne sur ce coeur */
	private boolean usedByVM;
	/** the output port used to send requests to the service of VirtualMachine  */
	private CoreInboundPort cp;
	/** Uri du coeur */
	private String uri;
	
	/**
	 * Crée un coeur grâce à sa fréquence et son uri. Le coeur est initialement libre.
	 * @param double
	 * @param String 
	 */
	public Core(double frequence, String uri)throws Exception{
		super(true,true);
		this.isFree = true;
		this.usedByVM=false;

		this.frequence = frequence;
		this.uri=uri;
		// Component management
		this.addOfferedInterface(CoreI.class) ;
		this.cp = new CoreInboundPort(uri,this);
		this.addPort(this.cp) ;
		this.cp.localPublishPort() ;
		
		
	}
	
	


	public double getFrequence() {
		return frequence;
	}
	
	public String getUri(){
		return this.uri;
	}


	public void setFrequence(double frequence) {
		assert frequence !=0.0;
		this.frequence = frequence;
	}

	public boolean isFree(){
		return this.isFree;
	}
	
	public boolean getUsedByVM(){
		return this.usedByVM;
	}
	

	/** Réalise le traitement d'une requête par le coeur
	 * @param Request
	 */
	public void requestTreatment(Request r) throws Exception {
		System.out.println("début traitement requete");
		this.isFree = false;
		Thread.sleep(r.getProcessingTime());
		this.isFree = true;
		System.out.println("fin traitement requete");
	}
	
	
	
}
