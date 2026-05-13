package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fenetreJeu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("witch hat atelier defense");
        stage.setScene(scene);
        stage.show();
//        Controller.displayImage

    }

    public static void main(String[] args) {
        launch();
    }

}