package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import universite_paris8.iut.nchaieb.sae_jeux.modele.combinaisonSorts.SortDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.modele.combinaisonSorts.SortDeFeu;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Environnement environnement;
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
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;
    protected MonObservateurMonstre observateur;




    private boolean modePlacementTour = false;

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

        this.monstreVue= new MonstreVue(stackPane);
        this.interfaceVue = new InterfaceVue(stackPane);

        System.out.println(Main.map);
        terrainVue.dessine(Main.map);
        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(stackPane);
        MonObservateurTour monObservateurTour = new MonObservateurTour(stackPane);

        environnement= new Environnement();
        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);


        initAnimation();

        if (stackPane != null) {
            stackPane.setOnMouseClicked(event -> {
                if (modePlacementTour) {
                    placerTour(event.getX(), event.getY());
                }
            });
        }


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

            //Partie symbole
            MonObservateurSymbole monObservateurSymbole = new MonObservateurSymbole(this.interfaceVue);
            this.environnement.getSymboles().addListener(monObservateurSymbole);

            //Partie sort
            this.lesSorts = new ArrayList<>();
            SortDeBase sc = new SortDeFeu(); //test la liste des sorts
            this.lesSorts.add(sc);
            this.interfaceVue.dessinMenu();
        }




    }

    private void mettreAJour() {
//        this.monstreVue.unTour();
//        if(this.environnement==null){
//            return;
//        }
        this.environnement.unTour();
    }

    @FXML
    public void onBoutonJouerClique() throws Exception {
        Main.map=2;
        Main.changerScene("universite_paris8/iut/nchaieb/sae_jeux/fenetreJeu.fxml");
    }



    @FXML
    public void AjouterMonstreEnnemi() {




        this.environnement.ajouterEntite();
    }
    
    @FXML
    public void AppuyerSurSymboleCroix() {
        Symbole symboleCroix = new Symbole("Croix");
        this.environnement.getSymboles().add(symboleCroix);
        System.out.println("Croix ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleGoutteDeau() {
        Symbole symboleGoutteDeau= new Symbole("Goutte");
        this.environnement.getSymboles().add(symboleGoutteDeau);
        System.out.println("Goutte ajouté dans liste");
    }

    @FXML
    public void AppuyerSurSymboleSpirale() {
        Symbole symboleSpirale = new Symbole("Spirale");
        this.environnement.getSymboles().add(symboleSpirale);
        System.out.println("Spirale ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleOeil(){
        Symbole symboleOeil = new Symbole("Oeil");
        this.environnement.getSymboles().add(symboleOeil);
        System.out.println("Oeil d'horus ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleEclipse() {
        Symbole symboleEclipse = new Symbole("Eclipse");
        this.environnement.getSymboles().add(symboleEclipse);
        System.out.println("Eclipse ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleOiseau(){
        Symbole symboleOiseau = new Symbole("Oiseau");
        this.environnement.getSymboles().add(symboleOiseau);
        System.out.println("Oiseau ajouté dans la liste");
    }

    private void placerTour(double xPixel, double yPixel) {
        int TAILLE_TUILE = 16;
        int gridX = (int) (xPixel / TAILLE_TUILE);
        int gridY = (int) (yPixel / TAILLE_TUILE);

        if (!this.terrain.estPraticable(gridX, gridY)) {

            int posXGridPixel = gridX * TAILLE_TUILE;
            int posYGridPixel = gridY * TAILLE_TUILE;

            TourDeBase nouvelleTour = new TourDeBase(posXGridPixel, posYGridPixel);
            this.environnement.ajouterTour(nouvelleTour);

            // Tour ramenée à 32x32 pour un terrain en 16px
            Rectangle rectTour = new Rectangle(32, 32, Color.DIMGRAY);
            rectTour.setTranslateX(posXGridPixel);
            rectTour.setTranslateY(posYGridPixel);
            stackPane.getChildren().add(rectTour);

            this.modePlacementTour = false;
            System.out.println("Tour placée avec succès en X:" + gridX + " Y:" + gridY);
        } else {
            System.out.println("Impossible de placer une tour sur le chemin des monstres !");
        }
    }
    @FXML
    public void AppuyerSurValideePentacle(){
        SortDeBase sortActuel;
        ArrayList<Symbole> listeTempo = new ArrayList<>(this.environnement.getSymboles());

        for (int i = 0; i < this.lesSorts.size(); i++){
            sortActuel = this.lesSorts.get(i);
            if (sortActuel.combinaisonValidee(listeTempo)){
                sortActuel.invoquerSort();
            }
        }
        this.environnement.getSymboles().clear();
        this.interfaceVue.viderSumbolesAffiches();
    }
}

}