package ports;

import java.util.ArrayList;
import components.Computer;
import interfaces.ComputerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

/**Inbound port du composant Ordinateur */

public class ComputerInboundPort  extends AbstractInboundPort implements ComputerI{

	/** Cr�e le port avec une certaine uri et un certain owner */
	public ComputerInboundPort(String uri,ComponentI owner) throws Exception {
		super(uri, ComputerI.class, owner);
	}

	/**
	 * N�cessaire
	 */
	private static final long serialVersionUID = 1L;

	
	/** Appelle la m�thode setAvailableCore du composant Ordinateur */
	@Override
	public void setAvailableCore(String uriCore, boolean b) {
		final Computer c = (Computer)  this.owner;
		c.setAvailableCore(uriCore, b);
	}

	/** Appelle la m�thode getAvailableCores du composant Ordinateur */
	@Override
	public ArrayList<String> getAvailableCores() {
		final Computer c = (Computer)  this.owner;
		return c.getAvailableCores();
	}

}
