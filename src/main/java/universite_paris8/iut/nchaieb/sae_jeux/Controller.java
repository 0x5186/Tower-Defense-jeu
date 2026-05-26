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
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Environnement environnement;

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

    private boolean modePlacementTour = false;

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.millis(16),
                (ev -> {
                    temps++;
                    mettreAJour();
                })
        );
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain = new Terrain();
        this.terrainVue = new TerrainVue(terrain, tilePane);

        this.environnement = new Environnement(this.terrain);

        this.monstreVue = new MonstreVue(stackPane, environnement);
        this.interfaceVue = new InterfaceVue(stackPane);

        System.out.println(Main.map);
        terrainVue.dessine(Main.map);
        initAnimation();

        if (stackPane != null) {
            stackPane.setOnMouseClicked(event -> {
                if (modePlacementTour) {
                    placerTour(event.getX(), event.getY());
                }
            });
        }

        if (Main.map == 2) {
            this.interfaceVue.dessinMenu();
            Rectangle rectangle = new Rectangle(50, 50, Color.RED);

            if (stackPane != null) {
                stackPane.getChildren().add(rectangle);
            }

            rectangle.setX(10);
            rectangle.setY(340);

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(rectangle);
            transition.setByX(1450);
            transition.setDuration(Duration.seconds(10));
            transition.setCycleCount(TranslateTransition.INDEFINITE);
            transition.setAutoReverse(true);
            transition.play();

            try {
                gameLoop.play();
            } catch (Exception e) {
                initAnimation();
            }
        }
    }

    private void mettreAJour() {
        this.environnement.unTour();
        this.monstreVue.animationAjour();
    }

    @FXML
    public void onBoutonJouerClique() throws Exception {
        Main.map = 2;
        Main.changerScene("universite_paris8/iut/nchaieb/sae_jeux/fenetreJeu.fxml");
    }

    @FXML
    public void AjouterMonstreAllie() {
        MonstreDeBase squelette = new Squelette();
        if (this.environnement == null) return;
        this.environnement.ajouterEntite(squelette, 0); // 0 = Allié
        this.monstreVue.ajouterMonstre(squelette);
    }

    @FXML
    public void AjouterMonstreEnnemi() {
        MonstreDeBase squelette = new Squelette();
        if (this.environnement == null) return;
        this.environnement.ajouterEntite(squelette, 1); // 1 = Ennemi
        this.monstreVue.ajouterMonstre(squelette);
    }

    @FXML
    public void AppuyerSurSymboleCroix() { System.out.println("ok okgdgfdofgdk"); }

    @FXML
    public void AppuyerSurSymboleGoutteDeau() { System.out.println("ok okgdgfdofgdk"); }



    @FXML
    public void activerModePlacementTour() {
        this.modePlacementTour = true;
        System.out.println("Mode placement de tour activé. Cliquez sur une case vide du terrain !");
    }

    private void placerTour(double xPixel, double yPixel) {
        int TAILLE_TUILE = 32;
        int gridX = (int) (xPixel / TAILLE_TUILE);
        int gridY = (int) (yPixel / TAILLE_TUILE);

        if (!this.terrain.estPraticable(gridX, gridY)) {

            int posXGridPixel = gridX * TAILLE_TUILE;
            int posYGridPixel = gridY * TAILLE_TUILE;

            TourDeBase nouvelleTour = new TourDeBase(posXGridPixel, posYGridPixel);
            this.environnement.ajouterTour(nouvelleTour);

            Rectangle rectTour = new Rectangle(TAILLE_TUILE, TAILLE_TUILE, Color.DIMGRAY);
            rectTour.setTranslateX(posXGridPixel);
            rectTour.setTranslateY(posYGridPixel);
            stackPane.getChildren().add(rectTour);

            this.modePlacementTour = false;
            System.out.println("Tour placée avec succès en X:" + gridX + " Y:" + gridY);
        } else {
            System.out.println("Impossible de placer une tour sur le chemin des monstres !");
        }
    }
}