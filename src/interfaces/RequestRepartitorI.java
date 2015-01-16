package interfaces;

import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;
import ressources.Request;

public interface RequestRepartitorI extends OfferedI, RequiredI {

	public void acceptRequest(Request request) throws Exception;
}
