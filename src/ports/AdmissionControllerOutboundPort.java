package ports;

import java.rmi.RemoteException;
import java.util.ArrayList;

import interfaces.ComputerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
/**
 * Outbound port de l'admission controller
 * @author Argonautes
 *
 */
public class AdmissionControllerOutboundPort extends AbstractOutboundPort implements ComputerI{

	public AdmissionControllerOutboundPort(String uri,ComponentI owner) throws Exception {
		super(uri, ComputerI.class, owner);
	}

	@Override
	public void setCoreAvailability(String uriCore, boolean b) throws RemoteException {
		((ComputerI)this.connector).setCoreAvailability(uriCore,b);
		
	}

	@Override
	public ArrayList<String> getAvailableCores() throws RemoteException {
		return ((ComputerI)this.connector).getAvailableCores();
	}

	@Override
	public String getId() throws RemoteException{
		return ((ComputerI)this.connector).getId();
	}
}
