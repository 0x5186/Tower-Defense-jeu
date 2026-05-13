package universite_paris8.iut.nchaieb.sae_jeux;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Environnement modele;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Terrain terrain = new Terrain();

        TerrainVue terrainVue = new TerrainVue(terrain);
        terrainVue.dessine();

//        this.tilePane.setPrefColumns(terrain.largeur());


//        for (int l = 0 ; l<terrain.hauteur() ; l++)
//            for (int col = 0 ; col < terrain.largeur() ; col++) {
//                switch (terrain.codeTuile(l,col)){
//                    case 1:
//                        ImageView ciel = new ImageView(bleu);
//                        this.tilePane.getChildren().add(ciel);
//                        break;
//
//                    case 2:
//                        ImageView nuagebleu = new ImageView(nuage);
//                        this.tilePane.getChildren().add(nuagebleu);
//                        break;
//                    case 3:
//                        ImageView solHerbe = new ImageView(vert);
//                        this.tilePane.getChildren().add(solHerbe);
//
//                }
//
//
//
//            }




//        modele= new Environnement(300, 300);
//        int i=0;
//        while(i<342){
//
//            if(i==10){

//            }
//            if(i==65){
//                ImageView nuage = new ImageView(nuagegauche);
//                this.tilePane.getChildren().add(nuage);
//                ImageView nuage1 = new ImageView(nuagemilieu);
//                this.tilePane.getChildren().add(nuage1);
//                ImageView nuage2 = new ImageView(nuagedroite);
//                this.tilePane.getChildren().add(nuage2);
//                i=i+3;
//            }
//            if(i==30){
//                ImageView nuage = new ImageView(nuagehaut);
//                this.tilePane.getChildren().add(nuage);
//            }
//            if(i==100){
//                ImageView nuage = new ImageView(nuagegauche);
//                this.tilePane.getChildren().add(nuage);
//                ImageView nuage1 = new ImageView(nuagemilieu);
//                this.tilePane.getChildren().add(nuage1);
//                ImageView nuage2 = new ImageView(nuagedroite);
//                this.tilePane.getChildren().add(nuage2);
//                i=i+3;
//            }
//            else{

//            }
//            i++;
//        }
//        i=0;
//        while( i<570){
//
//            ImageView myImageView = new ImageView(vert);
//            this.tilePane.getChildren().add(myImageView);
//            i++;
//        }
//        i=0;
//        while(i<342){
//
//            ImageView myImageView = new ImageView(marron);
//            this.tilePane.getChildren().add(myImageView);
//            i++;
//        }




    }

}
