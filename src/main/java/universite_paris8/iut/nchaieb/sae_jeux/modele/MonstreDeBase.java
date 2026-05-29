package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;

public abstract class MonstreDeBase extends EntiteAllieeDeBase {
    protected int nombreDePV;
    protected int pvMax;
    protected int atq;
    private String id;
    private IntegerProperty PosX;
    private IntegerProperty PosY;
    protected int vitesse;
    public static int compteurID = 0;
    private int recompenseArgent;
    protected boolean attaque;
    protected int portee;
    private int camp;

    private Terrain terrain;
    private ArrayList<Noeud> chemin;

    private final int TAILLE_TUILE = 16;

    private int targetX;
    private int targetY;

    public MonstreDeBase(int pvMax, int atq, int PosX, int PosY, int vitesse) {
        super(atq);
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
        this.atq = atq;
        this.attaque = false;
        this.id = "M" + this.compteurID;
        this.compteurID++;
        this.PosX = new SimpleIntegerProperty(PosX);
        this.PosY = new SimpleIntegerProperty(PosY);
        this.vitesse = vitesse;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setSpawnAllie() {
        this.setPosX((this.terrain.largeur() - 1) * TAILLE_TUILE);
        this.setPosY(26 * TAILLE_TUILE);

        int portailAleatoire = (int) (Math.random() * 3);
        if (portailAleatoire == 0) {
            this.targetX = 0; this.targetY = 8;
        } else if (portailAleatoire == 1) {
            this.targetX = 10; this.targetY = 51;
        } else {
            this.targetX = 50; this.targetY = 0;
        }
    }

    public void setSpawnEnnemi() {
        int portailAleatoire = (int) (Math.random() * 3);

        if (portailAleatoire == 0) { // Haut-Gauche
            this.setPosX(0);
            this.setPosY(8 * TAILLE_TUILE);
        } else if (portailAleatoire == 1) { // Bas-Gauche
            this.setPosX(10 * TAILLE_TUILE);
            this.setPosY(51 * TAILLE_TUILE);
        } else { // Haut-Milieu
            this.setPosX(50 * TAILLE_TUILE);
            this.setPosY(0);
        }

        // Base
        this.targetX = this.terrain.largeur() - 1;
        this.targetY = 26;
    }

    public void agir(ArrayList<MonstreDeBase> ennemis, ArrayList<MonstreDeBase> collegues) {
        if (!ennemis.isEmpty()) {
            MonstreDeBase monstrePlusProche = plusProche(ennemis);
            if (monstrePlusProche != null) {
                this.setAttaque(true);
                this.infligerDegat(monstrePlusProche);
                return;
            }
        }

        this.setAttaque(false);
        if (!estBloqueParAllie(collegues)) {
            this.avancer();
        }
    }

    private boolean estBloqueParAllie(ArrayList<MonstreDeBase> collegues) {
        for (MonstreDeBase collegue : collegues) {
            if (collegue != this && collegue.estVivant()) {
                int distanceX = Math.abs(collegue.getPosX() - this.getPosX());
                int distanceY = Math.abs(collegue.getPosY() - this.getPosY());

                if ((distanceX + distanceY) < 25) {
                    if (this.getCamp() == 1 && collegue.getPosX() > this.getPosX()) return true;
                    if (this.getCamp() == 0 && collegue.getPosX() < this.getPosX()) return true;

                    if (collegue.getPosX() == this.getPosX() && collegue.getPosY() == this.getPosY()) {
                        if (this.hashCode() > collegue.hashCode()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void infligerDegat(EntiteAllieeDeBase monstre) {}

    public void ajouterPV(int soin) {
        this.nombreDePV += soin;
        if (this.nombreDePV > this.pvMax) {
            this.nombreDePV = this.pvMax;
        }
    }

    public void retirerPV(int degat) {
        this.nombreDePV -= degat;
        if (this.nombreDePV <= 0) {
            this.nombreDePV = 0;
        }
    }

    public int getPortee() {
        return portee;
    }

    private void avancer() {
        if (this.terrain == null) return;

        if (this.chemin == null || this.chemin.isEmpty()) {
            int departGridX = this.getPosX() / TAILLE_TUILE;
            int departGridY = this.getPosY() / TAILLE_TUILE;

            this.chemin = AlgorithmeAEtoile.trouverChemin(terrain, departGridX, departGridY, this.targetX, this.targetY);

            if (this.chemin == null || this.chemin.isEmpty()) return;
        }

        Noeud prochaineEtape = this.chemin.get(0);

        int ciblePixelX = prochaineEtape.x * TAILLE_TUILE;
        int ciblePixelY = prochaineEtape.y * TAILLE_TUILE;

        int dx = ciblePixelX - this.getPosX();
        int dy = ciblePixelY - this.getPosY();

        int deplacementX = 0;
        int deplacementY = 0;

        if (dx > 0) deplacementX = Math.min(this.vitesse, dx);
        else if (dx < 0) deplacementX = Math.max(-this.vitesse, dx);

        if (dy > 0) deplacementY = Math.min(this.vitesse, dy);
        else if (dy < 0) deplacementY = Math.max(-this.vitesse, dy);

        this.setPosX(this.getPosX() + deplacementX);
        this.setPosY(this.getPosY() + deplacementY);

        if (this.getPosX() == ciblePixelX && this.getPosY() == ciblePixelY) {
            this.chemin.remove(0);
        }
    }

    public MonstreDeBase plusProche(ArrayList<MonstreDeBase> listeMonstre) {
        MonstreDeBase monstrePlusProche = null;
        for (int i = 0; i < listeMonstre.size(); i++) {
            if (this.estDansLeRayon(listeMonstre.get(i))) {
                if (monstrePlusProche == null || calculDistance(listeMonstre.get(i)) < calculDistance(monstrePlusProche)) {
                    monstrePlusProche = listeMonstre.get(i);
                }
            }
        }
        return monstrePlusProche;
    }

    private int calculDistance(MonstreDeBase monstre) {
        int distance = (monstre.getPosX() + monstre.getPosY()) - (getPosY() + getPosX());
        if (distance < 0) distance = distance * -1;
        return distance;
    }

    public boolean estDansLeRayon(MonstreDeBase monstre) {
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());
        int distance = distanceX + distanceY;
        return distance <= this.portee;
    }

    public boolean estVivant() {
        return this.nombreDePV > 0;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getPV() {
        return this.nombreDePV;
    }

    public String getId() {
        return this.id;
    }

    public void setPosX(int posX) {
        this.PosX.set(posX);
    }

    public void setPosY(int posY) {
        this.PosY.set(posY);
    }

    public int getPosX() {
        return this.PosX.get();
    }

    public int getPosY() {
        return this.PosY.get();
    }

    public IntegerProperty getPosXProperty() {
        return this.PosX;
    }

    public IntegerProperty getPosYProperty() {
        return this.PosY;
    }

    public boolean getAttaque() {
        return this.attaque;
    }

    public void setAttaque(boolean attaque) {
        this.attaque = attaque;
    }
}