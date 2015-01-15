package interfaces;

import ressources.Request;
import fr.upmc.components.interfaces.OfferedI;

public interface RequestRepartitorI extends OfferedI {

	public void acceptRequest(Request request) throws Exception;
}
