package ports;

import ressources.Request;
import interfaces.RequestRepartitorI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

public class RequestRepartitorInboundPort extends AbstractInboundPort{

	public RequestRepartitorInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, RequestRepartitorI.class , owner);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void requestTreatment(Request requete){
		
	}
	
	
}
