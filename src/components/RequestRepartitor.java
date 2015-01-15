package components;

import interfaces.RequestRepartitorI;

import java.util.ArrayList;

import ports.RequestRepartitorInboundPort;
import ressources.Request;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.ports.PortI;

public class RequestRepartitor extends AbstractComponent {
	private int mvcourante; //garde l'indice de la mv courante
	private ArrayList<MVport> listeMV;
	private String uriApplication; //va permettre que le répartiteur de requete soit retrouvé par le RG
	
	/**
	 * 
	 * @param ArrayList
	 * @param uriMVs
	 * @throws Exception
	 */
	public RequestRepartitor(String ArrayList,ArrayList<String> uriMVs) throws Exception{
		super(true,true);
		mvcourante = 0;
		//addOfferedInterface(), création du port, addport, localpublishport
		addOfferedInterface(RequestRepartitorI.class);
		PortI rrip = new RequestRepartitorInboundPort(ArrayList, null);
		//addRequiredInterface(MV), création des ports , addport, localpublishport, ajouter listeMV
	}
	
	public void repartition(Request requete){
		//TODO va répartir la requete sur la mv  
	}
	
	
}
