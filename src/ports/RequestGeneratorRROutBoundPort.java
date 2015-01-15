package ports;

import interfaces.RequestRepartitorI;
import ressources.Request;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

public class RequestGeneratorRROutBoundPort extends AbstractOutboundPort {

	public RequestGeneratorRROutBoundPort(String outboundPortURI,
			ComponentI owner) throws Exception {
		super(outboundPortURI, RequestRepartitorI.class, owner);
	}


	public void acceptRequest(Request r) throws Exception {
		((RequestRepartitorI)super.connector).acceptRequest(r);
	}

}
