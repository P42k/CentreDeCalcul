package interfaces;

import ressources.Request;
import fr.upmc.components.interfaces.OfferedI;

public interface VirtualMachineI extends OfferedI{
	/**
	 * Traitement de la requ�te par la MV
	 * @params Request
	 */
     public void traitementRequete(Request requete)throws Exception;
}
 