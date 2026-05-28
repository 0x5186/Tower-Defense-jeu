package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Monstre;

public class Squelette extends Monstre {
    public Squelette() {
        super(1, 1, 0, 0, 1);
        this.nombreDePV=pvMax;
        this.actionActuelle.set("fixe");
        this.portee= 100;
        this.setSpawnEnnemi();
    }


}
