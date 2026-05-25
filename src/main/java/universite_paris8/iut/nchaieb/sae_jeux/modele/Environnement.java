package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.MonObservateurEntite;

import java.util.ArrayList;

public class  Environnement {

//	private int largeur,hauteur;

	private IntegerProperty nbTours;



	private ObservableList<Entite> entites;
	private ObservableList<Tour> lesTours;
	private ObservableList<MonstreDeBase> lesMonstres;


	public Environnement() {

		this.nbTours = new SimpleIntegerProperty();


		this.entites =FXCollections.observableArrayList();

		this.lesTours =FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();


		MonstreDeBase.compteurID = 0;
		Entite.compteurID = 0;
	}

// 	les Get :


	public ObservableList<Entite> getEntites() {

		return entites;
	}

	public ObservableList<MonstreDeBase> getLesMonstres() {
		return lesMonstres;
	}

	public ObservableList<Tour> getLesTours() {
		return this.lesTours;
	}
	public ObservableList<MonstreDeBase> triLesMonstres() {
		lesMonstres.clear();
		MonstreDeBase monstre;
		for(int i=0; i<this.entites.size(); i++){
			if (entites.get(i) instanceof MonstreDeBase){
				monstre = (MonstreDeBase) entites.get(i);
				lesMonstres.add(monstre);


			}
		}


		return this.lesMonstres;
	}

	private ObservableList<MonstreDeBase> getmonstreAdverse(ObservableList<MonstreDeBase> monstreAdverse, int camp) {
		ObservableList<MonstreDeBase> adversaires = FXCollections.observableArrayList();

		for(int i=0;i>=monstreAdverse.size(); i ++){
			if (monstreAdverse.get(i).getCamp()!=camp){
				monstreAdverse.add(monstreAdverse.get(i));
			}

		}
		return monstreAdverse;
	}



	// autres Méthodes:


	public void ajouterEntite(MonstreDeBase entite, int camp){
		entite.setCamp(camp);

		if(camp==0) {entite.setSpawnAllie();}

		else { entite.setSpawnEnnemi();}

		entites.add(entite);
		if (entite instanceof MonstreDeBase){

			lesMonstres.add(entite);

		}
//		else {
//			lesTours.add(entite);
//		}





    }

	public final IntegerProperty nbToursProperty(){
		return this.nbTours;	
	}

	public int getNbTours() {
		return this.nbTours.getValue();
	}




	public void unTour() {
		System.out.println("untour");
		//faut les supp quand ils sont morts / sinon ils continuent d'avancer
		if(!(lesMonstres ==null) && !lesMonstres.isEmpty() ){
			System.out.println("dans if");
			for (int i = lesMonstres.size() - 1; i >= 0; i--){
				if (lesMonstres.get(i).estVivant()){

					lesMonstres.get(i).agir(getmonstreAdverse(lesMonstres, lesMonstres.get(i).getCamp()));

				} else {
					lesMonstres.remove(i);


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


//	public ObservableList<MonstreDeBase> triVitesse(ObservableList<MonstreDeBase> listMonstre){
//		ArrayList<MonstreDeBase> listeTrie= new ArrayList<MonstreDeBase>();
//		MonstreDeBase monstre;
//		int indexMax;
//		for(int i=0; i< listMonstre.size(); i++){
//			indexMax=i;
//			for(int j=i; j< listMonstre.size(); j++){
//				if(listMonstre.get(j).getVitesse()>listMonstre.get(i).getVitesse()){
//					indexMax= j;
//				}
//
//			}
//			listeTrie.add(listMonstre.get(indexMax));
//
//		}
//		return listMonstre;
//	}




	public void avancer(MonstreDeBase monstre) {
		monstre.setPosX(monstre.getPosX()+1);
	}

	//ce que j'ai rajouté(musa le japonais)


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
