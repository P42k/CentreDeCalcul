package connectors;

import interfaces.VirtualMachineI;
import ressources.Request;
import fr.upmc.components.connectors.AbstractConnector;

public class RequestRepartitorConnector extends AbstractConnector implements VirtualMachineI{

	@Override
	public void traitementRequete(Request requete) throws Exception {
		((VirtualMachineI)offering).traitementRequete(requete);
	}

	@Override
	public String getIdApplication() {
		// TODO Auto-generated method stub
		return ((VirtualMachineI)offering).getIdApplication();
	}

	@Override
	public void setIdApplication(String idApplication) {
		((VirtualMachineI)offering).setIdApplication(idApplication);
	}
}
