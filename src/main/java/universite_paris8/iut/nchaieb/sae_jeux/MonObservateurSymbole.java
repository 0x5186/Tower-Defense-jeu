package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Symbole;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;

import java.util.*;

public class MonObservateurSymbole implements ListChangeListener<Symbole> {

    private InterfaceVue interfaceVue;

    public MonObservateurSymbole(InterfaceVue interfaceVue){
        this.interfaceVue = interfaceVue;
    }

    @Override
    public void onChanged(Change<? extends Symbole> change){
        while(change.next()){
            if (change.wasAdded()){
                for (Symbole symbole : change.getAddedSubList()){
                    this.interfaceVue.afficherUnSeulSymbole(symbole.getType());
                }
            }
        }
    }

}
