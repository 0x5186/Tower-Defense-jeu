package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entite implements EntiteInterface{

    //    protected String biome;
    private String id;
    public static int compteurID = 0;


    private IntegerProperty PosX;
    private IntegerProperty PosY;


    protected StringProperty actionActuelle= new SimpleStringProperty();


    public Entite(){

        this.PosX = new SimpleIntegerProperty();
        this.PosY = new SimpleIntegerProperty();
//        this.biome = biome;
        this.id ="E"+ this.compteurID;
        this.compteurID++;
        this.actionActuelle.set("fixe");


    }

    public String getId(){
        return this.id;
    }

    public int getPosX() {
        return PosX.get();
    }

    public IntegerProperty posXProperty() {
        return PosX;
    }

    public int getPosY() {
        return PosY.get();
    }

    public IntegerProperty posYProperty() {
        return PosY;
    }


    public void setPosY(int posY) {
        this.PosY.set(posY);
    }

    public void setPosX(int posX) {
        this.PosX.set(posX);
    }



    public StringProperty getActionActuelle() {
        return actionActuelle;
    }


    public void setActionActuelle(String actionActuelle) {
        this.actionActuelle.set(actionActuelle);


    }
}
