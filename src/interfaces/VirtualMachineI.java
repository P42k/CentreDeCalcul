package interfaces;

import ressources.Request;
import fr.upmc.components.interfaces.OfferedI;

public interface VirtualMachineI extends OfferedI{
	/**
	 * Traitement de la requête par la MV
	 * @params Request
	 */
     public void traitementRequete(Request requete)throws Exception;
     
     /**
 	 * Récupère l'id de l'application à qui a été allouée la machine virtuelle
 	 * @return uri de l'applicaton à qui a été allouée la MV
 	 */
 	public String getIdApplication();
 	
 	/**
 	 * Met à jour l'id de l'appli à qui est allouée la MV
 	 * @param idApplication uri de l'application
 	 */
 	public void setIdApplication(String idApplication);
}
 