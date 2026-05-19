package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Squelette extends MonstreDeBase{
    public Squelette() {
        super(1, 1, 0, 300, 1);
        this.actionActuel = 1;
        this.type = "squelette";
        this.portee= 2;
    }

    @Override
    public void infligerDegat(MonstreDeBase entite) {

    }

    @Override
    public void retirerPV(int degat) {

    }


}
