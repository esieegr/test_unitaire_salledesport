package fr.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class ReservationTest {

    @Test
    public void newReservation_shouldBeConfirmee() {
        Seance s = new Seance(1, "S", LocalDateTime.now(), 10);
        Reservation r = new Reservation(s);
        assertEquals(StatutReservation.CONFIRMEE, r.getStatut());
    }

    @Test
    public void ajouterPrestation_shouldAddToList() {
        Seance s = new Seance(1, "S", LocalDateTime.now(), 10);
        Reservation r = new Reservation(s);
        Prestation p = new Prestation("P1", "Lib", 12.5);
        r.ajouterPrestation(p);
        assertTrue(r.getPrestations().contains(p));
    }

    @Test
    public void coutPrestation_sauna_coach_returns15630() {
        Seance s = new Seance(1, "S", LocalDateTime.now(), 10);
        Reservation r = new Reservation(s);
        Prestation sauna = new Prestation("SA", "Sauna", 5.80);
        Prestation coach = new Prestation("CO", "Coach", 150.50);
        r.ajouterPrestation(sauna);
        r.ajouterPrestation(coach);
        assertEquals(156.30, r.coutPrestation(), 1e-6);
    }

    @Test
    public void annuler_shouldChangeStatutAnnulee() {
        Seance s = new Seance(1, "S", LocalDateTime.now(), 10);
        Reservation r = new Reservation(s);
        r.annuler();
        assertEquals(StatutReservation.ANNULEE, r.getStatut());
    }

    @Test
    public void ajouterPrestation_null_shouldNotThrow() {
        Seance s = new Seance(1, "S", LocalDateTime.now(), 10);
        Reservation r = new Reservation(s);
        r.ajouterPrestation(null);
        assertTrue(r.getPrestations().isEmpty());
    }

    @Test
    public void ajouterPrestation_afterAnnulation_ignored() {
        Seance s = new Seance(1, "S", LocalDateTime.now(), 10);
        Reservation r = new Reservation(s);
        r.annuler();
        Prestation p = new Prestation("P1", "Lib", 10.0);
        r.ajouterPrestation(p);
        assertFalse(r.getPrestations().contains(p));
    }
}
