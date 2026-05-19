package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class MonstreDeBase implements Entite{

    protected String type;
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
    protected int actionActuel; //0=rien 1=fixe 2=avance  3=frappe 4=meurt
    protected int portee;

    public MonstreDeBase(int pvMax, int atq, int PosX, int PosY, int vitesse){
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
        this.atq = atq;
        this.actionActuel=0;

//        this.biome = biome;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
//        this.recompenseArgent = recompenseArgent;
        this.PosX = new SimpleIntegerProperty(PosX);
        this.PosY = new SimpleIntegerProperty(PosY);
        this.vitesse = vitesse;
    }

    protected MonstreDeBase() {
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

    public void agir(ArrayList<MonstreDeBase> listeMonstre)  {
        MonstreDeBase monstrePlusProche= plusProche(listeMonstre);

        if(monstrePlusProche!=null){
            infligerDegat((monstrePlusProche));
        }
        else{
            avancer();
        }

//        MonstreDeBase monstreAdverse= null;
//        for(int i=0; i <listeMonstre.size(); i++){
//            if(listeMonstre.get(i).estDansLeRayon()){
//                if()
//            }
//        }

        


    }

    private void avancer() {
        setPosX(getPosX()+25);
    }

    public MonstreDeBase plusProche(ArrayList<MonstreDeBase> listeMonstre){
        MonstreDeBase monstrePlusProche= null;
        for(int i=0; i <listeMonstre.size(); i++){
            if(listeMonstre.get(i).estDansLeRayon(listeMonstre.get(i))){
                if(calculDistance(listeMonstre.get(i))<calculDistance(monstrePlusProche) || monstrePlusProche==null){
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
        int distanceX = monstre.getPosX() - this.getPosX();
        int distanceY = monstre.getPosY() - this.getPosY();

        //on va multiplier la distance de monstre*tour(x) et monstre*tour(y)
        int distance = (distanceX * distanceX) + (distanceY * distanceY);

        //on compare la distance a la porte mais on doit les mettre à unité égale
        if (distance <= this.portee * this.portee) {
            return true;
        }

        return false;
    }

    public boolean estVivant() {
        if (this.nombreDePV != 0){
            return true;
        }
        return false;
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

    public int getPosX() { return this.PosX.get(); }

    public int getPosY() { return this.PosY.get(); }

    public int getActionActuel() {
        return actionActuel;
    }

    public String getType() {
        return type;
    }
}
