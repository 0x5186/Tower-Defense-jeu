package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Symbole;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class MonObservateurSymbole implements ObservableList<Symbole> {


    @Override
    public void addListener(ListChangeListener<? super Symbole> listChangeListener) {
    }

    @Override
    public void removeListener(ListChangeListener<? super Symbole> listChangeListener) {

    }

    @Override
    public void addListener (InvalidationListener invalidationListener){

    }
}
