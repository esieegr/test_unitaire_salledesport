package fr.example;
import java.time.LocalDate;

public abstract class Abonnement {
    protected String reference;
    protected LocalDate dateDebut;
    protected int dureeMois;
    protected double prixMensuel;

    //Constructeur
    public Abonnement(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel) {
        this.reference = reference;
        this.dateDebut = dateDebut;
        this.dureeMois = dureeMois;
        this.prixMensuel = prixMensuel;
    }
    //Méthodes
    public LocalDate dateFin() {
        return dateDebut.plusMonths(dureeMois);
    }
    public double coutTotal() {
        return prixMensuel * dureeMois;
    }
    //Méthodes abstraites
    public abstract boolean permetAccesSauna();
    public abstract int creditsCoachInclus();

    public String toString() {
        return "Abonnement:" + "Ref:" + reference + "Date début:" + dateDebut + "Durée:" + dureeMois + "Prix par mois:" + prixMensuel;
    }
}
