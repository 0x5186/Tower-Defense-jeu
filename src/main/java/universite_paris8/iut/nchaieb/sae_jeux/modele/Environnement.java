package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Environnement {

	private IntegerProperty nbTours;

	private Terrain terrain;
	private ArrayList<MonstreDeBase> lesAlliees;
	private ArrayList<MonstreDeBase> lesMonstres;

	public Environnement(Terrain terrain) {
		this.terrain = terrain;
		this.nbTours = new SimpleIntegerProperty();
		this.lesAlliees = new ArrayList<>();
		this.lesMonstres = new ArrayList<>();

		MonstreDeBase.compteurID = 0;
		EntiteAllieeDeBase.compteurID = 0;
	}

	// --- MODIFICATION : Calcul du chemin lors de l'ajout d'une entité ---
	public void ajouterEntite(MonstreDeBase entite) {
		// 1. On calcule la case de la grille où se trouve le monstre d'après ses pixels de spawn
		int departX = entite.getPosX() / MonstreDeBase.TAILLE_TUILE;
		int departY = entite.getPosY() / MonstreDeBase.TAILLE_TUILE;

		// 2. Coordonnée cible demandée : point le plus à droite (colonne 47) et au centre (ligne 12)
		int cibleX = 47;
		int cibleY = 12;

		// 3. On calcule le chemin et on l'associe au monstre
		if (this.terrain != null) {
			ArrayList<Noeud> chemin = AlgorithmeAEtoile.trouverChemin(this.terrain, departX, departY, cibleX, cibleY);
			entite.setChemin(chemin);
		}

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
		if(!lesMonstres.isEmpty()){
			for (int i = lesMonstres.size() - 1; i >= 0; i--){
				if (!lesMonstres.get(i).estVivant()){
					lesMonstres.remove(i);

				} else {
					lesMonstres.get(i).agir(lesAlliees);
				}
			}
		}
		if(!lesAlliees.isEmpty()){
			for (int i = lesAlliees.size() - 1; i >= 0; i--){
				if (!lesAlliees.get(i).estVivant()){
					lesAlliees.remove(i);

				} else {
					lesAlliees.get(i).agir(lesMonstres);
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

	public void avancer(MonstreDeBase monstre) {
		monstre.setPosX(monstre.getPosX()+1);
	}

	public ArrayList<MonstreDeBase> getLesAlliees() {
		return this.lesAlliees;
	}

	public ArrayList<MonstreDeBase> getLesMonstres() {
		return this.lesMonstres;
	}

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