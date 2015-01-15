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
	 * @param frequence double fréquence du coeur
	 * @param String uri du coeur
	 * @throws Exception
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
	
	


	/** Réupère la fréquence du coeur
	 * @return frequence double
	 *  */
	public double getFrequence() {
		return frequence;
	}
	
	/** Récupère l'URI du coeur
	 * @return String
	 * */
	public String getUri(){
		return this.uri;
	}

	/** Met à jour la fréquence du coeur 
	 * @param frequence double
	 */
	public void setFrequence(double frequence) {
		assert frequence !=0.0;
		this.frequence = frequence;
	}

	/** Retourne la valeur de l'attribut isFree: si le coeur est occupé par une requête ou non
	 * @return boolean
	 */
	public boolean isFree(){
		return this.isFree;
	}
	
	/** Retourne la valeur de l'attribut usedByMV: si le coeur est alloué à une machine virtuelle ou non
	 * @return boolean
	 */
	public boolean getUsedByVM(){
		return this.usedByVM;
	}
	
	/** Met à jour la valeur de l'occupation du coeur par une machine virtuelle
	 * @param b boolean
	 */
	public void setUsedByVM(boolean b){
		this.usedByVM=b;
	}
	

	/** Réalise le traitement d'une requête par le coeur suivant la fréquence du coeur
	 * @param r Request requête à traiter
	 * @throws Exception
	 */
	public void requestTreatment(Request r) throws Exception {
		System.out.println("début traitement requete");
		this.isFree = false;
		Thread.sleep((long)(r.getProcessingTime()/this.frequence));
		this.isFree = true;
		System.out.println("fin traitement requete");
	}
	
	
	
}
