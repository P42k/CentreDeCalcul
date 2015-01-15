package ports;

import interfaces.VirtualMachineI;
import ressources.Request;

import components.VirtualMachine;

import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

/** Inbound port du composant machine virtuelle */
public class VirtualMachineInboundPort extends AbstractInboundPort implements VirtualMachineI {

	private static final long serialVersionUID = 1L;

	/** Crée le port avec une certaine uri et un certain owner */
	public VirtualMachineInboundPort(String uri, ComponentI owner) throws Exception{
		super(uri, VirtualMachineI.class, owner);
	}
	
	/**
	 * Appelle la méthode de traitement de requête du composant Machine Virtuelle
	 */
	public void traitementRequete(Request requete) throws Exception {
		final VirtualMachine vm = (VirtualMachine) this.owner ;
		vm.traitementRequete(requete);

	}
		
}
