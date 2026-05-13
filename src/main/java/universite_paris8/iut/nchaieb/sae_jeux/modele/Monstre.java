package universite_paris8.iut.nchaieb.sae_jeux.modele;

public interface Monstre {
    public void infligerDegat(EntiteAllieeDeBase entite);
    public void ajouterPV(int soin);
    public void retirerPV(int degat);
    public int getPV();
    public String getId();
}
