package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Symbole;
import universite_paris8.iut.nchaieb.sae_jeux.vue.EntiteVue;

public class MonObservateurSymbole implements ListChangeListener<Symbole> {
    private StackPane stackPane;
    private EntiteVue monstreVue= new EntiteVue(stackPane);



    public MonObservateurSymbole(StackPane panneauJeu) {
        super();
        this.stackPane = panneauJeu;
    }


    @Override
    public void onChanged(Change<? extends Symbole> change) {
        while(change.next()) {
            if (change.wasAdded()) {
                for (String symbole : change.getAddedSubList()){
                    System.out.println(symbole + ": ajouté !");
                }
            }
        }
    }
}