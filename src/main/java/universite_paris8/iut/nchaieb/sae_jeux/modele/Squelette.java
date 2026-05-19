package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Squelette extends Monstre {
    public Squelette() {
        super(1, 1, 0, 300, 1);
        this.actionActuel = 1;
        this.type = "squelette";
    }

    @Override
    public void infligerDegat(Monstre entite) {

    }

    @Override
    public void retirerPV(int degat) {

    }


}
