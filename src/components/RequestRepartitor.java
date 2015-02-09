package components;

import interfaces.RequestRepartitorI;

import java.util.ArrayList;

import ports.RequestRepartitorInboundPort;
import ports.RequestRepartitorOutboundPort;
import ressources.Request;
import connectors.VirtualMachineConnector;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentStartException;
import fr.upmc.components.ports.PortI;

public class RequestRepartitor extends AbstractComponent {
	/** garde l'indice de la machine virtuelle courante */
	private int mvcourante; 
	/** la liste des uri des machines virtuelles avec lesquelles le répartiteur va communiquer */ 
	private ArrayList<String> listeMV;
	/** */
	private ArrayList<RequestRepartitorOutboundPort> listePortsRR;
	/** stocke l'URI de l'application */
	private String uriApplication; //va permettre que le répartiteur de requete soit retrouvé par le RG

	
	/**
	 * Crée le composant du Répartiteur de Requete
	 * @param ArrayList
	 * @param uriMVs
	 * @throws Exception
	 * @pre 	uriApplication != null && uriMVs !=null
	 * 
	 */
	public RequestRepartitor(String uriApplication,ArrayList<String> uriMVs) throws Exception{
		
		mvcourante = 0;
		this.listeMV = uriMVs;
		this.uriApplication = uriApplication;
		this.listePortsRR= new ArrayList<RequestRepartitorOutboundPort>();
		//addOfferedInterface(), création du port, addport, localpublishport
		this.addOfferedInterface(RequestRepartitorI.class);
		PortI rrip = new RequestRepartitorInboundPort(this.uriApplication, this);
		this.addPort(rrip);
		rrip.localPublishPort();	
		
		//addRequiredInterface(MV), création des ports , addport, localpublishport, ajouter listeMV
		this.addRequiredInterface(RequestRepartitorI.class);
		RequestRepartitorOutboundPort q;
		for(int i=0; i<this.listeMV.size();i++){
			//créer les ports pour communiquer avec les coeurs et les ajouter au composants ainsi qu'à la liste des ports Coeurs
			q= new RequestRepartitorOutboundPort(this.listeMV.get(i), this);
			this.addPort(q);
			q.localPublishPort();
			this.listePortsRR.add(q);
		}
		
		System.out.println("Request Repartitor a été crée ");
	}
	/**
	 * Répartit les requetes aux machines virtuelles
	 * @param requete
	 * @throws Exception
	 * @pre    requete !=null
	 */
	public void repartition(Request requete) throws Exception{
		System.out.println("entrée dans la méthode répartition de requête ");
		assert requete !=null;
		this.listePortsRR.get(this.mvcourante).traitementRequete(requete);
		this.mvcourante++;
		System.out.println("Requete envoyée à la machine virtuelle " +listePortsRR.get(mvcourante-1));
	}
	
	/**
	 * Sert à effectuer la connexion des ports recquis 
	 * au démarrage du centre de calcul
	 */
	@Override
	public void start() throws ComponentStartException{
		super.start();
		for (int i=0; i<listePortsRR.size();i++){
			try{
				this.listePortsRR.get(i).doConnection(this.listeMV.get(i),VirtualMachineConnector.class.getCanonicalName());
				System.out.println("Connexion du répartiteur de requete  "+this.listePortsRR.get(i)+ "avec la machine virtuelle " + this.listeMV.get(i));
			}catch(Exception e){
				System.err.println("Connection impossible avec la Machine Virtuelle : " + listeMV.get(i));
			}
		}

	}

	
	
}
