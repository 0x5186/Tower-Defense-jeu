package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.vue.EntiteVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Environnement environnement;
    private ObservableList<Symbole> symboles; //TODO déplacer dans environnement
    private ArrayList<SortDeBase> lesSorts;


    @FXML
    private Button boutonAjouterMonstre;
    @FXML
    private TilePane tilePane;
    @FXML
    private StackPane stackPane;




    private Timeline gameLoop;
    private int temps; //TODO supprimer car remplacé par nbTours dans Env
    TerrainVue terrainVue;
    Terrain terrain;
    EntiteVue monstreVue;
    InterfaceVue interfaceVue;
    protected MonObservateurEntite observateur;




    private void initAnimation() {
        gameLoop = new Timeline();


        KeyFrame kf = new KeyFrame(
                Duration.seconds(1),

                (ev ->{
                    temps++;
                    this.environnement.unTour();
//
                })
        );
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.terrain = new Terrain();
        this.terrainVue = new TerrainVue(terrain, tilePane);

        this.monstreVue= new EntiteVue(stackPane);
        this.interfaceVue = new InterfaceVue(stackPane);

        System.out.println(Main.map);
        terrainVue.dessine(Main.map);
        observateur = new MonObservateurEntite(stackPane);
        environnement= new Environnement();
        environnement.getLesMonstres().addListener(observateur);

        MonObservateurSymbole symbole = new MonObservateurSymbole(this.interfaceVue);

        initAnimation();

        if (Main.map == 2) {


            Rectangle rectangle = new Rectangle(50, 50, Color.RED);
            stackPane.getChildren().add(rectangle);
            rectangle.setX(10);
            rectangle.setY(340);




            try {
                gameLoop.play();
            } catch (Exception e) {
                initAnimation();
            }

            //Partie sort
            this.symboles = FXCollections.observableArrayList();
            MonObservateurSymbole observateur = new MonObservateurSymbole(this.interfaceVue);
            this.symboles.addListener(observateur);
            this.lesSorts = new ArrayList<>();
            SortDeBase sc = new SortDeFeu();
            this.lesSorts.add(sc);
            this.interfaceVue.dessinMenu();
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
        this.environnement.ajouterEntite(0);

    }
    @FXML
    public void AjouterMonstreEnnemi() {



        MonstreDeBase squelette=new Squelette(1);
        this.environnement.ajouterEntite(1);
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
    public void AppuyerSurSymboleOeil(){
        Symbole symboleOeil = new Symbole("Oeil");
        this.symboles.add(symboleOeil);
        System.out.println("Oeil d'horus ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleEclipse() {
        Symbole symboleEclipse = new Symbole("Eclipse");
        this.symboles.add(symboleEclipse);
        System.out.println("Eclipse ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleOiseau(){
        Symbole symboleOiseau = new Symbole("Oiseau");
        this.symboles.add(symboleOiseau);
        System.out.println("Oiseau ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleTriangle(){
        Symbole symboleTriangle = new Symbole("Triangle");
        this.symboles.add(symboleTriangle);
        System.out.println("Triangle ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurValideePentacle(){
        SortDeBase sortActuel;

        ArrayList<Symbole> listeTempo = new ArrayList<>(this.symboles);

        for (int i = 0; i < this.lesSorts.size(); i++){
            sortActuel = this.lesSorts.get(i);

            if (sortActuel.combinaisonValidee(listeTempo)){
                sortActuel.invoquerSort();
            }

        }

        this.symboles.clear();
        this.interfaceVue.viderSumbolesAffiches();
    }
}
