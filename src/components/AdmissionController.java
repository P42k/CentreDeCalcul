package components;

import interfaces.AdmissionControllerI;

import java.rmi.RemoteException;
import java.util.ArrayList;

import connectors.ComputerConnector;
import ports.AdmissionControllerInboundPort;
import ports.AdmissionControllerOutboundPort;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentStartException;
import fr.upmc.components.ports.PortI;

public class AdmissionController extends AbstractComponent{
	//public static final String admissionControllerURI = "admission-controller-URI";
	private ArrayList<VirtualMachine> listeMV;
	private ArrayList<String> listeComputer;
	private ArrayList<RequestRepartitor> listeApplication;
	private ArrayList<AdmissionControllerOutboundPort> cop;
	private int applicationId;
	
	public AdmissionController(String admissionControllerURI, ArrayList<String> uriComputer) throws Exception{
		super(true,true);
		this.toggleTracing();
		applicationId = 0;
		this.listeApplication= new ArrayList<RequestRepartitor>();
		//addofferedInterface pour le centre de calcul et le RG, création de port, addport, localpublishport
		addOfferedInterface(AdmissionControllerI.class);
		try {
			PortI acip = new AdmissionControllerInboundPort(admissionControllerURI, this);
			this.addPort(acip);
			acip.localPublishPort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		listeComputer = uriComputer;
		listeMV = new ArrayList<VirtualMachine>();
		cop = new ArrayList<AdmissionControllerOutboundPort>();
		AdmissionControllerOutboundPort p; 
		for (int i = 0; i<uriComputer.size();i++){
			p = new AdmissionControllerOutboundPort("uriAC"+i,this);
			addPort(p);
			p.localPublishPort();
			cop.add(p);
		}
		System.out.println("Admission controller créé.");
	}
	
	public void start() throws ComponentStartException{
		super.start();
		for(int i=0; i<listeComputer.size();i++){
			try {
				//System.out.println(listeComputer.get(i));
				cop.get(i).doConnection(listeComputer.get(i), "connectors.ComputerConnector");
				System.out.println("La connexion a été faite avec "+ listeComputer.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			createVirtualMachines();
		} catch (RemoteException e) {
			e.printStackTrace();
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
		//on crée une application V
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
		//désallouer les MV à partir de MV
	}
	
	/**
	 * Va créer les machines virtuelles. Par convention une machine virtuelle a 4 coeurs
	 * @param nb nombre de machines virtuelles à créer
	 * @throws RemoteException 
	 */
	public void createVirtualMachines() throws RemoteException{
		System.out.println("on va créer des mv dans admission controller");
		System.out.println("nombre de ports : "+cop.size());
		int cpt = 0;
		VirtualMachine mv;
		ArrayList<String> tmp = new ArrayList<String>();
//		System.out.println(cop.get(0).getId());
		for(int i=0;i<cop.size();i++){
			System.out.println("i = "+i);
			
			ArrayList<String> coeursUri=cop.get(i).getAvailableCores();
			int size = coeursUri.size();
			int cptVM = 0;
			try {
				while(cpt <= size){
					if((cpt!=0 && cpt%4 == 0)) {
						mv=new VirtualMachine(tmp, null, i+"_VM_"+cptVM, false);
						listeMV.add(mv);
						mv.start();
						// si on arrive ici, la création de la mv s'est déroulée sans encombres
						cptVM++;
						for (String coeur : tmp) {
							cop.get(i).setCoreAvailability(coeur, true);
						}
						tmp =  new ArrayList<String>();
						System.out.println("liste des coeurs libres après création de la mv :");
						for (String c: cop.get(i).getAvailableCores()) {
							System.out.println(c);
						}
						System.out.println("$$$$$$$$$$$$$$$$$$$$");
					}
					if(cpt!=size) tmp.add(coeursUri.get(cpt));
					cpt++;
				}
			} catch (Exception e) {
				System.out.println("Impossible de créer la machine VM"+cptVM);
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
}
