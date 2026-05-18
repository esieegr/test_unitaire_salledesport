# TDD notes — tp-salledesport

Résumé des tests (10) et notes Red / Green / Refactor

1) AdherentTest.depensesTotales_noResa_returnsZero
  - Intention (Red): Un adhérent sans réservation doit dépenser 0.
  - Green: Méthode `depensesTotales()` renvoie 0.0.
  - Refactor: sécurisation de la liste `reservations` (copie défensive) pour éviter NPE externes.

2) AdherentTest.depensesTotales_mixedStatuts_onlyConfirmed
  - Intention: Seules les prestations de reservations confirmées comptent.
  - Green: annuler() doit changer le statut et exclure les prestations.
  - Refactor: `Reservation.getPrestations()` protégé contre mutation externe.

3) AdherentTest.reservationsFutures_returnsFutureOnly
  - Intention: filtrer les réservations futures.
  - Green: `reservationsFutures()` renvoie uniquement celles dont la date est après maintenant.
  - Refactor: aucune modification fonctionnelle nécessaire.

4–9) ReservationTest.* (6 tests)
  - Intention: Covers creation, ajout prestation, calculs de coût, annulation et null-safety.
  - Green: Tous les comportements sont corrects.
  - Refactor: `Reservation` encapsule maintenant sa liste de prestations.

10) TestCoutPrestation
  - Intention: vérifier le calcul des coûts d'une prestation.
  - Green: `coutPrestation()` additionne les prix correctement.
