package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class  Environnement {

//	private int largeur,hauteur;

	private IntegerProperty nbTours;


	private ObservableList<MonstreDeBase> lesTours;  //TODO en faire une observableList
	private ObservableList<MonstreDeBase> lesMonstres;

	public Environnement() {

		this.nbTours = new SimpleIntegerProperty();
		this.lesTours =FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();

		//à voir, pour l'instant
		MonstreDeBase.compteurID = 0;
		Entite.compteurID = 0;
	}

// 	les Get :

	public ObservableList<MonstreDeBase> getLesTours() {
		return this.lesTours;
	}
	public ObservableList<MonstreDeBase> getLesMonstres() { return this.lesMonstres; }

	private ArrayList<MonstreDeBase> getmonstreAdverse(ArrayList<MonstreDeBase> monstreAdverse, int camp) {
		for(int i=monstreAdverse.size();i>=0; i --){
			if (monstreAdverse.get(i).getCamp()!=camp){
				monstreAdverse.remove(i);
			}

		}
		return monstreAdverse;
	}



	// autres Méthodes:


	public void ajouterEntite(MonstreDeBase entite, int camp){

		entite.setCamp(1);
		entite.setSpawnEnnemi();
		lesMonstres.add(entite);





    }

	public final IntegerProperty nbToursProperty(){
		return this.nbTours;	
	}

	public int getNbTours() {
		return this.nbTours.getValue();
	}




	public void unTour() {

		//faut les supp quand ils sont morts / sinon avance
		if(!(lesMonstres ==null) && !lesMonstres.isEmpty() ){
			for (int i = lesMonstres.size() - 1; i >= 0; i--){
				if (lesMonstres.get(i).estVivant()){

					lesMonstres.get(i).agir((ObservableList<MonstreDeBase>) lesMonstres);

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
