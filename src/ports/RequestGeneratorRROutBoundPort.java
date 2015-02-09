package ports;

import interfaces.RequestRepartitorI;
import ressources.Request;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
/**
 * Outbound port du requestGenerator pour liaison avec le répartiteur de requêtes
 * @author Argonautes
 *
 */
public class RequestGeneratorRROutBoundPort extends AbstractOutboundPort implements RequestRepartitorI{

	public RequestGeneratorRROutBoundPort(String outboundPortURI,
			ComponentI owner) throws Exception {
		super(outboundPortURI, RequestRepartitorI.class, owner);
	}

	@Override
	public void repartition(Request r) throws Exception {
		((RequestRepartitorI)this.connector).repartition(r);
	}


}
