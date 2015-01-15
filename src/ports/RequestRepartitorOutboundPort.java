package ports;

import interfaces.VirtualMachineI;
import ressources.Request;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

public class RequestRepartitorOutboundPort extends AbstractOutboundPort implements VirtualMachineI{

	public RequestRepartitorOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, VirtualMachineI.class, owner);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void traitementRequete(Request requete) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
