module universite_paris8.iut.nchaieb.sae_jeux {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens universite_paris8.iut.nchaieb.sae_jeux to javafx.fxml;
    exports universite_paris8.iut.nchaieb.sae_jeux;
    exports universite_paris8.iut.nchaieb.sae_jeux.modele;
    opens universite_paris8.iut.nchaieb.sae_jeux.modele to javafx.fxml;
}