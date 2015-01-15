package ports;

import interfaces.AdmissionControllerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;

public class RequestGeneratorACOutBoundPort extends AbstractOutboundPort implements AdmissionControllerI{

	public RequestGeneratorACOutBoundPort(ComponentI owner) throws Exception {
		super(AdmissionControllerI.class, owner);
		
	}


	@Override
	public String accept(int RequestGeneratorId) throws Exception {
		return ((AdmissionControllerI) super.connector).accept(RequestGeneratorId);
	}



	@Override
	public void finish(String applicationURI) throws Exception {
		((AdmissionControllerI) super.connector).finish(applicationURI);
		
	}

}
