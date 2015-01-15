package connectors;

import interfaces.CoreI;
import interfaces.VirtualMachineI;
import ressources.Request;
import fr.upmc.components.connectors.AbstractConnector;

/**
 * La classe <code>VMCoreConnector</code> impl�mente un connecteur entre
 * les interfaces <code>VirtualMachineI</code> et <code>CoreI</code>.
 */
public class CoreConnector extends AbstractConnector implements VirtualMachineI{

	/**
	 * Impl�mente l'interface recquise  en appelant le inbound port avec la m�thode offerte traitementRequete du Coeur
	 * @param Request
	 */
	public void traitementRequete(Request requete) throws Exception{
		((CoreI)this.offering).requestTreatment(requete);;
	}

	/**
	 * Impl�mente l'interface recquise  en appelant le inbound port avec la m�thode offerte isFree du Coeur
	 */
	public boolean isFree() throws Exception{
		return ((CoreI)this.offering).isFree();
	}
}
