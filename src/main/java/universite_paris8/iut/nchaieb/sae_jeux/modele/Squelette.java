package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Squelette extends MonstreDeBase{
    public Squelette(){

        this.atq=1;
        this.pvMax=1;
        this.nombreDePV=pvMax;
        this.actionActuel=1;

    }

    @Override
    public void infligerDegat(MonstreDeBase entite) {

    }

    @Override
    public void retirerPV(int degat) {

    }

}
