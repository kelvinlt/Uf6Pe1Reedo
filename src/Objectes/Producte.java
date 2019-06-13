package Objectes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producte {
    @Id
    String nom;
    double preu;

    public Producte(String nom, double preu) {
        this.nom = nom;
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Producte{" + "nom=" + nom + ", preu=" + preu + '}';
    }
    
    
}
