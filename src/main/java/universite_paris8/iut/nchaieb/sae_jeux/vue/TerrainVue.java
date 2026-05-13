package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class TerrainVue {

    @FXML
    private TilePane tilePane;

    Image bleu = new Image(getClass().getResourceAsStream("images/bleu.png"));
    Image  marron = new Image(getClass().getResourceAsStream("images/marron.png"));
    Image  vert = new Image(getClass().getResourceAsStream("images/vert.png"));
    Image  tableau = new Image(getClass().getResourceAsStream("images/tableau.png"));
    Image  nuage = new Image(getClass().getResourceAsStream("images/bleunuage.png"));

    private Terrain terrain;

    public TerrainVue(Terrain terrain) {
        this.terrain = terrain;
    }
    public void dessine(){
        for (int l = 0 ; l<this.terrain.hauteur() ; l++)
            for (int col = 0 ; col < this.terrain.largeur() ; col++) {
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
                        ImageView solHerbe = new ImageView(vert);
                        this.tilePane.getChildren().add(solHerbe);

                }
            }
    }
}
