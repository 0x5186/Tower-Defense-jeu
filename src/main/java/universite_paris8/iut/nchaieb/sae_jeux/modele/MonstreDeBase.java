package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class MonstreDeBase implements Entite {

    protected String type;
    protected int nombreDePV;
    protected int pvMax;
    protected int atq;
    private String id;
    private IntegerProperty PosX;
    private IntegerProperty PosY;
    protected int vitesse;
    public static int compteurID = 0;
    private int recompenseArgent;
    protected int actionActuel;
    protected int portee;
    private ArrayList<Noeud> cheminAEtoile;
    private int indexCheminActuel = 0;
    public static final int TAILLE_TUILE = 32;

    public void setChemin(ArrayList<Noeud> chemin){
        this.cheminAEtoile = chemin;
        this.indexCheminActuel = 0;
    }

    public MonstreDeBase(int pvMax, int atq, int PosX, int PosY, int vitesse){
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
        this.atq = atq;
        this.actionActuel=0;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
        this.PosX = new SimpleIntegerProperty(PosX);
        this.PosY = new SimpleIntegerProperty(PosY);
        this.vitesse = vitesse;
    }

    protected MonstreDeBase() {
    }

    @Override
    public void infligerDegat(MonstreDeBase entite){
        if (entite != null && entite.getPV() > 0){
            entite.retirerPV(this.atq);
        }
    }

    @Override
    public void retirerPV(int degat) {
        this.nombreDePV -= degat;
        if (this.nombreDePV < 0) {
            this.nombreDePV = 0;
        }
    }

    @Override
    public void ajouterPV(int soin){
        this.nombreDePV += soin;
        if (this.nombreDePV > this.pvMax){
            this.nombreDePV = this.pvMax;
        }
    }

    public void agir(ArrayList<MonstreDeBase> listeMonstre)  {
        MonstreDeBase monstrePlusProche = plusProche(listeMonstre);

        if(monstrePlusProche != null){
            infligerDegat(monstrePlusProche);
        }
        else {
            avancer();
        }
    }

    private void avancer() {
        if (cheminAEtoile == null || cheminAEtoile.isEmpty()) {
            System.out.println("Alerte : Le monstre " + getId() + " n'a aucun chemin possible !");
            return;
        }
        if (indexCheminActuel >= cheminAEtoile.size()) {
            System.out.println("Le monstre " + getId() + " est arrivé à destination !");
            return;
        }

        Noeud cibleActuelle = cheminAEtoile.get(indexCheminActuel);
        int pixelCibleX = cibleActuelle.x * TAILLE_TUILE;
        int pixelCibleY = cibleActuelle.y * TAILLE_TUILE;

        int posX = getPosX();
        int posY = getPosY();

        if (posX < pixelCibleX) {
            setPosX(posX + Math.min(vitesse, pixelCibleX - posX));
        } else if (posX > pixelCibleX) {
            setPosX(posX - Math.min(vitesse, posX - pixelCibleX));
        }

        if (posY < pixelCibleY) {
            setPosY(posY + Math.min(vitesse, pixelCibleY - posY));
        } else if (posY > pixelCibleY) {
            setPosY(posY - Math.min(vitesse, posY - pixelCibleY));
        }

        if (getPosX() == pixelCibleX && getPosY() == pixelCibleY) {
            indexCheminActuel++;
        }
    }

    public MonstreDeBase plusProche(ArrayList<MonstreDeBase> listeMonstre){
        MonstreDeBase monstrePlusProche = null;
        for(int i = 0; i < listeMonstre.size(); i++){
            // On ne se cible pas soi-même
            if (listeMonstre.get(i) == this) continue;

            if(estDansLeRayon(listeMonstre.get(i))){
                // CORRECTION 2 : On vérifie si monstrePlusProche est null AVANT d'appeler calculDistance dessus
                if(monstrePlusProche == null || calculDistance(listeMonstre.get(i)) < calculDistance(monstrePlusProche)){
                    monstrePlusProche = listeMonstre.get(i);
                }
            }
        }
        return monstrePlusProche;
    }

    private int calculDistance(MonstreDeBase monstre) {
        if (monstre == null) return 999999;

        int distanceX = monstre.getPosX() - this.getPosX();
        int distanceY = monstre.getPosY() - this.getPosY();

        if (distanceX < 0) distanceX = - distanceX;
        if (distanceY < 0) distanceY = - distanceY;

        return distanceX + distanceY;
    }

    public boolean estDansLeRayon (MonstreDeBase monstre){
        int distanceX = monstre.getPosX() - this.getPosX();
        int distanceY = monstre.getPosY() - this.getPosY();
        int distance = (distanceX * distanceX) + (distanceY * distanceY);

        if (distance <= this.portee * this.portee) {
            return true;
        }
        return false;
    }

    public boolean estVivant() {
        return this.nombreDePV > 0;
    }

    public int getVitesse() {
        return vitesse;
    }

    @Override
    public int getPV(){
        return this.nombreDePV;
    }

    @Override
    public String getId(){
        return this.id;
    }

    public void setPosX(int posX) {
        this.PosX.set(posX);
    }

    public void setPosY(int posY) {
        this.PosY.set(posY);
    }

    public int getPosX() { return this.PosX.get(); }

    public int getPosY() { return this.PosY.get(); }

    public int getActionActuel() {
        return actionActuel;
    }

    public String getType() {
        return type;
    }
}