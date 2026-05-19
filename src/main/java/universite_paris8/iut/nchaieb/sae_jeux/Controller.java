package universite_paris8.iut.nchaieb.sae_jeux;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Environnement environnement;
    private ArrayList<Symbole> symboles;
    private ArrayList<SortDeBase> lesSorts;

    @FXML
    private TilePane tilePane;
    @FXML
    private StackPane stackPane;




    private Timeline gameLoop;
    private int temps;
    TerrainVue terrainVue;
    Terrain terrain;
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;





    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(1),

                (ev ->{
                    temps++;
                    mettreAJour();
                    monstreVue.animationAjour();
                })
        );
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.terrain = new Terrain();
        this.terrainVue = new TerrainVue(terrain, tilePane);
        this.environnement= new Environnement();
        this.monstreVue= new MonstreVue(stackPane, environnement);
        this.interfaceVue = new InterfaceVue(stackPane);
       // this.interfaceVue.dessinMenu();
        System.out.println(Main.map);
        terrainVue.dessine(Main.map);

        initAnimation();
        //ajout du rectangle rouge(tempo) à changer plus tard
        //un if car le cube se mettait en mouvement automatiquement quand on lancait le jeu
        if (Main.map == 2) {

            this.interfaceVue.dessinMenu();
            Rectangle rectangle = new Rectangle(50, 50, Color.RED);
            stackPane.getChildren().add(rectangle);
            rectangle.setX(10);
            rectangle.setY(340);
            //la translation du cube("monstre")
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(rectangle);
            transition.setByX(1450); //on veut qu'il avance de 1020 px
            transition.setDuration(Duration.seconds(10)); //On veut que l'anim dure 10 s
            transition.setCycleCount(TranslateTransition.INDEFINITE); //Cela va durer jusqu'à quon stop la fenêtre
            transition.setAutoReverse(true); //va faire d'abord -> 520 px puis -520px puis ainsi de suite
            transition.play();



            try {
                gameLoop.play();
            } catch (Exception e) {
                initAnimation();
            }

            //Partie sort
            this.symboles = new ArrayList<>();
            this.lesSorts = new ArrayList<>();

        }




    }

    private void mettreAJour() {
        this.environnement.unTour();
    }

    @FXML
    public void onBoutonJouerClique() throws Exception {
        Main.map=2;
        Main.changerScene("universite_paris8/iut/nchaieb/sae_jeux/fenetreJeu.fxml");

    }

    @FXML
    public void AjouterMonstreAllie() {
        MonstreDeBase squelette=new Squelette();
        this.environnement.ajouterEntite(squelette,0);

    }

    @FXML
    public void AjouterMonstreEnnemi() {
        MonstreDeBase squelette=new Squelette();

        this.environnement.ajouterEntite(squelette,1);

    }
    
    @FXML
    public void AppuyerSurSymboleCroix() {
        Symbole symboleCroix = new Symbole("Croix");
        this.symboles.add(symboleCroix);
    }

    @FXML
    public void AppuyerSurSymboleGoutteDeau() {
        Symbole symboleGoutteDeau= new Symbole("GoutteDeau");
        this.symboles.add(symboleGoutteDeau);
    }

    @FXML
    public void validerPentacle(){
        SortDeBase sortActuel;

        for (int i = 0; i < this.lesSorts.size(); i++){
            sortActuel = this.lesSorts.get(i);

            if (sortActuel.combinaisonValidee(this.symboles)){
                //jsplus jhr aide moi
            }

        }

        this.symboles.clear();
    }

    //bouton reset de la combinaisons
    public void AppuyerSurBoutonReset(){
    }

}
