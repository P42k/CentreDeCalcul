package components;

import java.util.ArrayList;

import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentStartException;

/**
 * Le Centre de calcul
 * @author Argonautes
 *
 */
public class CalculCenter extends AbstractComponent{
	Computer comp;
	AdmissionController ac;
	String admissionControllerURI = "admissionControllerURI";
	String uriComputer = "uriComputer";
	
	/**
	 * Crée un centre de calcul
	 * @throws Exception
	 */
	public CalculCenter() throws Exception{
		
		comp = new Computer(16,uriComputer);

		ArrayList<String> cliste = new ArrayList<String>();
		cliste.add(uriComputer);
		ac = new AdmissionController(admissionControllerURI, cliste);
		this.innerComponents.add(ac);
		this.innerComponents.add(comp);
	}
	
	public void start() throws ComponentStartException{
		super.start();
//		comp.start();
//		ac.start();
	}

	/**
	 * Getter de l'uri de l'admission controller inner component du centre de calcul
	 * @return l'uri de l'admission controller
	 */
	public String getAdmissionControllerURI() {
		return admissionControllerURI;
	}
}
