package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EntiteVue {
    private StackPane stackPane;
    private HashMap hashMap= new HashMap<MonstreDeBase,ImageView>();
    private HashMap hashMapAnimation= new HashMap<MonstreDeBase, Timeline>();
    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));


    public EntiteVue(StackPane stackPane) {
        this.stackPane= stackPane;
    }

    public void ajouterSprite(Entite entite){

        ImageView  iv= new ImageView();
        if (entite instanceof Squelette) {
            iv = new ImageView(squelette);
        }
        iv.translateXProperty().bind(entite.posXProperty());
        iv.translateYProperty().bind(
                entite.posYProperty()
        );
        this.hashMap.put(entite, iv);

        if(entite.getCamp()==0) iv.setScaleX(-1);
        iv.setViewport(new Rectangle2D(0,0,240,240));

        this.stackPane.getChildren().add(iv);
    }

    public void retirer(Entite entite){
        ImageView  iv= (ImageView) hashMap.get(entite);
        iv.setImage(null);
        this.stackPane.getChildren().remove(iv);
        this.hashMap.remove(entite, iv);
    }





    public void animationMarche(Entite monstre) {

        ImageView iv = (ImageView) this.hashMap.get(monstre);


        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {13};

        if(this.hashMapAnimation.containsKey(monstre)){
            Timeline timeline= (Timeline) this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

        Timeline squeletteMarche = new Timeline(

                new KeyFrame(Duration.millis(90), e -> {

                    int x, y;
                    if (frameIndex[0] < 12) {
                        x = frameIndex[0] % 6;
                        y = frameIndex[0] / 6;
                    } else {
                        x = frameIndex[0] - 12;
                        y = 2;
                    }
                    frameIndex[0]++;
                    if (frameIndex[0] == 15) frameIndex[0] = 0;
                    iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

                })
        );
        squeletteMarche.setCycleCount(15);
        squeletteMarche.play();

        this.hashMapAnimation.put(monstre, squeletteMarche);



    }

    public void animationAttaque(Entite monstre) {

        ImageView iv = (ImageView) this.hashMap.get(monstre);


        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {13};

        if(this.hashMapAnimation.containsKey(monstre)){
            System.out.println("miaou");
            Timeline timeline= (Timeline) this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

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
        squeletteMarche.setCycleCount(10);
        squeletteMarche.play();
        this.hashMapAnimation.put(monstre, squeletteMarche);





    }

    public void animationMort(Entite monstre) {


        ImageView iv=(ImageView) this.hashMap.get(monstre);
        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {27};

        if(this.hashMapAnimation.containsKey(monstre)){
            Timeline timeline= (Timeline) this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

        Timeline squeletteMort = new Timeline(
                new KeyFrame(Duration.millis(120), e -> {
                    int x = frameIndex[0] % 6;
                    int y = frameIndex[0] / 6;

                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    frameIndex[0]++;
                })
        );
        squeletteMort.setCycleCount(7);


        squeletteMort.setOnFinished(e -> {
            FadeTransition fade = new FadeTransition(Duration.seconds(2), iv);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);

            fade.setOnFinished(fadeEvent -> {
                this.hashMap.remove(iv);
                this.retirer(monstre);
            });

            fade.play();
        });
        squeletteMort.play();
        System.out.println("animort fin");





    }

}
