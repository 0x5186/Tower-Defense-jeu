package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.vue.EntiteVue;

public class MonObservateurSymbole implements ListChangeListener<Entite> {
    private StackPane stackPane;
    private EntiteVue monstreVue= new EntiteVue(stackPane);



    public MonObservateurSymbole(StackPane panneauJeu) {
        super();
        this.stackPane = panneauJeu;
    }


    @Override
    public void onChanged(Change<? extends Entite> change) {

    }
}