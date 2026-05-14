package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;


import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    public static int map = 1;

    @Override
    public void start(Stage premierstage) throws IOException {
        stage=premierstage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fenetreMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("witch hat atelier defense");
        stage.setScene(scene);
        stage.show();


//        Controller.displayImage

    }

//    public static int mapChoisie() {
//        return map;
//    }


    public static void setMap(int map) {
        Main.map = map;
    }

    public static void changerScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fenetreJeu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

}