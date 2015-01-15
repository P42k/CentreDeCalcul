package ports;

import components.Core;

import ressources.Request;
import interfaces.CoreI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

/** InboundPort du composant Coeur */
public class CoreInboundPort extends AbstractInboundPort implements CoreI {

	/** Cr�e le port avec une certaine uri et un certain owner */
	public CoreInboundPort(String uri,ComponentI owner) throws Exception {
		super(uri, CoreI.class, owner);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void requestTreatment(Request r) throws Exception {
		final Core c = (Core) this.owner;
		c.requestTreatment(r);
	}

	@Override
	public boolean isFree() {
		final Core c = (Core) this.owner;
		return c.isFree();
	}

	@Override
	public void setFrequence(double frequence) {
		final Core c = (Core) this.owner;
		c.setFrequence(frequence);
	}

	@Override
	public double getFrequence() {
		final Core c = (Core) this.owner;
		return c.getFrequence();
	}

}
