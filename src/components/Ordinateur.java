package ressources;

import java.util.ArrayList;

import components.Core;

public class Ordinateur {
	private ArrayList<Core> coeurs;
	
	public Ordinateur(){
		coeurs = new ArrayList<Core>();
		//ajout pour l'instant de 20 coeurs
		for (int i = 0; i<10; i++){
			coeurs.add(new Core(i, 3.0));
			coeurs.add(new Core(i, 3.0));
		}
		
	}
	
	//Réallouer les coeurs de l'ordi à un nouveau controleur d'admission
	
}
