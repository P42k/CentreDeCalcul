package ressources;

import java.util.ArrayList;

import components.Core;

public class Computer {
	private ArrayList<Core> coeurs;
	
	public ArrayList<Core> getCoeurs() {
		return coeurs;
	}

	public void setCoeurs(ArrayList<Core> coeurs) {
		this.coeurs = coeurs;
	}

	public Computer(){
		coeurs = new ArrayList<Core>();
		//ajout pour l'instant de 20 coeurs
		for (int i = 0; i<10; i++){
			coeurs.add(new Core(i, 3.0));
			coeurs.add(new Core(i, 3.0));
		}
		
	}
	
	
	
}
