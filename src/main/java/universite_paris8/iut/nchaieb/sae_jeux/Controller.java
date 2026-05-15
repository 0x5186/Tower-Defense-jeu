package universite_paris8.iut.nchaieb.sae_jeux;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Environnement modele;


    @FXML
    private TilePane tilePane;
    @FXML
    private StackPane stackPane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Terrain terrain = new Terrain();
        TerrainVue terrainVue = new TerrainVue(terrain, tilePane, stackPane);
        System.out.println(Main.map);
        terrainVue.dessine(Main.map);



    }
    @FXML
    public void onBoutonJouerClique() throws Exception {
        Main.map=2;
        Main.changerScene("universite_paris8/iut/nchaieb/sae_jeux/fenetreJeu.fxml");

    }


}
