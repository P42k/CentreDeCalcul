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
	
	/** fr�quence du coeur */
	private double frequence;
	/** Bool�en indiquant si une machine virtuelle tourne sur ce coeur */
	private boolean usedByVM;
	/** the output port used to send requests to the service of VirtualMachine  */
	private CoreInboundPort cp;
	/** Uri du coeur */
	private String uri;
	
	/**
	 * Cr�e un coeur gr�ce � sa fr�quence et son uri. Le coeur est initialement libre.
	 * @param frequence double fr�quence du coeur
	 * @param String uri du coeur
	 * @throws Exception
	 */
	public Core(double frequence, String uri)throws Exception{
		super(true,true);
		this.usedByVM=false;

		this.frequence = frequence;
		this.uri=uri;
		// Component management
		this.addOfferedInterface(CoreI.class) ;
		this.cp = new CoreInboundPort(uri,this);
		this.addPort(this.cp) ;
		this.cp.localPublishPort() ;
		System.out.println("Le coeur "+ uri + " a �t� cr��.");
		
		
	}
	
	


	/** R�up�re la fr�quence du coeur
	 * @return frequence double
	 *  */
	public double getFrequence() {
		return frequence;
	}
	
	/** R�cup�re l'URI du coeur
	 * @return String
	 * */
	public String getUri(){
		return this.uri;
	}

	/** Met � jour la fr�quence du coeur 
	 * @param frequence double
	 */
	public void setFrequence(double frequence) {
		assert frequence !=0.0;
		this.frequence = frequence;
	}
	
	/** Retourne la valeur de l'attribut usedByMV: si le coeur est allou� � une machine virtuelle ou non
	 * @return boolean
	 */
	public boolean getUsedByVM(){
		return this.usedByVM;
	}
	
	/** Met � jour la valeur de l'occupation du coeur par une machine virtuelle
	 * @param b boolean
	 */
	public void setUsedByVM(boolean b){
		this.usedByVM=b;
	}
	

	/** R�alise le traitement d'une requ�te par le coeur suivant la fr�quence du coeur
	 * @param r Request requ�te � traiter
	 * @throws Exception
	 */
	public void requestTreatment(Request r) throws Exception {
		System.out.println("d�but traitement requete de " + r.getUri());
		Thread.sleep((long)(r.getProcessingTime()/this.frequence));
		System.out.println("fin traitement requete de " + r.getUri());
	}
	
	
	
}
