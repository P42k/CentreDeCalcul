package ports;

import interfaces.VirtualMachineI;
import ressources.Request;
import components.VirtualMachine;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

/** Inbound port du composant machine virtuelle */
public class VirtualMachineInboundPort extends AbstractInboundPort implements VirtualMachineI {

	private static final long serialVersionUID = 1L;

	/** Cr�e le port avec une certaine uri et un certain owner */
	public VirtualMachineInboundPort(String uri, ComponentI owner) throws Exception{
		super(uri, VirtualMachineI.class, owner);
	}
	
	/**
	 * Appelle la m�thode de traitement de requ�te du composant Machine Virtuelle
	 */
	public void traitementRequete(Request requete) throws Exception {
		final VirtualMachine vm = (VirtualMachine) this.owner ;
		vm.traitementRequete(requete);

	}

	/** Appelle la m�thode de r�cup�ration de l'uri de l'application du composant Machine Virtuelle */
	@Override
	public String getIdApplication() {
		final VirtualMachine vm = (VirtualMachine) this.owner ;
		return vm.getIdApplication();
	}

	/** Appelle la m�thode setIdApplication du composant Machine Virtuelle */
	@Override
	public void setIdApplication(String idApplication) {
		final VirtualMachine vm = (VirtualMachine) this.owner ;
		vm.setIdApplication(idApplication);
	}
		
}
