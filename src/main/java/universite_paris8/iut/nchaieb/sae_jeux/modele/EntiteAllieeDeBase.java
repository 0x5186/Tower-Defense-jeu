package universite_paris8.iut.nchaieb.sae_jeux.modele;

public abstract class EntiteAllieeDeBase implements Entite{
    protected int nombreDePV;
    protected int pvMax;
    protected int atq;
    private String id;
    public static int compteurID = 0;

    public EntiteAllieeDeBase(int atq){
        this.atq = atq;
        this.id ="E"+ this.compteurID;
        this.compteurID++;
    }

    public void infligerDegat(MonstreDeBase monstre){
        if (monstre.nombreDePV != 0){
            monstre.retirerPV(this.atq);
        }
    }

    public int getPV(){ return this.nombreDePV; }
    public String getId(){ return this.id; }
}