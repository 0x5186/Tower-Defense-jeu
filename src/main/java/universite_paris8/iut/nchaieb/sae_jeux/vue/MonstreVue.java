package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;

import java.util.HashMap;

public class MonstreVue {
    private StackPane stackPane;
    private HashMap<Entite, ImageView> hashMap = new HashMap<>();
    private HashMap<Entite, Timeline> hashMapAnimation = new HashMap<>();
    Image squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));
    Image sorcier = new Image(Main.class.getResourceAsStream("images/sorcier.png"));

    private final int DECALAGE_X = 105;
    private final int DECALAGE_Y = 120;

    public MonstreVue(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void ajouterSprite(Monstre monstre) {
        ImageView iv = new ImageView();
        if (monstre instanceof Squelette) {
            iv = new ImageView(squelette);
        } else if (monstre instanceof Sorcier) {
            iv = new ImageView(sorcier);
        }

        iv.translateXProperty().bind(monstre.posXProperty().subtract(DECALAGE_X));
        iv.translateYProperty().bind(monstre.posYProperty().subtract(DECALAGE_Y));

        this.hashMap.put(monstre, iv);

        if (monstre instanceof Squelette) {
            iv.setScaleX(0.25);
            iv.setScaleY(0.25);
        }

        iv.setViewport(new Rectangle2D(0, 0, 240, 240));
        this.stackPane.getChildren().add(iv);
    }

    public void retirer(Entite entite) {
        ImageView iv = hashMap.get(entite);
        if (iv != null) {
            iv.setImage(null);
            this.stackPane.getChildren().remove(iv);
            this.hashMap.remove(entite);
        }
    }

    public void stopAnimation(Entite entite) {
        if (this.hashMapAnimation.containsKey(entite)) {
            Timeline timeline = this.hashMapAnimation.get(entite);
            timeline.stop();
            this.hashMapAnimation.remove(entite);
        }
    }

    public void animationMarche(Entite monstre) {
        ImageView iv = this.hashMap.get(monstre);
        int largeurCase = 240;
        int hauteurCase = 240;

        if (monstre instanceof Squelette) {
            iv.setScaleX(0.25);
            iv.setScaleY(0.25);

            int[] frameIndex = {13};
            Timeline squeletteMarche = new Timeline(
                    new KeyFrame(Duration.millis(90), e -> {
                        int x, y;
                        if (frameIndex[0] < 12) {
                            x = frameIndex[0] % 6;
                            y = frameIndex[0] / 6;
                        } else {
                            x = frameIndex[0] - 12;
                            y = 2;
                        }
                        frameIndex[0]++;
                        if (frameIndex[0] == 15) frameIndex[0] = 0;
                        iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    })
            );
            this.hashMapAnimation.put(monstre, squeletteMarche);
            squeletteMarche.setCycleCount(Timeline.INDEFINITE);
            squeletteMarche.play();
        }
    }

    public void animationAttaque(Entite monstre) {
        ImageView iv = this.hashMap.get(monstre);
        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {13};

        Timeline squeletteAttaque = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    int x, y;
                    if (frameIndex[0] < 25) {
                        x = frameIndex[0] % 6;
                        y = frameIndex[0] / 6;
                    } else {
                        x = frameIndex[0] - 24;
                        y = 4;
                    }
                    frameIndex[0]++;
                    if (frameIndex[0] == 27) frameIndex[0] = 12;
                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                })
        );
        this.hashMapAnimation.put(monstre, squeletteAttaque);
        squeletteAttaque.setCycleCount(10);
        squeletteAttaque.play();
    }

    public void animationMort(Entite monstre) {
        ImageView iv = this.hashMap.get(monstre);
        if (iv == null) return;

        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {27};

        if (this.hashMapAnimation.containsKey(monstre)) {
            Timeline timeline = this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

        Timeline squeletteMort = new Timeline(
                new KeyFrame(Duration.millis(120), e -> {
                    int x = frameIndex[0] % 6;
                    int y = frameIndex[0] / 6;
                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    frameIndex[0]++;
                })
        );
        squeletteMort.setCycleCount(7);

        squeletteMort.setOnFinished(e -> {
            FadeTransition fade = new FadeTransition(Duration.seconds(2), iv);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);

            fade.setOnFinished(fadeEvent -> {
                this.retirer(monstre);
            });
            fade.play();
        });

        squeletteMort.play();
        System.out.println("Animation de mort jouée");
    }
}