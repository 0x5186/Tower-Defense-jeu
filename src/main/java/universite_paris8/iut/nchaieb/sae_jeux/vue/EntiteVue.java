package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EntiteVue {
    private StackPane stackPane;
    private ArrayList<MonstreDeBase> listeMonstre= new ArrayList<>();;
    private Environnement environnement;
    private HashMap hashMap= new HashMap<MonstreDeBase,ImageView>();
    private HashMap<MonstreDeBase, Timeline> animeLoop = new HashMap<>();
    private HashMap<Entite, ImageView> hashMapMorts = new HashMap<>();
    private HashMap<Entite, Timeline> animeLoopMorts = new HashMap<>();
    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));


    public EntiteVue(StackPane stackPane) {

        this.stackPane= stackPane;

//        this.environnement.le;
    }

    public void ajouterSprite(Entite entite){
        ImageView  iv= new ImageView();
        if (entite instanceof Squelette)
            iv= new  ImageView(squelette);
        iv.translateXProperty().bind(entite.posXProperty());
        iv.translateYProperty().bind(
                entite.posYProperty()
        );
        this.hashMap.put(entite, iv);

        if(entite.getCamp()==0) iv.setScaleX(-1);
        iv.setViewport(new Rectangle2D(5*240,0,240,240));

        this.stackPane.getChildren().add(iv);
    }

    public void retirer(Entite entite){
        ImageView  iv= (ImageView) hashMap.get(entite);
        iv.setImage(null);
        this.stackPane.getChildren().remove(iv);
        this.hashMap.remove(entite, iv);
    }

//    public void animeTest(ObservableList<MonstreDeBase> persos){
//        for (int i = 0; i < persos.size(); i++) {
//            MonstreDeBase monstre = persos.get(i);
//
//            if (!hashMap.containsKey(monstre) ) {
//                animationMarche(monstre);
//            }
//            else {
//
//                // TODO ça va dans le listener posé sur l'observablelist
//                ImageView iv = (ImageView) hashMap.get(monstre);
//                iv.setTranslateX(monstre.getPosX());
//                iv.setTranslateY(monstre.getPosY());
//
//
//                if (!monstre.estVivant()) {
//                    if(animeLoop.containsKey(monstre)){
//                        animeLoop.get(monstre).stop();
//                        animeLoop.remove(monstre);
//                    }
//                    this.stackPane.getChildren().remove(iv);
////                    ((ImageView) hashMap.get(monstre)).setImage(null);
//                    hashMap.remove(monstre);
//                    animationMort(monstre);
//                }
//            }
//        }
//    }
//
//    public void animationAjour() {
//        animeTest(environnement.getLesTours());
//        animeTest(environnement.getLesMonstres());
//
//    }






    public void animationMarche(Entite monstre) {


        System.out.println("marche");

        ImageView iv = (ImageView) this.hashMap.get(monstre);


        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {13};

        iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));




        Timeline squeletteMarche = new Timeline(

                new KeyFrame(Duration.millis(100), e -> {
                    System.out.println(monstre.getPosX());
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


    monstre.setActionActuelle("fixe");

    }

    public void animationMort(Entite monstre) {

        System.out.println("animort");

        ImageView iv=(ImageView) this.hashMap.get(monstre);
//
//            if(monstre.getCamp()==0) {
//                iv.setScaleX(-1);
//            }
//
//
//            iv.setTranslateX(monstre.getPosX());
//            iv.setTranslateY(monstre.getPosY());
//
//
        this.stackPane.getChildren().add(iv);

        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {27};
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

}
