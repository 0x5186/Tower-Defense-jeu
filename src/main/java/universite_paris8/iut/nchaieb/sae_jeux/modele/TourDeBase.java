package universite_paris8.iut.nchaieb.sae_jeux.modele;

public abstract class TourDeBase implements Tour{
    protected double portee;
    protected int atq;

    public TourDeBase(double portee, int atq) {
        this.portee = portee;
        this.atq = atq;
    }

    public void infligerDegat(MonstreDeBase entite){
        if (entite.nombreDePV != 0){
            entite.retirerPV(this.atq);
        }
    }

    public boolean estDansLeRayon (){
        /*
            Si un monstre se trouve à la portée de la tour
            alors true.
         */
        return false;
    }
}

