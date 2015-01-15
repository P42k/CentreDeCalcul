package interfaces;

import java.util.ArrayList;

import fr.upmc.components.interfaces.OfferedI;

/** Interface offerte du composant ordinateur */

public interface ComputerI extends OfferedI{
	
	/** Mise à jour de la disponibilité d'un coeur vis-à-vis d'une machine virtuelle
	 * @param uriCore uri du Coeur
	 * @param b booléen de la disponibilité
	 */
	public void setAvailableCore(String uriCore, boolean b);

	/** Fonction retournant la liste des uri des coeurs disponibles dans l'ordinateur
	 * @return liste des uri des coeurs disponibles
	 */
	public ArrayList<String> getAvailableCores();

	
}
