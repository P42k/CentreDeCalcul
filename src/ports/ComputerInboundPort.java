package ports;

import java.rmi.RemoteException;
import java.util.ArrayList;

import components.Computer;
import interfaces.ComputerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

/**Inbound port du composant Ordinateur
 * 
 * @author Argonautes
 *
 */

public class ComputerInboundPort  extends AbstractInboundPort implements ComputerI{

	/** Crée le port avec une certaine uri et un certain owner */
	public ComputerInboundPort(String uri,ComponentI owner) throws Exception {
		super(uri, ComputerI.class, owner);
	}

	/**
	 * Nécessaire
	 */
	private static final long serialVersionUID = 1L;

	
	/** Appelle la méthode setAvailableCore du composant Ordinateur */
	@Override
	public void setCoreAvailability(String uriCore, boolean b) throws RemoteException{
		final Computer c = (Computer)  this.owner;
		c.setCoreAvailability(uriCore, b);
	}

	/** Appelle la méthode getAvailableCores du composant Ordinateur */
	@Override
	public ArrayList<String> getAvailableCores() throws RemoteException{
		final Computer c = (Computer)  this.owner;
		return c.getAvailableCores();
	}

	public String getId() throws RemoteException{
		final Computer c = (Computer) this.owner;
		return c.getId();
	}
}
