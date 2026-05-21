package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;

public abstract class SortDeBase implements Sort   {

    protected int coutSort;


    public SortDeBase(int coutSort) {
        this.coutSort = coutSort;
    }

    public abstract ArrayList<Symbole> getCombinaison();

    public boolean combinaisonValidee(ArrayList<Symbole> symboles){
        ArrayList<Symbole> combinaisonDuSort = getCombinaison();
        String bonneCombinaison;
        String combinaisonComposee;

        if (symboles.size() != combinaisonDuSort.size()){
            System.out.println("Pas la même longueur");
            return false;
        }

        for (int i = 0; i < symboles.size(); i++){
            bonneCombinaison = combinaisonDuSort.get(i).getType();
            combinaisonComposee = symboles.get(i).getType();

            if (!bonneCombinaison.equals(combinaisonComposee)){
                System.out.println("pas égale");
                return false;
            }
        }
        return true;
    }

    public abstract void invoquerSort();

}
