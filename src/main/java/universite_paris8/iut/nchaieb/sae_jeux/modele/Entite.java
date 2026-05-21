package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Entite implements EntiteInterface{

    protected int nombreDePV;
    protected int pvMax;
    //    protected String biome;
    private String id;
    public static int compteurID = 0;

    public Entite(){


//        this.biome = biome;
        this.id ="E"+ this.compteurID;
        this.compteurID++;
    }






    public int  getPV(){
        return this.nombreDePV;
    }

    public String getId(){
        return this.id;
    }



}
