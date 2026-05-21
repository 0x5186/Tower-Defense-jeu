package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class MonstreDeBase extends EntiteAllieeDeBase{


    protected int nombreDePV;
    protected int pvMax;
    protected int atq;
//    protected String biome;
    private String id;
    private IntegerProperty PosX;
    private IntegerProperty PosY;
    protected int vitesse;// multiplicateur de vitesse ou pixels par sec ?
    public static int compteurID = 0;
    private int recompenseArgent;
    protected boolean attaque; //0=rien 1=fixe 2=avance  3=frappe 4=meurt
    protected int portee;
    private int camp;

    public MonstreDeBase(int pvMax, int atq, int PosX, int PosY, int vitesse){
        super(atq);
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
        this.atq = atq;
        this.attaque=false;

//        this.biome = biome;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
//        this.recompenseArgent = recompenseArgent;
        this.PosX = new SimpleIntegerProperty(PosX);
        this.PosY = new SimpleIntegerProperty(PosY);
        this.vitesse = vitesse;

    }

    public void agir(ArrayList<MonstreDeBase> listeMonstre) {
        if (!listeMonstre.isEmpty()) {
            MonstreDeBase monstrePlusProche = plusProche(listeMonstre);
            if (monstrePlusProche != null) {
                this.setAttaque(true);
                this.infligerDegat(monstrePlusProche);
            }
            else {

                this.setAttaque(false);
                this.avancer();
            }

        }
        else {
            this.setAttaque(false);
            this.avancer();
        }
    }
    @Override
    public  void infligerDegat(EntiteAllieeDeBase monstre){
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


    public int getPortee() {
        return portee;
    }

    private void avancer() {
        if(this.getCamp()==0){
            setPosX(getPosX()-25);

        }
        else{
            setPosX(getPosX()+25);

        }

    }

    public MonstreDeBase plusProche(ArrayList<MonstreDeBase> listeMonstre){

        MonstreDeBase monstrePlusProche= null;
        for(int i=0; i <listeMonstre.size(); i++){
            if(this.estDansLeRayon(listeMonstre.get(i))){
                if( monstrePlusProche==null || calculDistance(listeMonstre.get(i))<calculDistance(monstrePlusProche)){
                    monstrePlusProche=listeMonstre.get(i);

                }
            }
        }
        return monstrePlusProche;

    }

    private int calculDistance(MonstreDeBase monstre) {
        int distance = (monstre.getPosX()+ monstre.getPosY())-(getPosY()+getPosX());
        if (distance<0)
            distance=distance*-1;
        return distance;
    }

    public boolean estDansLeRayon (MonstreDeBase monstre){
        //on va calculer la distance entre la tour et le mosntre
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());

        //on va multiplier la distance de monstre*tour(x) et monstre*tour(y)
        int distance = distanceX+distanceY;


        //on compare la distance a la porte mais on doit les mettre à unité égale
        if (distance <= this.portee) {
            return true;
        }

        return false;
    }

    public boolean estVivant() {
//        if(this.nombreDePV==0){
//            setActionActuel(0);
//        }

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

    public int getPV(){
        return this.nombreDePV;
    }

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

    public boolean getAttaque() {
        return this.attaque;
    }

    public void setAttaque(boolean attaque) {
        this.attaque = attaque;
    }

    public void setSpawnAllie(){
        this.setPosX(700);
        this.setPosY(120);

    }
    public void setSpawnEnnemi(){
        this.setPosX(0);
        this.setPosY(120);

    }




}
