package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class TourDeBase  extends Tour{
    public TourDeBase(int x, int y){
        super(150,1,x,y);
    }

    @Override
    public void infligerDegat(MonstreDeBase entite){
        if (entite.nombreDePV != 0){
        }
    }

    @Override
    public void infligerDegat(EntiteAllieeDeBase monstre) {
    }

    @Override
    public void ajouterPV(int soin) {
    }

    @Override
    public void retirerPV(int degat) {
    }
}
