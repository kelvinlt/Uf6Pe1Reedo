package Objectes;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
    @Id String nom;
    List<Comanda> comandes;

    public Client(String nom, List<Comanda> comandes) {
        this.nom = nom;
        this.comandes = comandes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Comanda> getComandes() {
        return comandes;
    }

    public void setComandes(List<Comanda> comandes) {
        this.comandes = comandes;
    }

    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", comandes=" + comandes + '}';
    }
}
