package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import javafx.collections.FXCollections;
import universite_paris8.iut.nchaieb.sae_jeux.MonObservateurSymbole;

public class InterfaceVue {
    Image FeuilleSort = new Image(Main.class.getResourceAsStream("images/FeuillePourLesSorts.png"));
    Image InterfaceBas = new Image(Main.class.getResourceAsStream("images/interfaceBas.png"));
    Image SymboleGoutte = new Image(Main.class.getResourceAsStream("images/symboleGoutteDeau.png"));
    Image SymboleCroix = new Image(Main.class.getResourceAsStream("images/symboleCroix.png"));
    Image SymboleSpirale = new Image(Main.class.getResourceAsStream("images/symboleSpirale.png"));

    private StackPane stackPane;
    private ObservableList<String> tableauDimages;


    public InterfaceVue(StackPane stackPane) {
        this.stackPane = stackPane;
        this.tableauDimages = FXCollections.observableArrayList();
//        this.tableauDimages.addListener(new MonObservateurSymbole(stackPane));
    }

    public void dessinMenu () {

        ImageView FeuillePentacle = new ImageView(FeuilleSort);
        ImageView InterfaceDuBas = new ImageView(InterfaceBas);
        ImageView symboleGoutte = new ImageView(SymboleGoutte);
        ImageView symboleCroix = new ImageView(SymboleCroix);
        ImageView symboleSpirale = new ImageView(SymboleSpirale);
        if( this.stackPane!=null){
            //Interface Bas
            InterfaceDuBas.setTranslateY(750);
            InterfaceDuBas.setTranslateX(500);
            InterfaceDuBas.setScaleX(1.25);
            InterfaceDuBas.setScaleX(2.5);
            this.stackPane.getChildren().add(InterfaceDuBas);

            //Feuille pentacle
            FeuillePentacle.setTranslateX(1400); // position X en pixels
            FeuillePentacle.setTranslateY(650);
            FeuillePentacle.setScaleX(0.65);
            FeuillePentacle.setScaleY(0.65);
            this.stackPane.getChildren().add(FeuillePentacle);

            //Symbole Goutte
            symboleGoutte.setTranslateX(1480);
            symboleGoutte.setTranslateY(670);
            symboleGoutte.setScaleX(0.1);
            symboleGoutte.setScaleY(0.1);
            this.stackPane.getChildren().add(symboleGoutte);

            //Symbole Croix
            symboleCroix.setTranslateX(1320);
            symboleCroix.setTranslateY(670);
            symboleCroix.setScaleX(0.1);
            symboleCroix.setScaleY(0.1);
            this.stackPane.getChildren().add(symboleCroix);
        }


    }

}
