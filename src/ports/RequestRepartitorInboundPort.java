package ports;

import interfaces.RequestRepartitorI;
import ressources.Request;

import components.RequestRepartitor;

import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;
/** Inbound port du répartiteur de requete */
public class RequestRepartitorInboundPort extends AbstractInboundPort implements RequestRepartitorI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestRepartitorInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, RequestRepartitorI.class , owner);
	}

	@Override
	public void repartition(Request request) throws Exception {
		final RequestRepartitor rr = (RequestRepartitor) this.owner ;
		rr.repartition(request);
	}

}
