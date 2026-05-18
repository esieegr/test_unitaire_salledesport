package fr.example;

import java.time.LocalDateTime;

public class Seance {
    private int id;
    private String nom;
    private LocalDateTime dateHeure;
    private int capaciteMax;

    //Constructeur
    public Seance(int id, String nom, LocalDateTime dateHeure, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.dateHeure = dateHeure;
        this.capaciteMax = capaciteMax;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }
    public int getCapaciteMax() {
        return capaciteMax;
    }

    //Méthode
    public String toString() {
        return "Seance:" + " id=" + this.id + ", nom=" + this.nom + ", dateHeure=" + this.dateHeure + ", capaciteMax=" + this.capaciteMax;
    }
}
