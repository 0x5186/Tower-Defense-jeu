package universite_paris8.iut.nchaieb.sae_jeux.modele;

public interface Entite {
    public void infligerDegat(MonstreDeBase entite);
    public void ajouterPV(int soin);
    public void retirerPV(int degat);
    public int getPV();
    public String getId();
}
