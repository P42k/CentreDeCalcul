package components;

import java.util.ArrayList;

import fr.upmc.components.AbstractComponent;

public class Application extends AbstractComponent{
	private int idApplication;
	private RequestRepartitor rr;
	private PerformanceController pc; 
	private ArrayList<String> uriMV;
	
	public Application(String id, ArrayList<String> uriMV){
		super(true,true);
		//création de repartiteur( uriMV)
		//this.innercomponents.add(repartiteur)
		//this.innercomponents.add(controleurPerformance)
		
	}
	
}
