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
	
	private int id;
	private double frequence;
	private boolean isFree;
	static int coeurId=0;
	/** the output port used to send requests to the service of VirtualMachine  */
	private CoreInboundPort cp;
	
	/*
	 * create a Core component
	 * 
	 * ****** Contract *******
	 * pre 		outboundportURI not null
	 * post 	true
	 */
	public Core(double frequence, String outboundPortURI )throws Exception{
		super(true,true);
		this.isFree = true;
		assert	outboundPortURI != null ;
		
		this.id = ++coeurId;
		this.frequence = frequence;
		
		// Component management
		this.addRequiredInterface(CoreI.class) ;
		this.cp = new CoreInboundPort(outboundPortURI,this);
		this.addPort(this.cp) ;
		this.cp.localPublishPort() ;
		
		
	}
	
	


	public double getFrequence() {
		return frequence;
	}


	public void setFrequence(double frequence) {
		assert frequence !=0.0;
		this.frequence = frequence;
	}


	public  int getId() {
		return this.id;
	}


	public boolean isFree(){
		return this.isFree;
	}

	public void requestTreatment(Request r) throws Exception {
		// TODO Auto-generated method stub
		assert r !=null;
		System.out.println("début traitement requete");
		this.isFree = false;
		Thread.sleep(r.getProcessingTime());
		this.isFree = true;
		System.out.println("fin traitement requete");
	}
	
	
	
}
