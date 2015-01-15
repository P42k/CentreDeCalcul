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
	private String uriApplication; //va permettre que le r�partiteur de requete soit retrouv� par le RG
	
	/**
	 * 
	 * @param ArrayList
	 * @param uriMVs
	 * @throws Exception
	 */
	public RequestRepartitor(String ArrayList,ArrayList<String> uriMVs) throws Exception{
		super(true,true);
		mvcourante = 0;
		//addOfferedInterface(), cr�ation du port, addport, localpublishport
		addOfferedInterface(RequestRepartitorI.class);
		PortI rrip = new RequestRepartitorInboundPort(ArrayList, null);
		//addRequiredInterface(MV), cr�ation des ports , addport, localpublishport, ajouter listeMV
	}
	
	public void repartition(Request requete){
		//TODO va r�partir la requete sur la mv  
	}
	
	
}
