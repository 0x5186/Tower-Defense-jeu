package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;


public class MonstreVue {
    private StackPane stackPane;

    public MonstreVue(StackPane stackPane) {


        this.stackPane= stackPane;

    }
    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));

    public void animationSquelette()  {
        ImageView iv = ajouterEntite("squelette");

        int largeurCase = 240;
        int hauteurCase = 240;
        int totalFrames = 17;
        int compt=0;

        int[] frameIndex = {12};







        iv.setTranslateX(-700); // position X en pixels
        iv.setTranslateY(-70);

        Timeline squeletteMarche = new Timeline(
                new KeyFrame(Duration.millis(120), e -> {
                    int x, y;
                    if (frameIndex[0] < 25) {

                        x = frameIndex[0] % 5;
                        y = frameIndex[0] / 5;
                    } else {

                        x = frameIndex[0] - 25;
                        y = 5;
                    }





                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    frameIndex[0] = (frameIndex[0] + 1) ;
                    if (frameIndex[0]==28){
                        frameIndex[0]=12;
                    }
                    this.stackPane.getChildren().add(iv);
                })
        );

        squeletteMarche.setCycleCount(Timeline.INDEFINITE);
        squeletteMarche.play();




    }

    public Image verifMonstre(String typeMonstre) {
        if (typeMonstre.equals("squelette")) {
            return squelette;
        }
        return null;
    }

    @FXML
    public ImageView ajouterEntite(String entite) {
        Image image = verifMonstre(entite);
        if (image != null) {
        }
        ImageView squeletteView = new ImageView(image);
        return squeletteView;
    }
}
