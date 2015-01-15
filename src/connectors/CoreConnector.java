package connectors;

import interfaces.CoreI;
import interfaces.VirtualMachineI;
import ressources.Request;
import fr.upmc.components.connectors.AbstractConnector;

/**
 * La classe <code>VMCoreConnector</code> implémente un connecteur entre
 * les interfaces <code>VirtualMachineI</code> et <code>CoreI</code>.
 */
public class CoreConnector extends AbstractConnector implements VirtualMachineI{

	/**
	 * Implémente l'interface recquise  en appelant le inbound port avec la méthode offerte traitementRequete du Coeur
	 * @param Request
	 */
	public void traitementRequete(Request requete) throws Exception{
		((CoreI)this.offering).requestTreatment(requete);;
	}

	/**
	 * Implémente l'interface recquise  en appelant le inbound port avec la méthode offerte isFree du Coeur
	 */
	public boolean isFree() throws Exception{
		return ((CoreI)this.offering).isFree();
	}
}
