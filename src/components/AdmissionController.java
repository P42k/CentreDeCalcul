package components;

import interfaces.AdmissionControllerI;

import java.util.ArrayList;

import ports.AdmissionControllerInboundPort;
import ports.AdmissionControllerOutboundPort;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.ports.PortI;

public class AdmissionController extends AbstractComponent implements AdmissionControllerI{
	//public static final String admissionControllerURI = "admission-controller-URI";
	private ArrayList<String> listeMVdispos;
	private ArrayList<String> listeComputer;
	private ArrayList<String> listeApplication;
	private ArrayList<AdmissionControllerOutboundPort> cop;
	
	public AdmissionController(String admissionControllerURI, ArrayList<String> uriComputer) throws Exception{
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
		cop = new ArrayList<AdmissionControllerOutboundPort>();
		AdmissionControllerOutboundPort p; 
		for (int i = 0; i<uriComputer.size();i++){
			p = new AdmissionControllerOutboundPort(uriComputer.get(i),this);
			addPort(p);
			p.localPublishPort();
			cop.add(p);
		}
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
	
	/**
	 * Va cr�er les machines virtuelles
	 * @param nb nombre de machines virtuelles � cr�er
	 */
	public void createVirtualMachines(int nb){
		VirtualMachine mv;
		for(int i=0;i<nb;i++){
			listeComputer.get(i)
			mv=new VirtualMachine(listeURICoeur, null, "VM"+i, false);
		}
	}
	
}
