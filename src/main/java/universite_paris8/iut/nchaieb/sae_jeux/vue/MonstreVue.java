package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.MonstreDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class MonstreVue {
    private StackPane stackPane;
    private ArrayList<MonstreDeBase> listeMonstre= new ArrayList<>();;
    private Environnement environnement;
    private HashMap hashMap= new HashMap<MonstreDeBase,ImageView>();
    private HashMap<MonstreDeBase, Timeline> animeLoop = new HashMap<>();
    private HashMap<MonstreDeBase, ImageView> hashMapMorts = new HashMap<>();
    private HashMap<MonstreDeBase, Timeline> animeLoopMorts = new HashMap<>();
    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));


    public MonstreVue(StackPane stackPane, Environnement environnement) {

        this.stackPane= stackPane;
        this.environnement= environnement;
    }


    public void animeTest(ArrayList<MonstreDeBase> persos){
        for (int i = 0; i < persos.size(); i++) {
            MonstreDeBase monstre = persos.get(i);

            if (!hashMap.containsKey(monstre) ) {
                animationMarche(monstre);
            }
            else {

                // TODO ça va dans le listener posé sur l'observablelist
                ImageView iv = (ImageView) hashMap.get(monstre);
                iv.setTranslateX(monstre.getPosX());
                iv.setTranslateY(monstre.getPosY());


                if (!monstre.estVivant()) {
                    if(animeLoop.containsKey(monstre)){
                        animeLoop.get(monstre).stop();
                        animeLoop.remove(monstre);
                    }
                    this.stackPane.getChildren().remove(iv);
//                    ((ImageView) hashMap.get(monstre)).setImage(null);
                    hashMap.remove(monstre);
                    animationMort(monstre);
                }
            }
        }
    }

    public void animationAjour() {
        animeTest(environnement.getLesAlliees());
        animeTest(environnement.getLesMonstres());

    }

    public boolean verifHashMap(MonstreDeBase monstre){
        for(int i=0;i <stackPane.getChildren().size();i++) {
            if (hashMap.get(stackPane.getChildren().get(i)) == monstre) {
                return true;
            }
        }
        return false;
    }


    public void animationMarche(MonstreDeBase monstre) {

        if(monstre instanceof Squelette){
            ImageView iv=new ImageView(squelette);

            if(monstre.getCamp()==0) {
                iv.setScaleX(-1);
            }


            iv.setTranslateX(monstre.getPosX());
            iv.setTranslateY(monstre.getPosY());


            hashMap.put(monstre, iv);
            this.stackPane.getChildren().add(iv);

            int largeurCase = 240;
            int hauteurCase = 240;
            int[] frameIndex = {13};
            iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));




            Timeline squeletteMarche = new Timeline(

                    new KeyFrame(Duration.millis(100), e -> {

                        iv.setTranslateX(monstre.getPosX());
                        iv.setTranslateY(monstre.getPosY());
                        int x, y;
                        if (!monstre.estVivant()) {
                            iv.setImage(null);


                        }
                        else{
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
                        }
                    })
            );
            squeletteMarche.setCycleCount(Timeline.INDEFINITE);
            squeletteMarche.play();
            animeLoop.put(monstre, squeletteMarche);



        }


    }

    public void animationMort(MonstreDeBase monstre) {

        System.out.println("animort");

        if(monstre instanceof Squelette){
            ImageView iv=new ImageView(squelette);

            if(monstre.getCamp()==0) {
                iv.setScaleX(-1);
            }


            iv.setTranslateX(monstre.getPosX());
            iv.setTranslateY(monstre.getPosY());




            hashMapMorts.put(monstre,iv);

            this.stackPane.getChildren().add(iv);

            int largeurCase = 240;
            int hauteurCase = 240;
            int[] frameIndex = {27};
            iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));

            iv.toFront();

            Timeline squeletteMort = new Timeline(
                    new KeyFrame(Duration.millis(120), e -> {
                        int x = frameIndex[0] % 6;
                        int y = frameIndex[0] / 6;

                        iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                        frameIndex[0]++;
                    })
            );
            squeletteMort.setCycleCount(7);

            // 3. Une fois les 7 cycles finis, on enchaîne sur le Fade
            squeletteMort.setOnFinished(e -> {
                FadeTransition fade = new FadeTransition(Duration.seconds(2), iv);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);

                fade.setOnFinished(fadeEvent -> {
                    this.stackPane.getChildren().remove(iv);
                    hashMapMorts.remove(monstre);
                });

                fade.play();
            });
            squeletteMort.play();
            System.out.println("animort fin");






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
        System.out.println(monstre.getPosX());
    }
    public void ajouterMonstre(MonstreDeBase monstre){
        listeMonstre.add(monstre);
//        lancerAnimations();
    }
}
