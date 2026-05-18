package fr.example;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private Seance seance;
    private List<Prestation> prestations;
    private StatutReservation statut;

    //Constructeur
    public Reservation(Seance seance) {
        this.seance = seance;
        this.prestations = new ArrayList<>();
        this.statut = StatutReservation.CONFIRMEE;
    }
    //Getters
    public Seance getSeance() {
        return seance;
    }
    public List<Prestation> getPrestations() {
        return java.util.Collections.unmodifiableList(prestations);
    }
    public StatutReservation getStatut() {
        return statut;
    }

    //Méthode
    public void ajouterPrestation(Prestation p) {
        if (p !=null && statut ==StatutReservation.CONFIRMEE) {
            prestations.add(p);
        }
    }
    public double coutPrestation() {
        double total=0.0;
        for (Prestation p : prestations) {
            total += p.getPrix();
        }
        return total;
    }
    public void annuler() {
        this.statut=StatutReservation.ANNULEE;
    }
    public String toString() {
        return "Reservation:" + "seance=" + seance + "prestations=" + prestations + "statut=" + statut + "Cout:" + coutPrestation();
    }
}
