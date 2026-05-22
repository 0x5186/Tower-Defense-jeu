package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;

public class Environnement {
	private IntegerProperty nbTours;
	private ArrayList<MonstreDeBase> lesAlliees;
	private ArrayList<MonstreDeBase> lesMonstres;
	private Terrain terrain;

	public Environnement(Terrain terrain) {
		this.terrain = terrain;
		this.nbTours = new SimpleIntegerProperty();
		this.lesAlliees = new ArrayList<>();
		this.lesMonstres = new ArrayList<>();
		MonstreDeBase.compteurID = 0;
		EntiteAllieeDeBase.compteurID = 0;
	}

	public void ajouterEntite(MonstreDeBase entite, int camp){
		entite.setTerrain(this.terrain);
		if(camp==0){
			entite.setCamp(0);
			entite.setSpawnAllie();
			lesAlliees.add(entite);
		}
		else{
			entite.setCamp(1);
			entite.setSpawnEnnemi();
			lesMonstres.add(entite);
		}
	}

	public final IntegerProperty nbToursProperty(){ return this.nbTours; }
	public int getNbTours() { return this.nbTours.getValue(); }

	public void unTour() {
		if(!lesMonstres.isEmpty()){
			for (int i = lesMonstres.size() - 1; i >= 0; i--){
				if (!lesMonstres.get(i).estVivant()){
					lesMonstres.remove(i);
				} else {
					lesMonstres.get(i).agir(lesAlliees, lesMonstres);
				}
			}
		}
		if(!lesAlliees.isEmpty()){
			for (int i = lesAlliees.size() - 1; i >= 0; i--){
				if (!lesAlliees.get(i).estVivant()){
					lesAlliees.remove(i);
				} else {
					lesAlliees.get(i).agir(lesMonstres, lesAlliees);
				}
			}
		}
	}

	public ArrayList<MonstreDeBase> triVitesse(ArrayList<MonstreDeBase> listMonstre){
		ArrayList<MonstreDeBase> listeTrie= new ArrayList<MonstreDeBase>();
		MonstreDeBase monstre;
		int indexMax;
		for(int i=0; i< listMonstre.size(); i++){
			indexMax=i;
			for(int j=i; j< listMonstre.size(); j++){
				if(listMonstre.get(j).getVitesse()>listMonstre.get(i).getVitesse()){
					indexMax= j;
				}
			}
			listeTrie.add(listMonstre.get(indexMax));
		}
		return listMonstre;
	}

	public void avancer(MonstreDeBase monstre) { monstre.setPosX(monstre.getPosX()+1); }
	public ArrayList<MonstreDeBase> getLesAlliees() { return this.lesAlliees; }
	public ArrayList<MonstreDeBase> getLesMonstres() { return this.lesMonstres; }

	public MonstreDeBase getLesAlliees(String id) {
		for(MonstreDeBase a: this.lesAlliees){
			if(a.getId().equals(id)){ return a; }
		}
		return null;
	}

	public MonstreDeBase getLesMonstres(String id) {
		for(MonstreDeBase m: this.lesMonstres){
			if(m.getId().equals(id)){ return m; }
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