package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

import java.util.ArrayList;

public class InterfaceVue {
    Image FeuilleSort = new Image(Main.class.getResourceAsStream("images/FeuillePourLesSorts.png"));
    Image InterfaceBas = new Image(Main.class.getResourceAsStream("images/interfaceBas.png"));
    Image SymboleGoutte = new Image(Main.class.getResourceAsStream("images/symboleGoutteDeau.png"));
    Image SymboleCroix = new Image(Main.class.getResourceAsStream("images/symboleCroix.png"));
    Image SymboleSpirale = new Image(Main.class.getResourceAsStream("images/symboleSpirale.png"));

    private ArrayList<String> tableauDimages;
    private StackPane stackPane;


    public InterfaceVue(StackPane stackPane) {
        this.stackPane = stackPane;
        this.tableauDimages = new ArrayList<>();
    }

    public void dessinMenu () {

        ImageView FeuillePentacle = new ImageView(FeuilleSort);
        ImageView InterfaceDuBas = new ImageView(InterfaceBas);
        ImageView symboleGoutte = new ImageView(SymboleGoutte);
        ImageView symboleCroix = new ImageView(SymboleCroix);
        ImageView symboleSpirale = new ImageView(SymboleSpirale);
        System.out.println("bla");
        if( this.stackPane!=null){
            System.out.println("blo");
            this.stackPane.getChildren().add(InterfaceDuBas);

            //Interface du bas
            InterfaceDuBas.setTranslateY(750);
            InterfaceDuBas.setTranslateX(500);
            InterfaceDuBas.setScaleX(1.25);
            InterfaceDuBas.setScaleX(2.5);

            //Feuille pentacle
            FeuillePentacle.setTranslateX(1400); // position X en pixels
            FeuillePentacle.setTranslateY(650);
            FeuillePentacle.setScaleX(0.65);
            FeuillePentacle.setScaleY(0.65);
            this.stackPane.getChildren().add(FeuillePentacle);

            for (int i = 0; i < this.tableauDimages.size(); i++){
                System.out.println("On entre dans le for au moins");
                if (this.tableauDimages.get(i).equals("symboleSpirale")){
                    //Symbole SymboleSpirale
                    symboleSpirale.setTranslateX(1400); // position X en pixels
                    symboleSpirale.setTranslateY(670);
                    symboleSpirale.setScaleX(0.1);
                    symboleSpirale.setScaleY(0.1);
                    symboleSpirale.toFront();
                    this.stackPane.getChildren().add(symboleSpirale);

                }

            }

            //Symbole Goute
            symboleGoutte.setTranslateX(1480); // position X en pixels
            symboleGoutte.setTranslateY(670);
            symboleGoutte.setScaleX(0.1);
            symboleGoutte.setScaleY(0.1);
            this.stackPane.getChildren().add(symboleGoutte);

            //Symbole Croix
            symboleCroix.setTranslateX(1320); // position X en pixels
            symboleCroix.setTranslateY(670);
            symboleCroix.setScaleX(0.1);
            symboleCroix.setScaleY(0.1);
            this.stackPane.getChildren().add(symboleCroix);
        }
    }

    public void ajouterSymboleaAfficher (String symbole){
        System.out.println("Connexion établie");
        tableauDimages.add(symbole);
    }


}
