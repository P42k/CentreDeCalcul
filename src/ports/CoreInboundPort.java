package ports;

import interfaces.CoreI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

public class CoreInboundPort extends AbstractInboundPort {

	public CoreInboundPort(String uri,ComponentI owner) throws Exception {
		super(uri, CoreI.class, owner);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
