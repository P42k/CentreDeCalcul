package ressources;

import java.util.concurrent.atomic.AtomicBoolean;

import ports.VirtualMachineOutboundPort;

public class CoreStatus extends Thread{
	private VirtualMachineOutboundPort vmop;
	private final AtomicBoolean isFree;
	
	public AtomicBoolean getIsFree() {
		return isFree;
	}

	CoreStatus(VirtualMachineOutboundPort vmop){
		this.vmop = vmop;
		isFree = new AtomicBoolean(true);;
	}
	
	public void requestTreatment(Request r) throws Exception{
		synchronized(isFree){
			isFree.set(false);
		}
		vmop.requestTreatment(r);
		
		synchronized(isFree){
			isFree.set(true);
		}
	}
	
	public synchronized boolean isFree(){
		return isFree.get();
	}
	
}
