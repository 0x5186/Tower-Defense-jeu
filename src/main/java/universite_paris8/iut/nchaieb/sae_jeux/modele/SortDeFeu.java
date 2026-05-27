package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;

public class SortDeFeu extends SortDeBase {

    private final ArrayList<Symbole> combinaison;

    public SortDeFeu(){
        super(30);
        this.combinaison = new ArrayList<>();
        Symbole symboleCroix = new Symbole("Croix");
        this.combinaison.add(symboleCroix);
        Symbole symboleGoutteDeau = new Symbole("Goutte");
        this.combinaison.add(symboleGoutteDeau);
        Symbole symboleSpirale = new Symbole("Spirale");
        this.combinaison.add(symboleSpirale);
    }

    @Override
    public ArrayList<Symbole> getCombinaison() { return this.combinaison; }

    @Override
    public boolean combinaisonValidee(){
        return true;
    }

    public void invoquerSort(){
        System.out.println("BOOMBOOOOOSSSCCLAT un sort est lancé !!");
    }
}


