package connectors;

import interfaces.AdmissionControllerI;
import fr.upmc.components.connectors.AbstractConnector;

public class AdmissionControllerConnector extends AbstractConnector{
	public String accept(int requestGeneratorID) throws Exception{
		return ((AdmissionControllerI)offering).accept(requestGeneratorID);
	}
	public void finish(String applicationURI) throws Exception{
		((AdmissionControllerI)offering).finish(applicationURI);
	}
}
