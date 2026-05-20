package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;

import java.util.ArrayList;

public class  Environnement {

//	private int largeur,hauteur;

	private IntegerProperty nbTours;

	//ce que j'ai rajouté(musa le japonais)
	//t pas japonais heee heee
	private ArrayList<MonstreDeBase> lesAlliees;
	private ArrayList<MonstreDeBase> lesMonstres;

	public Environnement() {
		this.nbTours = new SimpleIntegerProperty();
		//ce que j'ai rajouté(musa le japonais)
		this.lesAlliees = new ArrayList<>();
		this.lesMonstres = new ArrayList<>();

		//à voir, pour l'instant
		MonstreDeBase.compteurID = 0;
		EntiteAllieeDeBase.compteurID = 0;
	}

	public void ajouterEntite(MonstreDeBase entite, int camp){
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

	public final IntegerProperty nbToursProperty(){
		return this.nbTours;	
	}

	public int getNbTours() {
		return this.nbTours.getValue();
	}




	public void unTour() {

		//faut les supp quand ils sont morts / sinon avance
		if(!lesMonstres.isEmpty()){
			for (int i = lesMonstres.size() - 1; i >= 0; i--){
				if (!lesMonstres.get(i).estVivant()){
					lesMonstres.remove(i);

				} else {
					System.out.println("Action Monstre");
					lesMonstres.get(i).agir(lesAlliees);
				}
			}
		}
		if(!lesAlliees.isEmpty()){

			for (int i = lesAlliees.size() - 1; i >= 0; i--){
				if (!lesAlliees.get(i).estVivant()){
					lesAlliees.remove(i);

				} else {
					System.out.println("Action Allié");
					lesAlliees.get(i).agir(lesMonstres);
				}
			}
		}

	}

//	public ArrayList<MonstreDeBase> fusionnerListe(ArrayList<MonstreDeBase> monstreAllie, ArrayList<MonstreDeBase> monstreEnnemi){
//		ArrayList<MonstreDeBase> listeFusion=new ArrayList<MonstreDeBase>();
//		for(int i=0; i<monstreAllie.size()+monstreEnnemi.size(); i++){
//			if(i>= monstreAllie.size()){
//				listeFusion.add(monstreEnnemi.get(i));
//			}
//			else{
//				listeFusion.add(monstreAllie.get(i));
//			}
//		}
//		return listeFusion;
//	}


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




	public void avancer(MonstreDeBase monstre) {
		monstre.setPosX(monstre.getPosX()+1);
	}

	//ce que j'ai rajouté(musa le japonais)

	public ArrayList<MonstreDeBase> getLesAlliees() {
		return this.lesAlliees;
	}
	public ArrayList<MonstreDeBase> getLesMonstres() { return this.lesMonstres; }

	public MonstreDeBase getLesAlliees(String id) {
		for(MonstreDeBase a: this.lesAlliees){
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
