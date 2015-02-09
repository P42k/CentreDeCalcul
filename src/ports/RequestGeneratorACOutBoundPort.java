package ports;

import interfaces.AdmissionControllerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
/**
 * Outbound port du requestGenerator pour liaison avec l'admission controller
 * @author Argonautes
 *
 */
public class RequestGeneratorACOutBoundPort extends AbstractOutboundPort implements AdmissionControllerI{

	public RequestGeneratorACOutBoundPort(ComponentI owner) throws Exception {
		super(AdmissionControllerI.class, owner);
		
	}


	@Override
	public String accept(int RequestGeneratorId) throws Exception {
		return ((AdmissionControllerI) this.connector).accept(RequestGeneratorId);
	}



	@Override
	public void finish(String applicationURI) throws Exception {
		((AdmissionControllerI) this.connector).finish(applicationURI);
		
	}

}
