package ports;

import ressources.Request;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
import interfaces.VirtualMachineI;

public class RequestRepartitorOutboundPort extends AbstractOutboundPort implements VirtualMachineI {

	public RequestRepartitorOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, VirtualMachineI.class, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getIdApplication() {
		
		return ((VirtualMachineI)super.connector).getIdApplication();
	}

	@Override
	public void setIdApplication(String idApplication) {
		((VirtualMachineI)super.connector).setIdApplication(idApplication);
	}

	@Override
	public void traitementRequete(Request requete) throws Exception {
		((VirtualMachineI)super.connector).traitementRequete(requete);
	}

}
