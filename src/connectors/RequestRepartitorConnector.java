package connectors;

import fr.upmc.components.connectors.AbstractConnector;
import ressources.Request;
import interfaces.RequestRepartitorI;

public class RequestRepartitorConnector extends AbstractConnector{

	public void acceptRequest(Request request) throws Exception{
		((RequestRepartitorI)offering).acceptRequest(request);
	}
}
