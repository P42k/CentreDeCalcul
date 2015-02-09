package connectors;

import java.rmi.RemoteException;

import interfaces.CoreI;
import interfaces.VirtualMachineI;
import ressources.Request;
import fr.upmc.components.connectors.AbstractConnector;

/**
 * La classe <code>VMCoreConnector</code> implémente un connecteur entre
 * les interfaces <code>VirtualMachineI</code> et <code>CoreI</code>.
 * @author Argonautes
 */

public class CoreConnector extends AbstractConnector implements CoreI{

	/**
	 * Implémente l'interface recquise  en appelant le inbound port avec la méthode offerte traitementRequete du Coeur
	 * @param Request
	 */
	public void requestTreatment(Request requete) throws Exception{
		((CoreI)this.offering).requestTreatment(requete);;
	}

	@Override
	public void setFrequence(double frequence) throws RemoteException{
		((CoreI)this.offering).setFrequence(frequence);
	
	}

	@Override
	public double getFrequence() throws RemoteException{
		return ((CoreI)this.offering).getFrequence();
	}
}
