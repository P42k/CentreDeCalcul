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

	/** Cr�e un ordinateur avec le nombre de Coeur pass� en param�tres
	 * @param int nombre de Coeurs
	 * @param int id de l'oridnateur
	 * */
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
		System.out.println("L'ordinateur " + id + "a �t� cr��.");

	}

	/** R�cup�re la liste des coeurs de l'ordinateur 
	 *@return ArrayList<Core> liste de coeur
	 **/
	public ArrayList<Core> getCores() {
		return cores;
	}

	/** Met � jour la liste des Coeurs avec les coeurs de l'ordinateur avec la liste pass�e en param�tre 
	 * @param ArrayList<Core> liste de coeur
	 * */
	public void setCores(ArrayList<Core> cores) {
		this.cores = cores;
	}

	/** Fonction retournant la liste des uri des coeurs disponibles dans l'ordinateur
	 * @return ArrayList<String> liste des uris des coeurs disponibles
	 */
	public ArrayList<String> getAvailableCores(){
		ArrayList<String> list= new ArrayList<String>();
		for(int i=0; i<this.cores.size();i++){
			if(!this.cores.get(i).getUsedByVM()){
				list.add(this.cores.get(i).getUri());
			}
		}
		return list;
	}

	/** Fonction permettant de mettre un coeur occup� par une machine virtuelle ou non
	 * @param uriCore String uri du coeur
	 * @param boolean disponibilit� du coeur
	 */
	public void setAvailableCore(String uriCore, boolean b){
		Core c;
		for(int i=0;i<this.cores.size();i++){
			c=this.cores.get(i);
			if(c.getUri()==uriCore){
				c.setUsedByVM(b);
			}
		}
	}
}
