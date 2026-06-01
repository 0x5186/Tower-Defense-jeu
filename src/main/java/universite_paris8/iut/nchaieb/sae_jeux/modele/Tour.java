package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.ObservableList;

public class Tour extends Entite{
    protected int portee;
    protected double x, y;
    protected int atq; // ajout attribut atq

    public Tour(int portee, int atq, int x, int y) {

        this.portee = portee;
        this.atq = atq;
        this.x = x;
        this.y = y;
    }
    public void agir(ObservableList<Monstre> listeMonstre) {
        Monstre monstrePlusProche;

        if (!listeMonstre.isEmpty()) {

            monstrePlusProche = this.plusProche(listeMonstre);
            if (monstrePlusProche != null) {
                this.setActionActuelle("fixe");
                System.out.println("pas nul");
                this.infligerDegat(monstrePlusProche);
                this.setActionActuelle("attaque");
            }

        }
    }

    public boolean estDansLeRayon (Monstre monstre){
        //on va calculer la distance entre la tour et le mosntre
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());

        //on va multiplier la distance de monstre*tour(x) et monstre*tour(y)
        int distance = distanceX + distanceY;


        //on compare la distance a la porte mais on doit les mettre à unité égale
        return distance <= this.portee;
    }

    public int getPortee() {
        return portee;
    }



    public Monstre plusProche (ObservableList<Monstre> listeMonstre){

        Monstre monstrePlusProche= null;
        for(int i=0; i <listeMonstre.size(); i++){
            if(estDansLeRayon(listeMonstre.get(i))){
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



    public  void infligerDegat(Monstre monstre){

        if (monstre.nombreDePV != 0){
            monstre.retirerPV(this.atq);
            System.out.println(monstre.getPV());
        }
    }
}

