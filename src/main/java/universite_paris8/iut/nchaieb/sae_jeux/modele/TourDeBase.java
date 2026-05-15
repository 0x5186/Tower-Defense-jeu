package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;

public abstract class TourDeBase implements Tour{
    protected double portee;
    protected int atq;
    protected int x, y;

    public TourDeBase(double portee, int atq, int x, int y) {
        this.portee = portee;
        this.atq = atq;
        this.x = x;
        this.y = y;
    }

    public void infligerDegat(MonstreDeBase entite){
        if (entite.nombreDePV != 0){
            entite.retirerPV(this.atq);
        }
    }

    public boolean estDansLeRayon (MonstreDeBase monstre){
        //on va calculer la distance entre la tour et le mosntre
        int distanceX = monstre.getPosX() - this.x;
        int distanceY = monstre.getPosY() - this.y;

        //on va multiplier la distance de monstre*tour(x) et monstre*tour(y)
        int distance = (distanceX * distanceX) + (distanceY * distanceY);

        //on compare la distance a la porte mais on doit les mettre à unité égale
        if (distance <= this.portee * this.portee) {
            return true;
        }

        return false;
    }

    public void agir(ArrayList<MonstreDeBase> monstres) {
        MonstreDeBase monstreActuel;

        for (int i = 0; i < monstres.size(); i++){
            monstreActuel = monstres.get(i);
            if (estDansLeRayon(monstreActuel)){
                infligerDegat(monstreActuel);
            }
        }
    }
}

