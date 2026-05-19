package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;

public abstract class SortDeBase implements Sort {

    protected int coutSort;


    public SortDeBase(int coutSort) {
        this.coutSort = coutSort;
    }

    public abstract ArrayList<Symbole> getCombinaison();

    public boolean combinaisonValidee(ArrayList<Symbole> symboles){
        ArrayList<Symbole> combinaisonSort = getCombinaison();
        String bonneCombinaison;
        String combinaisonProposee;

        if (symboles.size() != combinaisonSort.size()){
            return false;
        }

        for (int i = 0; i < symboles.size(); i++){
            bonneCombinaison = combinaisonSort.get(i).getType();
            combinaisonProposee = symboles.get(i).getType();

            if (bonneCombinaison != combinaisonProposee){
                return false;
            }
        }

        return true;
    }

}
