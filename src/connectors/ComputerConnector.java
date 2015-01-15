package connectors;

import java.util.ArrayList;

import interfaces.ComputerI;
import fr.upmc.components.AbstractComponent;
import fr.upmc.components.connectors.AbstractConnector;

public class ComputerConnector extends AbstractConnector implements ComputerI{

	@Override
	public void setAvailableCore(String uriCore, boolean b) {
		((ComputerI)this.offering).setAvailableCore(uriCore, b);
	}

	@Override
	public ArrayList<String> getAvailableCores() {
		return ((ComputerI)this.offering).getAvailableCores();
	}
	
}
