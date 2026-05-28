package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.MonstreDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.vue.EntiteVue;

public class MonObservateurEntite implements ListChangeListener<Entite> {

    private StackPane stackPane;
    private EntiteVue monstreVue;

    public MonObservateurEntite(StackPane panneauJeu) {
        super();
        this.stackPane = panneauJeu;
        this.monstreVue = new EntiteVue(this.stackPane);
    }



    private void creerSprite(MonstreDeBase monstreDeBase) {
        this.monstreVue.ajouterSprite(monstreDeBase);
    }



    private void enleverSprite(Entite entite) {

        this.monstreVue.animationMort(entite);

    }

    @Override
    public void onChanged(Change<? extends Entite> change) {

        while (change.next()) {
            if (change.wasAdded()) {

                for (Entite nouveau : change.getAddedSubList()) {
                    if (nouveau instanceof MonstreDeBase) {
                        creerSprite((MonstreDeBase) nouveau);
                        nouveau.getActionActuelle().addListener((observable, oldValue, newValue) -> {

                            if (newValue.equals("fixe")) {

                                this.monstreVue.stopAnimation(nouveau);
                            }
                            if (newValue.equals("marche")) {

                                this.monstreVue.animationMarche(nouveau);

                            }
                            if (newValue.equals("attaque")) {
                                this.monstreVue.animationAttaque(nouveau);
                            }
                        });
                    }
                    //else


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