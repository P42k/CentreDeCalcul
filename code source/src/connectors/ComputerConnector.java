package connectors;

import java.rmi.RemoteException;
import java.util.ArrayList;

import interfaces.ComputerI;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.connectors.AbstractConnector;

public class ComputerConnector extends AbstractConnector implements ComputerI{

	@Override
	public void setAvailableCore(String uriCore, boolean b) throws RemoteException {
		((ComputerI)this.offering).setAvailableCore(uriCore, b);
	}

	@Override
	public ArrayList<String> getAvailableCores() throws RemoteException {
		return ((ComputerI)this.offering).getAvailableCores();
	}

	@Override
	public String getId() throws RemoteException {
		return ((ComputerI)this.offering).getId();
	}
	
}
