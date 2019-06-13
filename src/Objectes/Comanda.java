package Objectes;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comanda {
    @Id
    int id;
    Date data;
    List<Producte> productes;

    public Comanda(int id, Date data, List<Producte> productes) {
        this.id = id;
        this.data = data;
        this.productes = productes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Producte> getProductes() {
        return productes;
    }

    public void setProductes(List<Producte> productes) {
        this.productes = productes;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", data=" + data + ", productes=" + productes + '}';
    }
    
}
