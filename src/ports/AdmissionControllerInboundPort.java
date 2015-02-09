package ports;

import components.AdmissionController;
import interfaces.AdmissionControllerI;
import fr.upmc.components.ComponentI;
import fr.upmc.components.ComponentI.ComponentService;
import fr.upmc.components.ports.AbstractInboundPort;
/**
 * Inbound port de l'admission controller
 * @author Argonautes
 *
 */
public class AdmissionControllerInboundPort extends AbstractInboundPort implements AdmissionControllerI{

	public AdmissionControllerInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, AdmissionControllerI.class, owner);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String accept(final int requestGeneratorId) throws Exception {
		final AdmissionController ac = (AdmissionController)this.owner;
		return ac.handleRequestSync(
				new ComponentService<String>() {
					@Override
					public String call() throws Exception {
						return ac.accept(requestGeneratorId);
					}
				});
	}
	
	@Override
	public void finish(final String applicationURI) throws Exception{
		final AdmissionController ac = (AdmissionController) this.owner;
		ac.handleRequestSync(
				new ComponentService<String>() {
					@Override
					public String call() throws Exception {
						 ac.finish(applicationURI) ;
						return "";
					}
				});
		
	}

}
