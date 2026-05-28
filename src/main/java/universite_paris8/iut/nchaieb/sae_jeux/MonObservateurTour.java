package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TourVue;


public class MonObservateurTour implements ListChangeListener<Tour>{

    private StackPane stackPane;
    private TourVue tourVue;

    public MonObservateurTour(StackPane panneauJeu) {
        super();
        this.stackPane = panneauJeu;
        this.tourVue = new TourVue(this.stackPane);
    }



    private void creerSprite(Monstre monstreDeBase) {
        this.tourVue.ajouterSprite(monstreDeBase);
    }



    private void enleverSprite(Entite entite) {


    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Tour> change) {

        while (change.next()) {
            if (change.wasAdded()) {

                for (Tour nouveau : change.getAddedSubList()) {

                    TourVue.ajouterSprite(nouveau);
                    nouveau.getActionActuelle().addListener((observable, oldValue, newValue) -> {

                        if (newValue.equals("fixe")) {

                            this.tourVue.stopAnimation(nouveau);
                        }
                        if (newValue.equals("attaque")) {
                            this.tourVue.animationAttaque(nouveau);
                        }
                    });




                }
            }
            if(change.wasRemoved()) {
                System.out.println("je suis morttttttttttttt");
                for (Entite mort : change.getRemoved()) {
                    enleverSprite(mort);
                }
            }

        }
    }
}
