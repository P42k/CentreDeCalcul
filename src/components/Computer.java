package components;

import java.util.ArrayList;

import ressources.Frequence;
import fr.upmc.components.AbstractComponent;

/** Composant simulant une machine physique */

public class Computer extends AbstractComponent {
	/** Liste des coeurs de l'ordinateur */
	private ArrayList<Core> cores;
	/** id unique de l'ordinateur		 */
	private int id;

	/** Crée un ordinateur avec le nombre de Coeur passé en paramètres
	 * @param int */
	public Computer(int nbCores, int id){
		//innercomponents des coeurs
		cores = new ArrayList<Core>(nbCores);
		this.id=id;
		//ajout des coeurs
		String uriCore = this.id +"";
		for (int i = 0; i<nbCores; i++){
			uriCore = "Core"+i;
			try {
				Core c= new Core(Frequence.F20.getFrequence(),uriCore);
				cores.add(c);
				this.innerComponents.add(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** Récupère la liste des coeurs de l'ordinateur 
	 *@return ArrayList<Core>
	 **/
	public ArrayList<Core> getCores() {
		return cores;
	}

	/** Met à jour la liste des Coeurs avec les coeurs de l'ordinateur avec la liste passée en paramètre 
	 * @param ArrayList<Core>
	 * */
	public void setCores(ArrayList<Core> cores) {
		this.cores = cores;
	}

	/** fonction retournant la liste des coeurs disponibles dans l'ordinateur
	 * @return ArrayList<Core>
	 */
	public ArrayList<Core> getAvailableCores(){
		ArrayList<Core> list= new ArrayList<Core>();
		for(int i=0; i<this.cores.size();i++){
			if(!this.cores.get(i).getUsedByVM()){
				list.add(this.cores.get(i));
			}
		}
		return list;
	}
	
	
}
