package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class MonstreDeBase implements Monstre{
    protected int nombreDePV;
    protected int pvMax;
    protected int atq;
    protected String biome;
    private String id;
    private IntegerProperty PosX;
    private IntegerProperty PosY;
    private int vitesse;
    public static int compteurID = 0;
    private int recompenseArgent;

    public MonstreDeBase(int pvMax, int atq, String biome, int recompenseArgent, int PosX, int PosY, int vitesse){
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
        this.atq = atq;
        this.biome = biome;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
        this.recompenseArgent = recompenseArgent;
        this.PosX = new SimpleIntegerProperty(PosX);
        this.PosY = new SimpleIntegerProperty(PosY);
        this.vitesse = vitesse;
    }

    public  void infligerDegat(EntiteAllieeDeBase alliee){
        if (alliee.nombreDePV != 0){
            alliee.retirerPV(this.atq);
        }
    }


    public void ajouterPV(int soin){
        this.nombreDePV += soin;
        if (this.nombreDePV > this.pvMax){
            this.nombreDePV = this.pvMax;
        }
    }


    public void retirerPV(int degat) {
        this.nombreDePV -= degat;
        if (this.nombreDePV <= 0){
            this.nombreDePV = 0;
        }
    }

    public int getPV(){
        return this.nombreDePV;
    }

    public String getId(){
        return this.id;
    }
}
