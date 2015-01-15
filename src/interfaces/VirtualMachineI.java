package interfaces;

import ressources.Request;
import fr.upmc.components.interfaces.OfferedI;

public interface VirtualMachineI extends OfferedI{
	/**
	 * Traitement de la requ�te par la MV
	 * @params Request
	 */
     public void traitementRequete(Request requete)throws Exception;
     
     /**
 	 * R�cup�re l'id de l'application � qui a �t� allou�e la machine virtuelle
 	 * @return uri de l'applicaton � qui a �t� allou�e la MV
 	 */
 	public String getIdApplication();
 	
 	/**
 	 * Met � jour l'id de l'appli � qui est allou�e la MV
 	 * @param idApplication uri de l'application
 	 */
 	public void setIdApplication(String idApplication);
}
 