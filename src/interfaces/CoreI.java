package interfaces;

import ressources.Request;
import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;

public interface CoreI extends OfferedI, RequiredI {

	/**
	 * treat a new request .
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	r != null
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param r				request to be serviced.
	 * @throws Exception	
	 */
	public void			requestTreatment(Request r) throws Exception ;
	
	/**
	 * 
	 * 
	 */
	
	public boolean isFree();
	/**
	 * 
	 * 
	 */
	public void setFrequence(double frequence);
	/**
	 * 
	 */
	public double getFrequence();
	
	
}
