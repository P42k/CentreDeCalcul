package components;

import java.util.ArrayList;

import fr.upmc.components.AbstractComponent;

public class Computer extends AbstractComponent {
	private ArrayList<Core> Coeurs;
	
	public ArrayList<Core> getCoeurs() {
		return Coeurs;
	}

	public void setCoeurs(ArrayList<Core> coeurs) {
		Coeurs = coeurs;
	}

	public Computer(){
		//innercomponents des coeurs
	}
	
	
}
