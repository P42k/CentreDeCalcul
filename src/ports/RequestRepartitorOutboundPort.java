package ports;

import java.rmi.RemoteException;

import ressources.Request;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
import interfaces.VirtualMachineI;
/**
 * Outbound port du r�partiteur de qu�tes
 * @author Argonautes
 *
 */
public class RequestRepartitorOutboundPort extends AbstractOutboundPort implements VirtualMachineI {

	public RequestRepartitorOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, VirtualMachineI.class, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getIdApplication() throws RemoteException {
		
		return ((VirtualMachineI)this.connector).getIdApplication();
	}

	@Override
	public void setIdApplication(String idApplication) throws RemoteException {
		((VirtualMachineI)this.connector).setIdApplication(idApplication);
	}

	@Override
	public void traitementRequete(Request requete) throws Exception {
		((VirtualMachineI)this.connector).traitementRequete(requete);
	}

}
