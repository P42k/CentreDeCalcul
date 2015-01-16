package cvm;

import components.CalculCenter;
import components.RequestGenerator;
import fr.upmc.components.cvm.AbstractCVM;

public class CVM extends AbstractCVM{
	RequestGenerator rg;
	CalculCenter cc;
	
	
	public void deploy() throws Exception{
		cc = new CalculCenter();
		rg = new RequestGenerator(1000.0, 800.0, 0, cc.getAdmissionControllerURI());
		
		super.deploy();
	}
	
	public void start() throws Exception{
		super.start();
		cc.start();
		rg.start();
	}
	
	
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
