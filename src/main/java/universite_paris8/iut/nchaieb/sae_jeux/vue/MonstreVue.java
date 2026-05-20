package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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

import java.util.ArrayList;
import java.util.HashMap;


public class MonstreVue {
    private StackPane stackPane;
    private ArrayList<MonstreDeBase> listeMonstre= new ArrayList<>();;
    private Environnement environnement;
    private HashMap hashMap= new HashMap<MonstreDeBase,ImageView>();
    private HashMap<MonstreDeBase, Timeline> animeLoop = new HashMap<>();

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

                ImageView iv = (ImageView) hashMap.get(monstre);
                iv.setTranslateX(monstre.getPosX());
                iv.setTranslateY(monstre.getPosY());


                if (!monstre.estVivant() ) {
                    animeLoop.get(monstre).stop();
                    animeLoop.remove(monstre);
//


//                    stackPane.getChildren().remove(iv);
                    hashMap.remove(monstre);
                    animationMort(monstre);
                }
            }
        }
    }

    public void animationAjour() {
        animeTest(environnement.getLesAlliees());
        animeTest(environnement.getLesMonstres());

//
//        for (int i = 0; i < environnement.getLesAlliees().size(); i++) {
//            MonstreDeBase monstre = environnement.getLesAlliees().get(i);
//
//            if (!hashMap.containsKey(monstre)) {
//                System.out.println("pas dans hasmap");
//                animationMarche(monstre);
//
//            }
//            else {
//                ImageView iv = (ImageView) hashMap.get(monstre);
//                iv.setTranslateX(monstre.getPosX());
//                iv.setTranslateY(monstre.getPosY());
//
//                System.out.println("dans hashmap");
//
//                if (!monstre.estVivant()) {
//                    System.out.println("dead");
//
//                    animeLoop.remove(monstre);
//
//                    animationMort(monstre);
//                    ((ImageView) hashMap.get(monstre)).setVisible(false);
//                    stackPane.getChildren().remove(iv);
//                    hashMap.remove(monstre);
//
//
//                }
//            }
//        }



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


            if(monstre.getActionActuel()==2){
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
//                                System.out.println("mort");
//                                if (frameIndex[0] < 31) {
//                                    x = frameIndex[0] % 6;
//                                    y = frameIndex[0] / 6;
//                                } else {
//                                    x = frameIndex[0] - 27;
//                                    y = 6;
//                                }
//                                frameIndex[0]++;
//                                if (frameIndex[0] == 36) frameIndex[0] = 27;
//                                iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

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
//
                squeletteMarche.setCycleCount(Timeline.INDEFINITE);
                squeletteMarche.play();
                animeLoop.put(monstre, squeletteMarche);


            }

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


            hashMap.put(monstre, iv);

            this.stackPane.getChildren().add(iv);

            int largeurCase = 240;
            int hauteurCase = 240;
            int[] frameIndex = {13};
            iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));

            Timeline squeletteMarche = new Timeline(

                    new KeyFrame(Duration.millis(90), e -> {

                        iv.setTranslateX(monstre.getPosX());
                        iv.setTranslateY(monstre.getPosY());
                        if (!monstre.estVivant()) {
                            iv.setImage(null);
                            return;
                        }
                        System.out.println("animation mort");
                        int x, y;
                        if (frameIndex[0] < 31) {
                            x = frameIndex[0] % 6;
                            y = frameIndex[0] / 6;
                        } else {
                            x = frameIndex[0] - 27;
                            y = 6;
                        }
                        frameIndex[0]++;
                        if (frameIndex[0] == 36) frameIndex[0] = 27;
                        iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

                    })
            );
//

            squeletteMarche.setCycleCount(Timeline.INDEFINITE);
            squeletteMarche.play();
            System.out.println("animort fin");

            animeLoop.put(monstre, squeletteMarche);






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
