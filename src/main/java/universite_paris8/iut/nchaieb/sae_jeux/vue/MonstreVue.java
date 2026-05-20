package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.MonstreDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Squelette;

import java.util.ArrayList;
import javafx.geometry.Pos; // Import bien présent

public class MonstreVue {
    private StackPane stackPane;
    private ArrayList<MonstreDeBase> listeMonstre= new ArrayList<>();
    private Environnement environnement;

    Image squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));

    public MonstreVue(StackPane stackPane, Environnement environnement) {
        this.stackPane = stackPane;
        this.environnement = environnement;
    }

    public void animationMarche(MonstreDeBase monstre) {

        if(monstre instanceof Squelette) {
            ImageView iv = new ImageView(squelette);

            StackPane.setAlignment(iv, Pos.TOP_LEFT);

            this.stackPane.getChildren().add(iv);

            int largeurCase = 240;
            int hauteurCase = 240;
            int[] frameIndex = {12};
            iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));

            Timeline squeletteMarche = new Timeline(
                    new KeyFrame(Duration.millis(90), e -> {
                        int x, y;
                        if (frameIndex[0] < 25) {
                            x = frameIndex[0] % 5;
                            y = frameIndex[0] / 5;
                        } else {
                            x = frameIndex[0] - 25;
                            y = 5;
                        }
                        iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                        frameIndex[0]++;
                        if (frameIndex[0] == 27) frameIndex[0] = 12;

                        // Met à jour la position visuelle grâce aux coordonnées logiques du monstre
                        iv.setTranslateX(monstre.getPosX());
                        iv.setTranslateY(monstre.getPosY());
                    })
            );
            squeletteMarche.setCycleCount(Timeline.INDEFINITE);
            squeletteMarche.play();
        }
    }

    public Image verifMonstre(String typeMonstre) {
        if (typeMonstre.equals("squelette")) {
            return squelette;
        }
        return null;
    }

    public void unTour() {
        for(int i=0; i<listeMonstre.size(); i++){
            avancer(listeMonstre.get(i));
        }
    }

    public void avancer(MonstreDeBase monstre){
        monstre.setPosX(monstre.getPosX()+5);
    }

    public void ajouterMonstre(MonstreDeBase monstre){
        listeMonstre.add(monstre);
    }
}