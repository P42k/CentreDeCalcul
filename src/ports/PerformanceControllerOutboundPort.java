package ports;

import java.rmi.RemoteException;

import ressources.Request;
import interfaces.CoreI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

public class PerformanceControllerOutboundPort extends AbstractOutboundPort implements CoreI{

	public PerformanceControllerOutboundPort(String uri,
			 ComponentI owner) throws Exception {
		super(uri, CoreI.class, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void requestTreatment(Request r) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFrequence(double frequence) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getFrequence() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
