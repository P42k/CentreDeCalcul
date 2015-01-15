package ports;

import interfaces.CoreI;
import ressources.Request;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

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
	
	/**
	 * Vérifie si le coeur est occupé ou non
	 * @return boolean
	 */
	public boolean isFree()
	{
		return ((CoreI)this.connector).isFree();
	}


	@Override
	public void setFrequence(double frequence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getFrequence() {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
