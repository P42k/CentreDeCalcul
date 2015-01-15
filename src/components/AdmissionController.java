package components;

import interfaces.AdmissionControllerI;

import java.util.ArrayList;

import connectors.AdmissionControllerInboundPort;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.ports.PortI;

public class AdmissionController extends AbstractComponent implements AdmissionControllerI{
	//public static final String admissionControllerURI = "admission-controller-URI";
	private ArrayList<String> listeMVdispos;
	private Computer computer;
	private ArrayList<String> listeApplication;
	
	public AdmissionController(String admissionControllerURI, ArrayList<String> uriComputer){
		super(true,true);
		//addofferedInterface pour le centre de calcul et le RG, cr�ation de port, addport, localpublishport
		addOfferedInterface(AdmissionControllerI.class);
		try {
			PortI acip = new AdmissionControllerInboundPort(admissionControllerURI, this);
			addPort(acip);
			acip.localPublishPort();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//On d�finit que un MV a deux coeurs
		for(int i=0;i<computer.getCoeurs().size();i++){
			
		}
		//cr�ation des mv
		
	}
	
	/***
	 * 
	 * @param RequestGeneratorID
	 * @return uri de l'application
	 * @throws Exception
	 */
	public String accept(int RequestGeneratorID)throws Exception{
		//verifier les mvdispos
		//on fixe le meme nombre de MV pour chaque appli (2MV par appli)
		//on enleve les mv de la listeMVdispos
		//on cr�e une application
		//on l'ajoute dans la listeApplication
		//on retourne l'uri de celle ci
		return null;
	}
	
	public void finish(String uriApplication)throws Exception{
		//enlever de la liste
		//d�sallou�s les MV
		//remettre les mv dans la listeMVdispos
	}
	
}
