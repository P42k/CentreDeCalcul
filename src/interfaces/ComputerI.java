package interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;

import fr.upmc.components.interfaces.OfferedI;

/** Interface offerte du composant ordinateur */

public interface ComputerI extends OfferedI{
	
	/** Mise � jour de la disponibilit� d'un coeur vis-�-vis d'une machine virtuelle
	 * @param uriCore uri du Coeur
	 * @param b bool�en de la disponibilit�
	 */
	public void setCoreAvailability(String uriCore, boolean b) throws RemoteException;

	/** Fonction retournant la liste des uri des coeurs disponibles dans l'ordinateur
	 * @return liste des uri des coeurs disponibles
	 */
	public ArrayList<String> getAvailableCores() throws RemoteException;
	
	public String getId() throws RemoteException;
	
}
