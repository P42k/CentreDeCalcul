package connectors;

import ressources.Request;
import interfaces.RequestRepartitorI;
import fr.upmc.components.connectors.AbstractConnector;

public class RequestRepartitorConnector extends AbstractConnector implements RequestRepartitorI{

	@Override
	public void repartition(Request request) throws Exception {
		((RequestRepartitorI) offering).repartition(request);
	}

}
