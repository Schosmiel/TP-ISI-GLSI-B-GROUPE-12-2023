package groupe12.EGA.payload;


import groupe12.EGA.model.Compte;

public class PostCompte {
    private long proprietaire;
    private Compte.TypeCompte typeCompte;


    public Compte.TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(Compte.TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public long getProprietaire() {
        return proprietaire;
    }



}
