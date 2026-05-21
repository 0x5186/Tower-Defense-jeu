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
    private EntiteVue monstreVue; // On ne l'initialise pas ici sinon stackPane est null !

    public MonObservateurEntite(StackPane panneauJeu) {
        super();
        this.stackPane = panneauJeu;
        // On l'initialise ici, maintenant que stackPane a reçu sa valeur
        this.monstreVue = new EntiteVue(this.stackPane);
    }

    private void creerSprite(Entite entite) {
        if (entite instanceof Squelette) {
            System.out.println("111111");
            if (entite instanceof MonstreDeBase) {
                MonstreDeBase monstre = (MonstreDeBase) entite;
                monstreVue.animationMarche(monstre);
            }
        }
    }

    private void enleverSprite(Entite mort) {
        // Ta logique pour retirer le sprite de la vue quand un monstre meurt
    }

    @Override
    public void onChanged(Change<? extends Entite> change) {
        while (change.next()) {
            // Gestion des ajouts
            for (Entite nouveau : change.getAddedSubList()) {
                creerSprite(nouveau);
            }

            // Gestion des suppressions
            for (Entite mort : change.getRemoved()) {
                enleverSprite(mort);
            }
        }
    }
}