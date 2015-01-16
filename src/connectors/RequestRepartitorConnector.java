package connectors;

import fr.upmc.components.connectors.AbstractConnector;
import ressources.Request;
import interfaces.RequestRepartitorI;

public class RequestRepartitorConnector extends AbstractConnector{

	public void repartition(Request request) throws Exception{
		((RequestRepartitorI)offering).repartition(request);
	}
}
