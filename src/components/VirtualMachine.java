package components;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ports.CorePort;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentStartException;

public class VirtualMachine extends AbstractComponent {
	private ArrayList<CorePort> listeCoeurs;
	private int idApplication;
	private ArrayList<String> listeURICoeur;
	
	public VirtualMachine(ArrayList<String> listeURICoeur, int idApp){
		//ajout de la méthode offeredInterface, création port qui fournit la machine virtuelle, addport
		//crée des ports qui requiert coeur, de les ajouter au composant puis à listeCoeurs
	}
	
	public void traitementRequete(Requete requete, int idCoeur) throws RemoteException, InterruptedException, Exception{
		listeCoeurs.get(idCoeur).traitementRequete(requete);
	}
	
	@Override
	public void start() throws ComponentStartException{
		super.start();
		for (int i=0; i<listeCoeurs.size();i++){
			//listeCoeur.get(i).doConnetion(listeURICoeur.get(i), le connecteur)
		}
	}
	
}
