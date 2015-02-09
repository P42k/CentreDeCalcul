package interfaces;

import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;
import ressources.Request;

public interface RequestRepartitorI extends OfferedI, RequiredI {

	/**
	 * Répartit les requetes aux machines virtuelles
	 * @param requete
	 * @throws Exception
	 * @pre    requete !=null
	 */
	public void repartition(Request request) throws Exception;
}
