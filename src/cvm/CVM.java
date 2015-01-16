package cvm;

import components.CalculCenter;
import components.RequestGenerator;
import fr.upmc.components.cvm.AbstractCVM;
/**
 * <p><strong>D�scription</strong></p>
 * Le CVM  (component virtual machines )permets de cr�er les composants, de les initialiser et de les lier entre eux 
 * afin de d�marrer l'execution de l'application.
 * 
 * <p><strong>Invariant</strong></p>
 *
 */
public class CVM extends AbstractCVM{
	
	RequestGenerator rg;
	CalculCenter cc;
	
	/**
	 * instancie, publie et lie les composants entre eux
	 *
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true				// no more preconditions.
	 * post	deploymentDone
	 * </pre>
	 * @throws Exception 
	 */
	public void deploy() throws Exception{
		cc = new CalculCenter();
		rg = new RequestGenerator(1000.0, 800.0, 0, cc.getAdmissionControllerURI());
		
		super.deploy();
	}
	/**
	 * d�marre l'execution des composants
	 * @throws Exception 
	 */
	public void start() throws Exception{
		super.start();
		cc.start();
		rg.start();
	}
	/**
	 * Le point d'entr�e de notre programme
	 * @param args
	 */
	
	public static void		main(String[] args)
	{
		CVM a = new CVM() ;
		try {
			a.deploy() ;
			System.out.println("starting...") ;
			a.start() ;
			Thread.sleep(15000L) ;
			System.out.println("shutting down...") ;
			System.out.print("\007"); System.out.flush();
			a.shutdown() ;
			System.out.println("ending...") ;
			System.exit(0) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
