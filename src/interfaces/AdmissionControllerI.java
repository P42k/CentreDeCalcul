package interfaces;

import fr.upmc.components.interfaces.OfferedI;

public interface AdmissionControllerI extends OfferedI{
	
	/***
	 * Demande le démarrage d'une application
	 * @param RequestGeneratorId
	 * @return
	 * @throws Exception 
	 */
	String accept(int RequestGeneratorId) throws Exception;
	
	/***
	 * Va arreter l'application
	 * @param RequestGeneratorId
	 */
	void finish(String applicationURI) throws Exception;
}
