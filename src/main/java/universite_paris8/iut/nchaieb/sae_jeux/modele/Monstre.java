package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;

public abstract class Monstre extends Entite{

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



    public Monstre(int pvMax, int atq, int PosX, int PosY, int vitesse){

        this.atq=atq;
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;

//        this.biome = biome;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
//        this.recompenseArgent = recompenseArgent;
        this.vitesse = vitesse;
        this.actionActuelle.set("fixe");

    }

//    public void agir(ObservableList<Monstre> listeMonstre) {
//        Monstre monstrePlusProche;
//        ArrayList<Monstre> monstreAdverse;
//        monstreAdverse=getmonstreAdverse(listeMonstre, this.camp);
//        if (!listeMonstre.isEmpty()) {
//
//             monstrePlusProche = this.plusProche(monstreAdverse);
//            if (monstrePlusProche != null) {
//                this.setActionActuelle("fixe");
//                System.out.println("pas nul");
//
//                this.infligerDegat(monstrePlusProche);
//                this.setActionActuelle("attaque");
//            }
//            else {
//
//
//                this.avancer();
//                this.setActionActuelle("fixe");
//                this.setActionActuelle("marche");
//
//            }
//
////        }
//        else {
//
//            this.avancer();
//            this.setActionActuelle("fixe");
//            this.setActionActuelle("marche");
//        }
//    }

//   private ArrayList<Monstre> getmonstreAdverse(ObservableList<Monstre> monstreAdverse, int camp) {
//        ArrayList<Monstre> listeAdverse= new ArrayList<>();
//        for(int i=0; i <monstreAdverse.size(); i++){
//            if(monstreAdverse.get(i).camp != this.camp){
//                listeAdverse.add(monstreAdverse.get(i));
//
//            }
//        }
//        return listeAdverse;
//    }

    public  void infligerDegat(Monstre monstre){

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
        setPosX(getPosX()+25);

    }

    public Monstre plusProche(ArrayList<Monstre> listeMonstre){

        Monstre monstrePlusProche= null;
        for(int i=0; i <listeMonstre.size(); i++){
            if(this.estDansLeRayon(listeMonstre.get(i))){
                if( monstrePlusProche==null || calculDistance(listeMonstre.get(i))<calculDistance(monstrePlusProche)){
                    monstrePlusProche=listeMonstre.get(i);

                }
            }
        }
        return monstrePlusProche;

    }

    private int calculDistance(Monstre monstre) {
        int distance = (monstre.getPosX()+ monstre.getPosY())-(getPosY()+getPosX());
        if (distance<0)
            distance=distance*-1;
        return distance;
    }

    public boolean estDansLeRayon (Monstre monstre){
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


    public void setSpawnAllie(){
        this.setPosX(700);
        this.setPosY(120);

    }
    public void setSpawnEnnemi(){
        this.setPosX(0);
        this.setPosY(120);

    }


}
