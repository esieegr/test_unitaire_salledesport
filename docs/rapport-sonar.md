# Rapport SonarQube — tp-salledesport (1 page)

Date: 2026-05-18

Projet: tp-salledesport

Objectif
- Rendre un mini-projet Java avec : 10 tests TDD exécutés, corriger 3 code smells majeurs, passer le Quality Gate SonarQube (Maintainability >= B) et produire un rapport avant/après.

Résumé (une phrase)
- Après refactorings sûrs et ajout de tests, le projet passe le Quality Gate SonarQube (Quality Gate: Passed) et la note de maintainability est A. Les tests (10) sont verts.

Avant / Après (captures)
- Capture 1 — Dashboard Projects (before/after) : docs/images/sonar-projects.png
- Capture 2 — Project Overview (before) : docs/images/sonar-overview-before.png
- Capture 3 — Project Overview (after) : docs/images/sonar-overview-after.png
- Capture 4 — Activity / Issues trend : docs/images/sonar-activity.png

Remarques clés
- Tests : 10 tests JUnit5, tous verts (commande run: `mvn -DskipTests=false test`).
- Refactors appliqués (3) :
  1. `Adherent` : champs rendus privés + copie défensive des listes.
  2. `SalleDeSport` : collections rendues privées et getters retournent des listes non modifiables.
  3. `Reservation` : `getPrestations()` retourne désormais une liste non modifiable.
- Sonar : configuration ajoutée dans `pom.xml` pour éviter le double-indexing (sonar.sources, sonar.tests, sonar.exclusions). Le project key utilisé : `tp-salledesport`.

## Détails des corrections appliquées (pour Sonar / Maintainability)

1) Encapsulation des champs exposés — `Adherent.java`
  - Avant : champs publics `id`, `nom`, `abonnement`, `reservations`.
  - Changement : rendu `private` + ajout de getters publics.
  - Impact Sonar : corrige les code smells liés aux champs publics mutables et améliore la conception (réduction des issues de type "Mutable fields should not be public").

2) Copie défensive de collections — `Adherent.java` (constructeur)
  - Avant : la liste `reservations` pouvait être partagée par aliasing avec l'appelant.
  - Changement : utilisation de `this.reservations = (reservations == null) ? new ArrayList<>() : new ArrayList<>(reservations);`.
  - Impact Sonar : évite les effets de bord et potentiels NPE ; réduit les issues sur l'utilisation non sécurisée des collections partagées.

3) Exposition contrôlée des collections — `SalleDeSport.java` et `Reservation.java`
  - Avant : getters retournaient les listes internes (modifiable) et champs étaient `ArrayList` publics/paresseux.
  - Changement : champs déclarés `private List<...>` et getters retournent `Collections.unmodifiableList(...)`.
  - Impact Sonar : empêche la modification externe de l'état interne (smell "exposed internal representation") et améliore l'API publique.

4) Utilisation des interfaces pour types de champs — `SalleDeSport.java`
  - Changement : remplacer `ArrayList<...>` par `List<...>` pour les déclarations de champs et signatures publiques.
  - Impact Sonar : favorise l'abstraction et réduit les warnings liés à l'utilisation de types concrets dans les API.

5) Tests et sécurité d'API
  - Vérification unitaire que les changements sont sûrs : tous les tests existants (10) passent après les refactors.
  - Impact Sonar : les changements couverts par tests ont permis de conserver le comportement métier tout en corrigeant des smells.

## Actions recommandées (prioritaires) — patches suggérés

Voici 6 actions concrètes, ordinées par gain rapide -> effort, avec extrait de code proposé :

1) Remplacer `RuntimeException` par une exception plus précise — `SalleDeSport.java`
  - Exemple :
```java
public Adherent trouverAdherent(int id) {
   return adherents.stream()
      .filter(a -> a.getId() == id)
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Adherent introuvable: " + id));
}
```
  - Pourquoi : Sonar signale l'utilisation de RuntimeException générique ; une exception précise améliore la lisibilité et réduit les warnings.

2) Réduire la complexité cyclomatique en adoptant Streams — `Adherent.depensesTotales()`
```java
public double depensesTotales() {
   return reservations.stream()
      .filter(r -> r.getStatut() == StatutReservation.CONFIRMEE)
      .mapToDouble(Reservation::coutPrestation)
      .sum();
}
```
  - Pourquoi : réduit la complexité et le nombre d'issues signalées par Sonar sur les méthodes longues.

3) Standardiser `toString()` avec `StringBuilder` ou `String.format` et éviter d'épingler des collections volumineuses
  - Exemple simple :
```java
public String toString() {
   return String.format("Seance{id=%d, nom=%s, date=%s}", id, nom, dateHeure);
}
```
  - Pourquoi : meilleures performances et moins de warnings sur concaténation de chaînes.

4) Marquer les champs immuables `final` quand possible
  - Fichiers cibles : `Prestation`, `Seance`, classes `Abonnement*`.
  - Exemple : `private final String code; private final double prix;`.
  - Pourquoi : Sonar récompense l'immuabilité (réduit risques et issues).

5) Augmenter la couverture des tests (couvrir cas d'erreur)
  - Ajouter tests pour : `trouverAdherent` (assertThrows), tentative de modification de `getPrestations()` (vérifier qu'UnsupportedOperationException est levée), cas limites `depensesTotales`.
  - Pourquoi : Sonar montre 0% de coverage sur certaines lignes; augmenter la couverture réduit la sévérité de Quality Gate liée à la couverture.

6) Moderniser petites méthodes en API stream (ex: `adherentsAvecSauna()`)
```java
public List<Adherent> adherentsAvecSauna() {
   return adherents.stream()
      .filter(a -> a.getAbonnement() != null && a.getAbonnement().permetAccesSauna())
      .collect(Collectors.toList());
}
```

## Vérification / commandes
- Exécuter les tests après modifications :
```powershell
mvn -DskipTests=false test
```
- Lancer Sonar (après set du token) :
```powershell
$env:SONAR_TOKEN = 'YOUR_TOKEN'
mvn -X sonar:sonar
```

---
Ces ajouts détaillent les corrections déjà faites et proposent les actions suivantes pour réduire davantage les issues Sonar. Dis-moi si tu veux que je prépare les patches (A) pour appliquer automatiquement ces changements (tests vérifiés) ou si tu préfères recevoir seulement les extraits de code (B) à appliquer toi‑même.

Commandes utilisées
- Lancer tests :
```powershell
mvn -DskipTests=false test
```
- Lancer analyse Sonar (PowerShell) :
```powershell
# define token first, for example:
# $env:SONAR_TOKEN = 'YOUR_TOKEN'
mvn -X sonar:sonar
```