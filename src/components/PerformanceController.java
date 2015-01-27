package components;

import interfaces.CoreI;

import java.util.ArrayList;

import ports.CoreInboundPort;
import ports.PerformanceControllerOutboundPort;
import fr.upmc.components.AbstractComponent;
/** composant simulant le contrôleur de performance 
 * Il n'offre rien
 * Il requiert CoreI
 * Il est créé par le CDA lors de la méthode accept
 * innerComponent de CDA
 * */

public class PerformanceController extends AbstractComponent {
	//garder le tmps moyen d'une requête, on va voir si elle passe certains seuils
	//changer la fréquence si au-dessus/en dessous d'un certain seuil
	//Faire des threads comme dans le controller
	//
	/** Temps moyen d'exécution d'une requête */
	private long meanProcessingTime;
	/** Seuil à partir duquel il faut faire descendre la fréquence */
	static final long lowThreshold=10000L; // à définir
	
	/**Seuil à partir duquel il faut augmenter la fréquence des coeurs */
	static final long highThreshold=100000L;
	/** Liste des Uris des Coeurs (la liste des coeurs des machines virtuelles pour une meme application) */
	ArrayList<PerformanceControllerOutboundPort> listeCoeurs;
	/** crée un contrôleur de performances */
	public PerformanceController(){
		this.addRequiredInterface(CoreI.class);
	}
	
	public void start(){
		//faire la connexion entre le composant et les coeurs
	}
	/**
	 * méthode qui va s'appeler récursivement
	 */
	public void checkFreq(){
		for(PerformanceControllerOutboundPort p : listeCoeurs){
			//faire un getMeanProcessingTime
			//Si superieur à seuil, augmenter fréquence
			//Si trop basse (30%), diminuer la frequence
		}
	}
	
}
