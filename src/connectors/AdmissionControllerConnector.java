package connectors;

import interfaces.AdmissionControllerI;
import fr.upmc.components.connectors.AbstractConnector;

public class AdmissionControllerConnector extends AbstractConnector{
	public String accept(int requestGeneratorID){
		return ((AdmissionControllerI)offering).accept(requestGeneratorID);
	}
	public void finish(int requestGeneratorID){
		((AdmissionControllerI)offering).finish(requestGeneratorID);
	}
}
