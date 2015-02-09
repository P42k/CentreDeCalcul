package ressources;

import java.util.concurrent.atomic.AtomicBoolean;

import ports.VirtualMachineOutboundPort;

/**
 * Thread statut du coeur, s'il exécute une requête ou non
 * @author Julia
 *
 */
public class CoreStatus extends Thread{
	/**le port qui va demander le traitement sur le coeur libre*/
	private VirtualMachineOutboundPort vmop;
	/** indique si le coeur est libre ou non */
	private final AtomicBoolean isFree;
	
	
	public CoreStatus(VirtualMachineOutboundPort vmop){
		this.vmop = vmop;
		isFree = new AtomicBoolean(true);;
	}
	
	/***
	 * va retourner l'attribut isFree
	 * @return isFree
	 */
	public AtomicBoolean getIsFree() {
		return isFree;
	}

	/***
	 * appelle le traitement de la requete par le coeur
	 * @param r
	 * @throws Exception
	 */
	public void requestTreatment(Request r) throws Exception{
		//on met ce coeur en occupé
		synchronized(isFree){
			isFree.set(false);
		}
		//on traite la requete
		vmop.requestTreatment(r);
		
		//on libère le coeur
		synchronized(isFree){
			isFree.set(true);
		}
	}
	
	/***
	 * retourne si le coeur est disponible ou pas
	 * @return le booleen de isFree
	 */
	public synchronized boolean isFree(){
		return isFree.get();
	}
	
}
