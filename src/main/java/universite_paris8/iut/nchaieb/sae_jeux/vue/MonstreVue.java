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


    public void animationAjour() {
        for (int i = 0; i < environnement.getLesMonstres().size(); i++) {
            MonstreDeBase monstre = environnement.getLesMonstres().get(i);

            if (!hashMap.containsKey(monstre)) {
                animationMarche(monstre);
            }
            else {
                // monstre existant → mettre à jour position
                ImageView iv = (ImageView) hashMap.get(monstre);
                iv.setTranslateX(monstre.getPosX());
                iv.setTranslateY(monstre.getPosY());


                if (!monstre.estVivant()) {
                    ((ImageView) hashMap.get(monstre)).setImage(null);
                    ((ImageView) hashMap.get(monstre)).setVisible(false);
                    stackPane.getChildren().remove(iv);
                    hashMap.remove(monstre);

                }
            }
        }

        for (int i = 0; i < environnement.getLesAlliees().size(); i++) {
            MonstreDeBase monstre = environnement.getLesAlliees().get(i);

            if (!hashMap.containsKey(monstre)) {

                animationMarche(monstre);
            }
            else {
                ImageView iv = (ImageView) hashMap.get(monstre);
                iv.setTranslateX(monstre.getPosX());
                iv.setTranslateY(monstre.getPosY());



                if (!monstre.estVivant()) {


                    ((ImageView) hashMap.get(monstre)).setVisible(false);
                    stackPane.getChildren().remove(iv);
                    hashMap.remove(monstre);
                }
            }
        }



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
            hashMap.put(monstre, iv);
            if(monstre.getCamp()==0) {
                iv.setScaleX(-1);
            }

            iv.setTranslateX(monstre.getPosX());
            iv.setTranslateY(monstre.getPosY());
            this.stackPane.getChildren().add(iv);
            System.out.println(monstre.estVivant());
            if(monstre.getActionActuel()==2){
                int largeurCase = 240;
                int hauteurCase = 240;
                int[] frameIndex = {12};
                iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));




                Timeline squeletteMarche = new Timeline(


                        new KeyFrame(Duration.millis(90), e -> {

                            iv.setTranslateX(monstre.getPosX());
                            iv.setTranslateY(monstre.getPosY());
                            if (!monstre.estVivant()) {
                                iv.setImage(null);
                                return;
                            }

                            int x, y;
                            if (frameIndex[0] < 25) {
                                x = frameIndex[0] % 5;
                                y = frameIndex[0] / 5;
                            } else {
                                x = frameIndex[0] - 25;
                                y = 5;
                            }
                            frameIndex[0]++;
                            if (frameIndex[0] == 27) frameIndex[0] = 12;
                            iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

                        })
                );

                squeletteMarche.setCycleCount(Timeline.INDEFINITE);
                squeletteMarche.play();
                animeLoop.put(monstre, squeletteMarche);
            }

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
