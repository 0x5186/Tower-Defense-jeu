package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    public static int map = 1;

    @Override
    public void start(Stage premierstage) throws IOException {
        stage = premierstage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fenetreMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Witch Hat Atelier Defense");
        stage.setScene(scene);
        stage.show();
    }

    public static void setMap(int map) { Main.map = map; }

    public static void changerScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fenetreJeu.fxml"));
        Scene scene = new Scene(loader.load(), 1920, 1080);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch();
    }
}