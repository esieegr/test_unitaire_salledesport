package fr.example;

import java.util.ArrayList;
import java.util.List;

public class SalleDeSport {

    private List<Adherent> adherents;
    private List<Seance> seances;

    //Constructeur
    public SalleDeSport () {
        adherents = new ArrayList<Adherent>();
        seances = new ArrayList<Seance>();
    }
    //Getters
    public List<Adherent> getAdherents() {
        return java.util.Collections.unmodifiableList(this.adherents);
    }
    public List<Seance> getSeances() {
        return java.util.Collections.unmodifiableList(this.seances);
    }

    //Méthodes
    public void ajouterAdherent(Adherent a) {
      if (a !=null) {
          this.adherents.add(a);
        }
    }
    public void ajouterSeance(Seance s) {
        if (s !=null) {
            seances.add(s);
        }
    }
    public List<Seance> seancesDisponibles() {
        return seances;
    }
    public List<Adherent> adherentsAvecSauna() {
        List<Adherent> resultat = new ArrayList<>();
        for (Adherent a : adherents) {
            if (a.getAbonnement() !=null && a.getAbonnement().permetAccesSauna()) {
                resultat.add(a);
            }
        }
        return resultat;
    }
    public Adherent trouverAdherent(int id) {
        for (Adherent a : adherents) {
            if (a.getId() ==id) {
                return a;
            }
        }

        throw new RuntimeException("Adhérent introuvable par id=:" + id);

    }

    public String toString() {
            return "Salle de sport:" + "nombre d'adhérents:" + adherents.size() + "nombre de séances:" + seances.size();
    }

}
