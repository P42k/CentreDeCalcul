package components;

import java.util.ArrayList;

import fr.upmc.components.AbstractComponent;
import fr.upmc.components.exceptions.ComponentStartException;

public class CalculCenter extends AbstractComponent{
	Computer comp;
	AdmissionController ac;
	String admissionControllerURI = "admissionControllerURI";
	String uriComputer = "uriComputer";
	
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
		ac.start();
	}

	public String getAdmissionControllerURI() {
		return admissionControllerURI;
	}
}
