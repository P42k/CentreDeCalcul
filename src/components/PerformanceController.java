package components;

import java.util.ArrayList;

import ports.CoreInboundPort;
import fr.upmc.components.AbstractComponent;
/** composant simulant le contr�leur de performance 
 * Il n'offre rien
 * Il requiert CoreI
 * Il est cr�� par le CDA lors de la m�thode accept
 * innerComponent de CDA
 * 
 * 
 * */

public class PerformanceController extends AbstractComponent {
	//garder le tmps moyen d'une requ�te, on va voir si elle passe certains seuils
	//changer la fr�quence si au-dessus/en dessous d'un certain seuil
	//Faire des threads comme dans le controller
	
	/** Temps moyen d'ex�cution d'une requ�te */
	private long meanProcessingTime;
	/** Seuil � partir duquel il faut faire descendre la fr�quence */
	static final long lowThreshold=10000L; // � d�finir
	
	/**Seuil � partir duquel il faut augmenter la fr�quence des coeurs */
	static final long highThreshold=100000L;
	/** Liste des Uris des Coeurs (la liste des coeurs des machines virtuelles pour une meme application) */
	ArrayList<Core> listeCoeurs;
	/** cr�e un contr�leur de performances */
	public PerformanceController(){
		this.addRequiredInterface(CoreInboundPort.class);
	}
	
}
