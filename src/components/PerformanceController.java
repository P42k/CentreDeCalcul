package components;

import interfaces.CoreI;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ports.CoreInboundPort;
import ports.PerformanceControllerOutboundPort;
import ports.VirtualMachineOutboundPort;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentStartException;
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
	
	/** uri du performance controller */
	String uri;
	
	/** Liste des ports recquiérants du performance controller */
	ArrayList<PerformanceControllerOutboundPort> listePorts;
	
	/** Liste des Uris des Coeurs (la liste des coeurs des machines virtuelles pour une meme application) */
	ArrayList<String> listeURICoeur;
	
	/** crée un contrôleur de performances */
	public PerformanceController(long meanprocessingtime, ArrayList<String> listUriCoeurs, String uri) throws Exception{
		this.meanProcessingTime=meanprocessingtime;
		this.listeURICoeur=listUriCoeurs;
		this.uri = uri;
		//création des ports outbound pour communiquer avec les coeurs utilisés par l'application
		PerformanceControllerOutboundPort q;
		listePorts = new ArrayList<PerformanceControllerOutboundPort>();
		for(int i=0; i<this.listeURICoeur.size();i++){
			q= new PerformanceControllerOutboundPort(uri+"TO"+listeURICoeur.get(i), this);
			this.addPort(q);
			q.localPublishPort();
			this.listePorts.add(q);
		}
		this.addRequiredInterface(CoreI.class);
	}
	
	/**
	 * Permet la connexion avec les coeurs
	 */
	public void start() throws ComponentStartException{
		//faire la connexion entre le composant et les coeurs
		super.start();
		for (int i=0; i<listePorts.size();i++){
			try{
				this.listePorts.get(i).doConnection(listeURICoeur.get(i),"connectors.CoreConnector");
				System.out.println("Connexion du contrôleur de performance "+uri +" avec le coeur " + listeURICoeur.get(i));
			}catch(Exception e){
				System.err.println("Connection impossible du performance controller avec le coeur " + listeURICoeur.get(i));
			}
		}
	}
	
	/**
	 * méthode qui va s'appeler récursivement pour vérifier la fréquence et permettre son changement
	 * en fonction de seuil et par rapport au temps moyen de process calculé dans le générateur
	 */
	public void checkFreq(){
		//this.meanProcessingTime=?
		//calcul du meanprocessingtime dans le répartiteur et renvoie de cette valeur à chaque appel à répartition
		for(PerformanceControllerOutboundPort p : listePorts){
			if(this.meanProcessingTime<lowThreshold){
				double freq;
				try {
					freq = p.getFrequence();
					p.setFrequence(freq-0.2);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			if(this.meanProcessingTime>highThreshold){
				double freq;
				try {
					freq = p.getFrequence();
					p.setFrequence(freq+0.2);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			checkFreq(); // Condition d'arrêt?
		
			//faire un getMeanProcessingTime
			//Si superieur à seuil, augmenter fréquence
			//Si trop basse (30%), diminuer la frequence
		}
	}
	
}
