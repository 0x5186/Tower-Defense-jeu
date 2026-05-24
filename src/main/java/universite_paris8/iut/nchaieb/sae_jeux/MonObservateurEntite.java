package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.MonstreDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.vue.EntiteVue;

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
        if (entite instanceof MonstreDeBase) {
            MonstreDeBase monstre = (MonstreDeBase) entite;
            monstreVue.animationMort(monstre);

        }
    }

    @Override
    public void onChanged(Change<? extends Entite> change) {
        while (change.next()) {
            if (change.wasAdded()) {

                for (Entite nouveau : change.getAddedSubList()) {
                    creerSprite(nouveau);
                    nouveau.getActionActuelle().addListener((observable, oldValue, newValue) -> {

                        if (nouveau.getActionActuelle().equals("fixe")){

                        }
                        else if(nouveau.getActionActuelle().equals("marche")){
                            this.monstreVue.animationMarche(nouveau);

                        }
                        else if (nouveau.getActionActuelle().equals("attaque")){

                        }
                    });


                }
            }
            else {
                for (Entite mort : change.getRemoved()) {
                    this.monstreVue.animationMort(mort);
                    enleverSprite(mort);
                }
            }

        }
    }
}