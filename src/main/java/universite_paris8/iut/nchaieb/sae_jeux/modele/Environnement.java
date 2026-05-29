package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;

import java.util.ArrayList;

public class  Environnement {

//	private int largeur,hauteur;

	private IntegerProperty nbTours;


	private ObservableList<Tour> lesTours;
	private ObservableList<Monstre> lesMonstres;


	private ObservableList<Symbole> symboles; //liste des symboles

	public Environnement() {

		this.nbTours = new SimpleIntegerProperty();
		this.lesTours =FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();
		this.symboles = FXCollections.observableArrayList();


		Monstre.compteurID = 0;
		Entite.compteurID = 0;
	}

// 	les Get :


	public ObservableList<Monstre> getLesMonstres() {
		return lesMonstres;
	}

	public ObservableList<Tour> getLesTours() {
		return this.lesTours;
	}

	public ObservableList<Symbole> getSymboles() {
		return this.symboles;
	}


//	private ObservableList<Monstre> getmonstreAdverse(ObservableList<Monstre> monstreAdverse, int camp) {
//		ObservableList<Monstre> adversaires = FXCollections.observableArrayList();
//
//		for(int i=0;i>=monstreAdverse.size(); i ++){
//			if (monstreAdverse.get(i).getCamp()!=camp){
//				monstreAdverse.add(monstreAdverse.get(i));
//			}
//
//		}
//		return monstreAdverse;
//	}



	// autres Méthodes:


	public void ajouterEntite(){

		Monstre monstre=new Squelette();

		if (monstre instanceof Monstre){

			lesMonstres.add(monstre);

		}
//		else {
//			lesTours.add(monstre);
//		}





    }

	public final IntegerProperty nbToursProperty(){
		return this.nbTours;	
	}

	public int getNbTours() {
		return this.nbTours.getValue();
	}




	public void unTour() {

		//faut les supp quand ils sont morts / sinon ils continuent d'avancer
		if(!(this.lesMonstres ==null) && !this.lesMonstres.isEmpty() ){

			for (int i = this.lesMonstres.size() -1; i >= 0; i--){
				if (this.lesMonstres.get(i).estVivant()){
					System.out.println(this.lesMonstres.get(i).getPV());

					this.lesTours.get(i).agir(this.lesMonstres);

				} else {
					System.out.println("je suis remove");
					this.lesMonstres.remove(i);



				}
			}
		}


	}

//	public ArrayList<Monstre> fusionnerListe(ArrayList<Monstre> monstreAllie, ArrayList<Monstre> monstreEnnemi){
//		ArrayList<Monstre> listeFusion=new ArrayList<Monstre>();
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


//	public ObservableList<Monstre> triVitesse(ObservableList<Monstre> listMonstre){
//		ArrayList<Monstre> listeTrie= new ArrayList<Monstre>();
//		Monstre monstre;
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




	public void avancer(Monstre monstre) {
		monstre.setPosX(monstre.getPosX()+1);
	}

	//ce que j'ai rajouté(musa le japonais)


	public ArrayList<Monstre> voirLesMonstresElimines(){
		ArrayList<Monstre> historiqueDeKill = new ArrayList<>();
		Monstre entiteActuel;
		for (int i = 0; i < this.lesMonstres.size(); i++){
			entiteActuel = lesMonstres.get(i);
			historiqueDeKill.add(entiteActuel);
			this.lesMonstres.remove(i);
		}
		return historiqueDeKill;
	}
}
