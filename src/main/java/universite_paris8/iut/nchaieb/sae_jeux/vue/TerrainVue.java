package universite_paris8.iut.nchaieb.sae_jeux.vue;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

public class TerrainVue {



    Image bleu = new Image(Main.class.getResourceAsStream("images/bleu.png"));
    Image  herbe = new Image(Main.class.getResourceAsStream("images/herbe.png"));
    Image  tableau = new Image(Main.class.getResourceAsStream("images/tableau.png"));
    Image  nuage = new Image(Main.class.getResourceAsStream("images/bleunuage.png"));
    Image  herbecailloux = new Image(Main.class.getResourceAsStream("images/herbecailloux.png"));
    Image  barriere = new Image(Main.class.getResourceAsStream("images/barriere.png"));
    Image  solmilieu = new Image(Main.class.getResourceAsStream("images/solmilieu.png"));
    Image  solhaut = new Image(Main.class.getResourceAsStream("images/solhaut.png"));
    Image  solbas = new Image(Main.class.getResourceAsStream("images/solbas.png"));
    Image  tableauBase = new Image(Main.class.getResourceAsStream("images/tableauBase.png"));
    Image  tableauHaut1 = new Image(Main.class.getResourceAsStream("images/tableauHaut1.png"));
    Image  tableauHaut2 = new Image(Main.class.getResourceAsStream("images/tableauHaut2.png"));
    Image  menutest = new Image(Main.class.getResourceAsStream("images/menutest.png"));




    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));

    private TilePane tilePane;
    private Terrain terrain;
    private StackPane stackPane;

    public TerrainVue(Terrain terrain, TilePane tilePane, StackPane stackPane) {
        this.terrain = terrain;
        this.tilePane = tilePane;
        this.stackPane= stackPane;

    }
    public void animationSquelette()  {
        ImageView iv = ajouterEntite("squelette");

        int largeurCase = 240;
        int hauteurCase = 240;
        int totalFrames = 17;
        int compt=0;

        int[] frameIndex = {12};

        iv.setTranslateX(-700); // position X en pixels
        iv.setTranslateY(-70);
        this.stackPane.getChildren().add(iv);
        Timeline squeletteMarche = new Timeline(
                new KeyFrame(Duration.millis(120), e -> {
                    int x, y;
                    if (frameIndex[0] < 25) {

                        x = frameIndex[0] % 5;
                        y = frameIndex[0] / 5;
                    } else {

                        x = frameIndex[0] - 25;
                        y = 5;
                    }





                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    frameIndex[0] = (frameIndex[0] + 1) ;
                    if (frameIndex[0]==28){
                        frameIndex[0]=12;
                    }

                })
        );

        squeletteMarche.setCycleCount(Timeline.INDEFINITE);
        squeletteMarche.play();



    }
    public Image verifMonstre(String typeMonstre) {
        if (typeMonstre.equals("squelette")) {
            return squelette;
        }
        return null;
    }

    @FXML
    public ImageView ajouterEntite(String entite) {
        Image image = verifMonstre(entite);
        if (image != null) {
        }
        ImageView squeletteView = new ImageView(image);
        return squeletteView;
    }

    public void dessine(int map) {
        switch (map){
            case 1:
                terrain.test();
                break;

            case 2:
                terrain.terrainPlainesCode();
                break;
        }


        for (int l = 0; l < this.terrain.hauteur(); l++) {
            for (int col = 0; col < this.terrain.largeur(); col++) {
                switch (this.terrain.codeTuile(l, col)) {
                    case 1:
                        ImageView ciel = new ImageView(bleu);
                        this.tilePane.getChildren().add(ciel);
                        break;

                    case 2:
                        ImageView nuagebleu = new ImageView(nuage);
                        this.tilePane.getChildren().add(nuagebleu);
                        break;
                    case 3:
                        ImageView solHerbe = new ImageView(herbe);
                        this.tilePane.getChildren().add(solHerbe);
                        break;
                    case 4:
                        ImageView herberavecailloux = new ImageView(herbecailloux);
                        this.tilePane.getChildren().add(herberavecailloux);
                        break;
                    case 5:
                        ImageView barrieres = new ImageView(barriere);
                        this.tilePane.getChildren().add(barrieres);
                        break;
                    case 6:
                        ImageView solTerreMilieu = new ImageView(solmilieu);
                        this.tilePane.getChildren().add(solTerreMilieu);
                        break;
                    case 7:
                        ImageView solTerreHaut = new ImageView(solhaut);
                        this.tilePane.getChildren().add(solTerreHaut);
                        break;
                    case 8:
                        ImageView solTerreBas = new ImageView(solbas);
                        this.tilePane.getChildren().add(solTerreBas);
                        break;
                    case 9:
                        ImageView tabBase = new ImageView(tableauBase);
                        this.tilePane.getChildren().add(tabBase);
                        break;
                    case 10:
                        ImageView tabHaut1 = new ImageView(tableauHaut1);
                        this.tilePane.getChildren().add(tabHaut1);
                        break;
                    case 11:
                        ImageView tabHaut2 = new ImageView(tableauHaut2);
                        this.tilePane.getChildren().add(tabHaut2);
                        break;
                }
            }
        }
    }
}
