package interfaces;

import ressources.Request;
import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;

public interface CoreI extends OfferedI, RequiredI {

	/**
	 * Traitement d'une requ�te
	 * @param r	requ�te � traiter
	 * @throws Exception	
	 */
	public void	requestTreatment(Request r) throws Exception ;
	
	/**
	 * Met � jour la fr�quence du coeur
	 * @param frequence double repr�sentant la fr�quence du coeur � choisir dans l'enum Frequence
	 */
	public void setFrequence(double frequence);
	
	/** Met � jour la fr�quence du coeur 
	 * @param frequence double
	 */
	public double getFrequence();
	
	
}
