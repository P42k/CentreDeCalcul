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
	}

	@Override
	public void requestTreatment(Request r) throws Exception {
		((CoreI)this.connector).requestTreatment(r);
		
	}

	@Override
	public void setFrequence(double frequence) throws RemoteException {
		((CoreI)this.connector).setFrequence(frequence);
		
	}

	@Override
	public double getFrequence() throws RemoteException {
		return ((CoreI)this.connector).getFrequence();
	}

}
