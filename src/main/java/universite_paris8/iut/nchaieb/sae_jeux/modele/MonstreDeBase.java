package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public abstract class MonstreDeBase extends Entite{

    public static int compteurID = 0;

    private String id;

    protected int nombreDePV;
    protected int pvMax;
    private int atq;
//    protected String biome;

    private IntegerProperty PosX;
    private IntegerProperty PosY;
    protected int vitesse;// multiplicateur de vitesse ou pixels par sec ?
    protected int portee;

    public MonstreDeBase(int pvMax, int atq, int PosX, int PosY, int vitesse){
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;

//        this.biome = biome;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
//        this.recompenseArgent = recompenseArgent;
        this.PosX = new SimpleIntegerProperty(PosX);
        this.PosY = new SimpleIntegerProperty(PosY);
        this.vitesse = vitesse;
        this.actionActuelle.set("fixe");

    }

    public void agir(ObservableList<MonstreDeBase> listeMonstre) {
        System.out.println("j'agis");
        ArrayList<MonstreDeBase> monstreAdverse= new ArrayList<>();
        monstreAdverse=getmonstreAdverse(monstreAdverse, this.getCamp());
        if (!listeMonstre.isEmpty()) {
            MonstreDeBase monstrePlusProche = plusProche(listeMonstre);
            if (monstrePlusProche != null) {

                this.infligerDegat(monstrePlusProche);
                this.setActionActuelle("attaque");
            }
            else {


                this.avancer();
                this.setActionActuelle("marche");
            }

        }
        else {

            this.avancer();
            this.setActionActuelle("marche");
        }
    }

   private ArrayList<MonstreDeBase> getmonstreAdverse(ArrayList<MonstreDeBase> monstreAdverse, int camp) {
        ArrayList<MonstreDeBase> listeAdverse= new ArrayList<>();
        for(int i=0; i <monstreAdverse.size(); i++){
            if(monstreAdverse.get(i).getCamp()!= camp){
                listeAdverse.add(monstreAdverse.get(i));

            }
        }
        return listeAdverse;
    }

    public  void infligerDegat(MonstreDeBase monstre){

        if (monstre.nombreDePV != 0){
            monstre.retirerPV(this.atq);

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

    public MonstreDeBase plusProche(ObservableList<MonstreDeBase> listeMonstre){

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


    public void setSpawnAllie(){
        this.setPosX(700);
        this.setPosY(120);

    }
    public void setSpawnEnnemi(){
        this.setPosX(0);
        this.setPosY(120);

    }




}
