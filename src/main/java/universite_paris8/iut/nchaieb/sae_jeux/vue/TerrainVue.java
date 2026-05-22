package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

public class TerrainVue {
    Image bleu = new Image(Main.class.getResourceAsStream("images/bleu.png"));
    Image herbe = new Image(Main.class.getResourceAsStream("images/herbe.png"));
    Image nuage = new Image(Main.class.getResourceAsStream("images/bleunuage.png"));
    Image herbecailloux = new Image(Main.class.getResourceAsStream("images/herbecailloux.png"));
    Image barriere = new Image(Main.class.getResourceAsStream("images/barriere.png"));
    Image solmilieu = new Image(Main.class.getResourceAsStream("images/solmilieu.png"));
    Image solhaut = new Image(Main.class.getResourceAsStream("images/solhaut.png"));
    Image solbas = new Image(Main.class.getResourceAsStream("images/solbas.png"));

    private TilePane tilePane;
    private Terrain terrain;

    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        this.tilePane = tilePane;
    }

    public void dessine(int map) {
        switch (map){
            case 1: terrain.test(); break;
            case 2: terrain.terrainPlainesCode(); break;
        }

        for (int l = 0; l < this.terrain.hauteur(); l++) {
            for (int col = 0; col < this.terrain.largeur(); col++) {
                switch (this.terrain.codeTuile(l, col)) {
                    case 1: this.tilePane.getChildren().add(new ImageView(bleu)); break;
                    case 2: this.tilePane.getChildren().add(new ImageView(nuage)); break;
                    case 3: this.tilePane.getChildren().add(new ImageView(herbe)); break;
                    case 4: this.tilePane.getChildren().add(new ImageView(herbecailloux)); break;
                    case 5: this.tilePane.getChildren().add(new ImageView(barriere)); break;
                    case 6: this.tilePane.getChildren().add(new ImageView(solmilieu)); break;
                    case 7: this.tilePane.getChildren().add(new ImageView(solhaut)); break;
                    case 8: this.tilePane.getChildren().add(new ImageView(solbas)); break;
                }
            }
        }
    }
}