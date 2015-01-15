package ports;

import java.util.ArrayList;

import interfaces.ComputerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

public class AdmissionControllerOutboundPort extends AbstractOutboundPort implements ComputerI{

	public AdmissionControllerOutboundPort(String uri,ComponentI owner) throws Exception {
		super(uri, ComputerI.class, owner);
	}

	@Override
	public void setAvailableCore(String uriCore, boolean b) {
		((ComputerI)super.connector).setAvailableCore(uriCore,b);
		
	}

	@Override
	public ArrayList<String> getAvailableCores() {
		return ((ComputerI)super.connector).getAvailableCores();
	}

}
