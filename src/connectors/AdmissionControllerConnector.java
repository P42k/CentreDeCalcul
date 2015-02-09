package connectors;

import interfaces.AdmissionControllerI;
import fr.upmc.components.connectors.AbstractConnector;

/**
 * La classe <code>AdmissionControllerConnector</code> implémente un connecteur entre
 * les interfaces <code>AdmissionControllerI</code> et <code>RequestRepartitorI</code>.
 * @author Argonautes
 *
 */
public class AdmissionControllerConnector extends AbstractConnector implements AdmissionControllerI{
	
	public String accept(int requestGeneratorID) throws Exception{
		return ((AdmissionControllerI)offering).accept(requestGeneratorID);
	}
	public void finish(String applicationURI) throws Exception{
		((AdmissionControllerI)offering).finish(applicationURI);
	}
}
