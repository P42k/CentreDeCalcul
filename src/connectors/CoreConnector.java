package connectors;

import interfaces.CoreI;
import interfaces.VirtualMachineI;
import ressources.Request;
import fr.upmc.components.connectors.AbstractConnector;

/**
 * La classe <code>VMCoreConnector</code> impl�mente un connecteur entre
 * les interfaces <code>VirtualMachineI</code> et <code>CoreI</code>.
 */
public class CoreConnector extends AbstractConnector implements CoreI{

	/**
	 * Impl�mente l'interface recquise  en appelant le inbound port avec la m�thode offerte traitementRequete du Coeur
	 * @param Request
	 */
	public void requestTreatment(Request requete) throws Exception{
		((CoreI)this.offering).requestTreatment(requete);;
	}

	@Override
	public void setFrequence(double frequence) {
		((CoreI)this.offering).setFrequence(frequence);
	
	}

	@Override
	public double getFrequence() {
		return ((CoreI)this.offering).getFrequence();
	}
}
