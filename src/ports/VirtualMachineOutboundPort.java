package ports;

import java.rmi.RemoteException;

import interfaces.CoreI;
import ressources.Request;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

/** outbound port du composant machine virtuelle */
public class VirtualMachineOutboundPort extends AbstractOutboundPort implements CoreI{

	public VirtualMachineOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, CoreI.class, owner) ;
	}
	
	/**
	 * Effectue le traitement de la requête en appelant le coeur via 
	 * le connecteur qui implémente l'interface recquise.
	 * @params Request
	 */
	public void	requestTreatment(Request r) throws Exception
	{
		((CoreI)this.connector).requestTreatment(r);
	}
	

	@Override
	public void setFrequence(double frequence) throws RemoteException {
		((CoreI)this.connector).setFrequence(frequence);;
	}

	@Override
	public double getFrequence() throws RemoteException {
		return ((CoreI)this.connector).getFrequence();
	}
		
}
