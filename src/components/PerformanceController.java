package components;

import ports.CoreInboundPort;
import fr.upmc.components.AbstractComponent;
/** composant simulant le contr�leur de performance */

public class PerformanceController extends AbstractComponent {
	//garder le tmps moyen d'une requ�te
	//changer la fr�quence si au-dessus/en dessous d'un certain seuil, changer la fr�quence ou sinon la m�me mais avec la taille de la
	//file d'attente.
	//Faire des threads comme dans le controller
	/** Seuil � partir duquel il faut faire descendre la fr�quence */
	static final long lowThreshold=10000L; // � d�finir
	
	/**Seuil � partir duquel il faut augmenter la fr�quence des coeurs */
	static final long highThreshold=100000L;
	
	/** cr�e un contr�leur de performances */
	public PerformanceController(){
		this.addRequiredInterface(CoreInboundPort.class);
	}
	
	//faire un start avec la connexion avec les coeurs?
}
