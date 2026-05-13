package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Terrain {

    private int[][] codeTuiles = {{1,1,1},{2,1,2},{3,3,3},{3,3,3}};



    public int hauteur() {return this.codeTuiles.length;}
    public int largeur() {return this.codeTuiles[0].length;}
    public int codeTuile(int ligne, int col) {return codeTuiles[ligne][col];}
}
