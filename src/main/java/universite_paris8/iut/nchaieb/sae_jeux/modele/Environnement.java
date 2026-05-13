package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Environnement {

	private int width,height;	

	private IntegerProperty nbTours;

	public Environnement(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.nbTours = new SimpleIntegerProperty();

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



}
