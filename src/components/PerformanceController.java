package components;

import interfaces.CoreI;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ports.CoreInboundPort;
import ports.PerformanceControllerOutboundPort;
import ports.VirtualMachineOutboundPort;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentStartException;
/** composant simulant le contr�leur de performance 
 * Il n'offre rien
 * Il requiert CoreI
 * Il est cr�� par le CDA lors de la m�thode accept
 * innerComponent de CDA
 * */

public class PerformanceController extends AbstractComponent {
	//garder le tmps moyen d'une requ�te, on va voir si elle passe certains seuils
	//changer la fr�quence si au-dessus/en dessous d'un certain seuil
	//Faire des threads comme dans le controller
	//
	/** Temps moyen d'ex�cution d'une requ�te */
	private long meanProcessingTime;
	/** Seuil � partir duquel il faut faire descendre la fr�quence */
	static final long lowThreshold=10000L; // � d�finir
	
	/**Seuil � partir duquel il faut augmenter la fr�quence des coeurs */
	static final long highThreshold=100000L;
	
	/** uri du performance controller */
	String uri;
	
	/** Liste des ports recqui�rants du performance controller */
	ArrayList<PerformanceControllerOutboundPort> listePorts;
	
	/** Liste des Uris des Coeurs (la liste des coeurs des machines virtuelles pour une meme application) */
	ArrayList<String> listeURICoeur;
	
	/** cr�e un contr�leur de performances */
	public PerformanceController(long meanprocessingtime, ArrayList<String> listUriCoeurs, String uri) throws Exception{
		this.meanProcessingTime=meanprocessingtime;
		this.listeURICoeur=listUriCoeurs;
		this.uri = uri;
		//cr�ation des ports outbound pour communiquer avec les coeurs utilis�s par l'application
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
				System.out.println("Connexion du contr�leur de performance "+uri +" avec le coeur " + listeURICoeur.get(i));
			}catch(Exception e){
				System.err.println("Connection impossible du performance controller avec le coeur " + listeURICoeur.get(i));
			}
		}
	}
	
	/**
	 * m�thode qui va s'appeler r�cursivement pour v�rifier la fr�quence et permettre son changement
	 * en fonction de seuil et par rapport au temps moyen de process calcul� dans le g�n�rateur
	 */
	public void checkFreq(){
		//this.meanProcessingTime=?
		//calcul du meanprocessingtime dans le r�partiteur et renvoie de cette valeur � chaque appel � r�partition
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
			checkFreq(); // Condition d'arr�t?
		
			//faire un getMeanProcessingTime
			//Si superieur � seuil, augmenter fr�quence
			//Si trop basse (30%), diminuer la frequence
		}
	}
	
}
