package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;

import java.util.Stack;

public class InterfaceVue {
    Image FeuilleSort = new Image(Main.class.getResourceAsStream("images/FeuillePourLesSorts.png"));
    Image InterfaceBas = new Image(Main.class.getResourceAsStream("images/interfaceBas.png"));
    private StackPane stackPane;


    public InterfaceVue(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void dessinMenu () {
        ImageView FeuillePentacle = new ImageView(FeuilleSort);
        ImageView InterfaceDuBas = new ImageView(InterfaceBas);

        this.stackPane.getChildren().add(InterfaceDuBas);

        InterfaceDuBas.setTranslateY(250);
        InterfaceDuBas.setScaleX(2);

        FeuillePentacle.setTranslateX(575); // position X en pixels
        FeuillePentacle.setTranslateY(250);
        FeuillePentacle.setScaleX(0.65);
        FeuillePentacle.setScaleY(0.65);
        this.stackPane.getChildren().add(FeuillePentacle);
    }

}
