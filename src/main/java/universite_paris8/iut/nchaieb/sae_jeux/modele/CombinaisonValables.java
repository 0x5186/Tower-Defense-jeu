package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class CombinaisonValables {
    protected ArrayList<String> tourOeil;


    public CombinaisonValables() {
        this.tourOeil = new ArrayList<String>(List.of("oeil", "croix", "eclipse"));
    }


}
