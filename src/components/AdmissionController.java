package components;

import interfaces.AdmissionControllerI;

import java.util.ArrayList;

import connectors.ComputerConnector;
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
	private int applicationId;
	
	public AdmissionController(String admissionControllerURI, ArrayList<String> uriComputer) throws Exception{
		super(true,true);
		applicationId = 0;
		//addofferedInterface pour le centre de calcul et le RG, cr�ation de port, addport, localpublishport
		addOfferedInterface(AdmissionControllerI.class);
		try {
			PortI acip = new AdmissionControllerInboundPort(admissionControllerURI, this);
			addPort(acip);
			acip.localPublishPort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		listeComputer = uriComputer;
		cop = new ArrayList<AdmissionControllerOutboundPort>();
		AdmissionControllerOutboundPort p; 
		for (int i = 0; i<uriComputer.size();i++){
			p = new AdmissionControllerOutboundPort(uriComputer.get(i),this);
			addPort(p);
			p.localPublishPort();
			cop.add(p);
		}
	}
	
	public void start(){
		for(int i=0; i<listeComputer.size();i++){
			try {
				cop.get(i).doConnection(listeComputer.get(i), ComputerConnector.class.getCanonicalName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/***
	 * 
	 * @param RequestGeneratorID
	 * @return uri de l'application
	 * @throws Exception
	 */
	public String accept(int RequestGeneratorID)throws Exception{
		//verifier les mvdispos V
		//on fixe le meme nombre de MV pour chaque appli (2MV par appli)V
		//on enleve les mv de la listeMVdisposV
		//on cr�e une application V
		//on l'ajoute dans la listeApplication;
		//on retourne l'uri de celle ci
		if(listeMVdispos.size()>=4){
			ArrayList<String> tmpVm=new ArrayList<String>();
			tmpVm.add(listeMVdispos.remove(0));
			tmpVm.add(listeMVdispos.remove(0));
			tmpVm.add(listeMVdispos.remove(0));
			tmpVm.add(listeMVdispos.remove(0));
		}
		String auri = "application_"+applicationId;
		applicationId++;
		listeApplication.add(auri);

		return auri;
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
		ArrayList<String> coeursUri;
		for(int i=0;i<nb;i++){
			coeursUri=cop.get(i).getAvailableCores();
			try {
				mv=new VirtualMachine(coeursUri, null, "VM"+i, false);
				listeMVdispos.add("VM"+i);
			} catch (Exception e) {
				System.out.println("Impossible de cr�er la machine VM"+i);
				e.printStackTrace();
			}
		}
	}
	
}
