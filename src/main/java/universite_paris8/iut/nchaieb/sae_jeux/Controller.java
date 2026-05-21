package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.vue.EntiteVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Environnement environnement;
    private ArrayList<Symbole> symboles;
    private ArrayList<SortDeBase> lesSorts;


    @FXML
    private Button boutonAjouterMonstre;
    @FXML
    private TilePane tilePane;
    @FXML
    private StackPane stackPane;




    private Timeline gameLoop;
    private int temps=0; //TODO supprimer car remplacé par nbTours dans Env
    TerrainVue terrainVue;
    Terrain terrain;
    EntiteVue monstreVue;
    InterfaceVue interfaceVue;





    private void initAnimation() {
        gameLoop = new Timeline();


        KeyFrame kf = new KeyFrame(
                Duration.seconds(1),

                (ev ->{
                    temps++;
                    environnement.unTour();
//                    monstreVue.animationAjour();
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
        this.monstreVue= new EntiteVue(stackPane);
        this.interfaceVue = new InterfaceVue(stackPane);
       // this.interfaceVue.dessinMenu();
        System.out.println(Main.map);
        terrainVue.dessine(Main.map);
        MonObservateurEntite observateur = new MonObservateurEntite(stackPane);
        environnement.getLesMonstres().addListener(observateur);
        MonObservateurSymbole symbole = new MonObservateurSymbole(stackPane);

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
            SortDeBase sc = new SortDeFeu();
            this.lesSorts.add(sc);
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
//        ChangeListener<Entite> ll = ((obs, old, nouv)-> );

        this.environnement.ajouterEntite(squelette,1);

    }
    
    @FXML
    public void AppuyerSurSymboleCroix() {
        Symbole symboleCroix = new Symbole("Croix");
        this.symboles.add(symboleCroix);
        System.out.println("Croix ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleGoutteDeau() {
        Symbole symboleGoutteDeau= new Symbole("Goutte");
        this.symboles.add(symboleGoutteDeau);
        System.out.println("Goutte ajouté dans liste");
    }

    @FXML
    public void AppuyerSurSymboleSpirale() {
        Symbole symboleSpirale = new Symbole("Spirale");
        this.symboles.add(symboleSpirale);
        System.out.println("Spirale ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurValideePentacle(){
        SortDeBase sortActuel;

        for (int i = 0; i < this.lesSorts.size(); i++){
            sortActuel = this.lesSorts.get(i);

            if (sortActuel.combinaisonValidee(this.symboles)){
                sortActuel.invoquerSort();
            }

        }

        this.symboles.clear();
    }
}
