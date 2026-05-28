package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;

import java.util.HashMap;

public class TourVue {
    private StackPane stackPane;
    private HashMap hashMap= new HashMap<Monstre, ImageView>();
    private HashMap hashMapAnimation= new HashMap<Monstre, Timeline>();
    Image squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));
    Image  sorcier = new Image(Main.class.getResourceAsStream("images/sorcier.png"));



    public TourVue(StackPane stackPane) {
        this.stackPane= stackPane;
    }

    public void ajouterSprite(Tour tour){

        ImageView  iv= new ImageView();

        iv.translateXProperty().bind(tour.posXProperty());
        iv.translateYProperty().bind(
                tour.posYProperty()
        );
        this.hashMap.put(tour, iv);


        iv.setViewport(new Rectangle2D(0,0,240,240));

        this.stackPane.getChildren().add(iv);
    }

    public void retirer(Entite entite){
        ImageView  iv= (ImageView) hashMap.get(entite);
        iv.setImage(null);
        this.stackPane.getChildren().remove(iv);
        this.hashMap.remove(entite, iv);
    }

    public void stopAnimation(Entite entite){
        if(this.hashMapAnimation.containsKey(entite)){
            Timeline timeline= (Timeline) this.hashMapAnimation.get(entite);
            timeline.stop();
            this.hashMapAnimation.remove(entite);
        }
    }


    public void animationAttaque(Entite monstre) {

        ImageView iv = (ImageView) this.hashMap.get(monstre);


        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {13};



        Timeline squeletteMarche = new Timeline(

                new KeyFrame(Duration.millis(100), e -> {

                    int x, y;
                    if (frameIndex[0] < 25) {
                        x = frameIndex[0] % 6;
                        y = frameIndex[0] / 6;
                    } else {
                        x = frameIndex[0] - 24;
                        y = 4;
                    }
                    frameIndex[0]++;
                    if (frameIndex[0] == 27) frameIndex[0] = 12;
                    iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

                })
        );
        this.hashMapAnimation.put(monstre, squeletteMarche);
        squeletteMarche.setCycleCount(10);
        squeletteMarche.play();






    }

}
