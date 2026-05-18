package fr.example;

public class Prestation {
    private String code;
    private String libelle;
    private double prix;

    //Constructeur
    public Prestation(String code, String libelle, double prix) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
    }

    //Getter
    public String getCode() {
        return code;
    }
    public String getLibelle() {
        return libelle;
    }
    public double getPrix() {
        return prix;
    }

    //Méthode
    public String toString() {
        return "Prestation:" + "Code:" + code + "Libellé:" + libelle + "Prix:" + prix;
    }
}
