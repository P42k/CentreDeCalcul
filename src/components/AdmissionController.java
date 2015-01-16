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
	private ArrayList<VirtualMachine> listeMV;
	private ArrayList<String> listeComputer;
	private ArrayList<RequestRepartitor> listeApplication;
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
		listeMV = new ArrayList<VirtualMachine>();
		createVirtualMachines();
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
		ArrayList<String> tmpVm=new ArrayList<String>();
		if(listeMV.size()>=4){
			tmpVm.add(listeMV.get(applicationId*4).getMvUri());
			tmpVm.add(listeMV.get(applicationId*4+1).getMvUri());
			tmpVm.add(listeMV.get(applicationId*4+2).getMvUri());
			tmpVm.add(listeMV.get(applicationId*4+3).getMvUri());
		}
		String auri = "application_"+applicationId;
		applicationId++;
		RequestRepartitor rr = new RequestRepartitor(auri, tmpVm);
		listeApplication.add(rr);
		this.innerComponents.add(rr);

		return auri;
	}

	public void finish(String uriApplication)throws Exception{
		//RequestRepartitor rr = applicationId
		//faire un remove
		//this.innercomponents.remove(rr)
		//d�sallouer les MV � partir de MV
	}
	
	/**
	 * Va cr�er les machines virtuelles. Par convention une machine virtuelle a 4 coeurs
	 * @param nb nombre de machines virtuelles � cr�er
	 */
	public void createVirtualMachines(){
		int cpt = 0;
		VirtualMachine mv;
		ArrayList<String> tmp = new ArrayList<String>();
		for(int i=0;i<cop.size();i++){
			ArrayList<String> coeursUri=cop.get(i).getAvailableCores();
			try {
				while(!coeursUri.isEmpty()){
					if(cpt%4!=0||cpt==0){
						tmp.add(coeursUri.remove(cpt));
					}else{
						mv=new VirtualMachine(tmp, null, "VM_"+cpt/4, false);
						listeMV.add(mv);
						mv.start();
						tmp =  new ArrayList<String>();
					}
				}
			} catch (Exception e) {
				System.out.println("Impossible de cr�er la machine VM"+i);
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
}
