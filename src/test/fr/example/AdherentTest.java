package fr.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

public class AdherentTest {

    @Test
    public void depensesTotales_noResa_returnsZero() {
        Abonnement a = new AbonnementBasic("R1", LocalDate.now(), 1, 10.0);
        Adherent adh = new Adherent(1, "Nom", a, null);
        assertEquals(0.0, adh.depensesTotales(), 1e-6);
    }

    @Test
    public void depensesTotales_mixedStatuts_onlyConfirmed() {
        Abonnement a = new AbonnementBasic("R1", LocalDate.now(), 1, 10.0);
        Adherent adh = new Adherent(1, "Nom", a, null);
        Seance s = new Seance(1, "S", LocalDateTime.now(), 10);
        Reservation r1 = adh.reserver(s);
        r1.ajouterPrestation(new Prestation("P1", "X", 5.0));
        Reservation r2 = adh.reserver(s);
        r2.ajouterPrestation(new Prestation("P2", "Y", 7.0));
        r2.annuler();
        assertEquals(5.0, adh.depensesTotales(), 1e-6);
    }

    @Test
    public void reservationsFutures_returnsFutureOnly() {
        Abonnement a = new AbonnementBasic("R1", LocalDate.now(), 1, 10.0);
        Adherent adh = new Adherent(1, "Nom", a, null);
        Seance past = new Seance(1, "P", LocalDateTime.now().minusDays(1), 10);
        Seance future = new Seance(2, "F", LocalDateTime.now().plusDays(1), 10);
        adh.reserver(past);
        adh.reserver(future);
        List<Reservation> fut = adh.reservationsFutures();
        assertEquals(1, fut.size());
        assertEquals(future, fut.get(0).getSeance());
    }
}
