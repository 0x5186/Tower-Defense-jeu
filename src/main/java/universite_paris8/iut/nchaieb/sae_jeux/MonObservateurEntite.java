package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.MonstreDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.vue.EntiteVue;

import javax.swing.*;

public class MonObservateurEntite implements ListChangeListener<Entite> {

    private StackPane stackPane;
    private EntiteVue monstreVue;

    public MonObservateurEntite(StackPane panneauJeu) {
        super();
        this.stackPane = panneauJeu;
        this.monstreVue = new EntiteVue(this.stackPane);
    }

    private void creerSprite(Entite entite) {
        this.monstreVue.ajouterSprite(entite);
    }





    private void enleverSprite(Entite entite) {

        this.monstreVue.animationMort(entite);

    }

    @Override
    public void onChanged(Change<? extends Entite> change) {
        System.out.println("ohh");
        while (change.next()) {
            if (change.wasAdded()) {

                for (Entite nouveau : change.getAddedSubList()) {
                    creerSprite(nouveau);
                    nouveau.getActionActuelle().addListener((observable, oldValue, newValue) -> {

                        if (newValue.equals("fixe")){

                        }
                        if(newValue.equals("marche")){

                            this.monstreVue.animationMarche(nouveau);

                        }
                        if (newValue.equals("attaque")){

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