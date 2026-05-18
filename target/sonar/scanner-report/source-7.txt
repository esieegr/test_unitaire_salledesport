package fr.example;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        //Création prestations
        Prestation sauna = new Prestation("Sauna", "Accès sauna", 5.80);
        Prestation coach = new Prestation("Coach", "Cours perso", 150.50);

        //Création seances
        Seance bodyPump = new Seance(1, "BodyPump",
                LocalDateTime.of(2026, 3, 10, 18,0), 10);
        Seance yoga = new Seance(2, "Yoga",
                LocalDateTime.of(2026, 3, 17, 19, 0), 20);

        //Création résa
        Reservation r1 = new Reservation(bodyPump);
        r1.ajouterPrestation(sauna);
        r1.ajouterPrestation(coach);

        //Affichage
        System.out.println("Résaervation :");
        System.out.println(r1);
        System.out.println("Cout prestations =" +r1.coutPrestation() + "euros");

        //Annulation
        r1.annuler();
        System.out.println("Après annlation :");
        System.out.println("Statut = " + r1.getStatut());
    }
}
