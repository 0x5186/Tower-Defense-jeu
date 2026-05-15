package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class  Environnement {

	private int width,height;	

	private IntegerProperty nbTours;

	//ce que j'ai rajouté(musa le japonais)
	private ArrayList<EntiteAllieeDeBase> lesAlliees;
	private ArrayList<MonstreDeBase> lesMonstres;

	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.nbTours = new SimpleIntegerProperty();
		//ce que j'ai rajouté(musa le japonais)
		this.lesAlliees = new ArrayList<>();
		this.lesMonstres = new ArrayList<>();

	}

	public final IntegerProperty nbToursProperty(){
		return this.nbTours;	
	}

	public int getNbTours() {
		return this.nbTours.getValue();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	



	//ce que j'ai rajouté(musa le japonais)

	public ArrayList<EntiteAllieeDeBase> getLesAlliees() {
		return this.lesAlliees;
	}
	public ArrayList<MonstreDeBase> getLesMonstres() { return this.lesMonstres; }

	public EntiteAllieeDeBase getLesAlliees(String id) {
		for(EntiteAllieeDeBase a: this.lesAlliees){
			if(a.getId().equals(id)){
				return a;
			}
		}
		return null;
	}

	public MonstreDeBase getLesMonstres(String id) {
		for(MonstreDeBase m: this.lesMonstres){
			if(m.getId().equals(id)){
				return m;
			}
		}
		return null;
	}

	public ArrayList<MonstreDeBase> voirLesMonstresElimines(){
		ArrayList<MonstreDeBase> historiqueDeKill = new ArrayList<>();
		MonstreDeBase entiteActuel;
		for (int i = 0; i < this.lesMonstres.size(); i++){
			entiteActuel = lesMonstres.get(i);
			historiqueDeKill.add(entiteActuel);
			this.lesMonstres.remove(i);
		}
		return historiqueDeKill;
	}
}
