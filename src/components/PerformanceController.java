package components;

import ports.CoreInboundPort;
import fr.upmc.components.AbstractComponent;
/** composant simulant le contrôleur de performance */

public class PerformanceController extends AbstractComponent {
	//garder le tmps moyen d'une requête
	//changer la fréquence si au-dessus/en dessous d'un certain seuil, changer la fréquence ou sinon la même mais avec la taille de la
	//file d'attente.
	//Faire des threads comme dans le controller
	/** Seuil à partir duquel il faut faire descendre la fréquence */
	static final long lowThreshold=10000L; // à définir
	
	/**Seuil à partir duquel il faut augmenter la fréquence des coeurs */
	static final long highThreshold=100000L;
	
	/** crée un contrôleur de performances */
	public PerformanceController(){
		this.addRequiredInterface(CoreInboundPort.class);
	}
	
	//faire un start avec la connexion avec les coeurs?
}
