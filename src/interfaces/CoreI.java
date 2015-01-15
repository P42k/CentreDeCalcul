package interfaces;

import ressources.Request;
import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;

public interface CoreI extends OfferedI, RequiredI {

	/**
	 * Traitement d'une requête
	 * @param r	requête à traiter
	 * @throws Exception	
	 */
	public void	requestTreatment(Request r) throws Exception ;
	
	/**
	 * Met à jour la fréquence du coeur
	 * @param frequence double représentant la fréquence du coeur à choisir dans l'enum Frequence
	 */
	public void setFrequence(double frequence);
	
	/** Met à jour la fréquence du coeur 
	 * @param frequence double
	 */
	public double getFrequence();
	
	
}
