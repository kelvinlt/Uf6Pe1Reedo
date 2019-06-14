package leekelvinuf6pe1test;

import Objectes.Client;
import Objectes.Comanda;
import Objectes.Producte;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class LeeKelvinUf6Pe1Test {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcionObj = 0;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/TestUf6.odb");
        EntityManager em = emf.createEntityManager();

        try {
            while (opcionObj != 6) {
                mmenu();
                opcionObj = Integer.parseInt(br.readLine());
                System.out.println("------------------------------------------------------------------------");

                switch (opcionObj) {
                    case 1:
                        System.out.println("1-Afegir un nou Producte");
                        System.out.println("Introdueix un nom:");
                        String nPNom = br.readLine();
                        System.out.println("Introdueix un preu:");
                        Double nPPreu = Double.parseDouble(br.readLine());

                        Producte newProducte = new Producte(nPNom, nPPreu);
                        if (checkProducte(em, newProducte.getNom()) == false) {
                            em.getTransaction().begin();
                            em.persist(newProducte);
                            em.getTransaction().commit();
                            System.out.println("Se ha introducido correctamente: " + newProducte.getNom());
                        } else {
                            System.out.println("No se ha introducido ya que existe el producto: " + newProducte.getNom());
                        }
                        break;
                    case 2:
                        System.out.println("2-Afegir un nou Client");
                        System.out.println("Introdueix un nom:");
                        String nCNom = br.readLine();

                        //Client nCLient = new Client(nCNom, comandes);
                        em.getTransaction().begin();
                        //em.persist(nCLient);
                        em.getTransaction().commit();

                        break;
                    case 3:
                        System.out.println("3-Afegir una nova Comanda");
                        List<Producte> productes = new ArrayList();
                        Date newDate = new Date();

                        List<Producte> Productes1 = allProductos(em);
                        int i = 0;
                        int opcionComanda = 0;
                        for (Producte t : Productes1) {
                            System.out.println(i + ": " + t);
                            i++;
                        }
                        System.out.println(i + ": Salir de opciones");

                        int numeroFinal = i + 1;

                        while (opcionComanda != numeroFinal) {
                            System.out.println("Escoge el numero de un producto");
                            opcionComanda = Integer.parseInt(br.readLine());

                            if (opcionComanda == numeroFinal) {
                                opcionComanda = 0;
                            } else {
                                productes.add(Productes1.get(opcionComanda));
                            }

                        }
                        Comanda nComanda = new Comanda(newDate, productes);

                        break;
                    case 4:
                        System.out.println("4-Llistar tots els Productes");
                        List<Producte> Productes2 = allProductos(em);

                        for (Producte t : Productes2) {
                            System.out.println(t);
                        }

                        break;
                    case 5:
                        System.out.println("5-Llistar les dades d'un Client i totes les seves Comandes");
                        System.out.println("Escribe el nombre del cliente");
                        String nombreCliente = br.readLine();
                        if (checkClient(em, nombreCliente) == true) {
                            allComandasFromClient(em, nombreCliente);
                        } else {
                            System.out.println("No existe el cliente");
                        }

                        break;
                    case 6:
                        System.out.println("6-Sortida del programa");
                        em.close();
                        break;
                }
            }

            Producte testGet = em.find(Producte.class, "agua");
            if (testGet != null) {
                System.out.println(testGet);
            } else {
                System.out.println("No se ha encontrado nada");
            }
            em.close();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }

    public static void mmenu() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("---------------------------Menu Principal-------------------------------");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("1-Afegir un nou Producte");
        System.out.println("2-Afegir un nou Client");
        System.out.println("3-Afegir una nova Comanda");
        System.out.println("4-Llistar tots els Productes");
        System.out.println("5-Llistar les dades d'un Client i totes les seves Comandes");
        System.out.println("6-Sortida del programa");
        System.out.println("------------------------------------------------------------------------");
    }

    public static boolean checkProducte(EntityManager em, String nombreProducto) {
        Producte producto = em.find(Producte.class, nombreProducto);
        if (producto == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkClient(EntityManager em, String nombreCliente) {
        Client cliente = em.find(Client.class, nombreCliente);
        if (cliente == null) {
            return false;
        } else {
            return true;
        }
    }

    public static List<Producte> allProductos(EntityManager em) {
        TypedQuery<Producte> consulta = em.createQuery("SELECT t FROM Producte t", Producte.class);
        List<Producte> Productes = consulta.getResultList();
        return Productes;
    }

    public static void allComandasFromClient(EntityManager em, String nom) {
        List<Comanda> Comandas = new ArrayList<>();
        Client cliente = em.find(Client.class, nom);
        Comandas = cliente.getComandes();
        if (Comandas == null) {
            System.out.println("El cliente no tiene comandas");
        } else {
            for (Comanda t : Comandas) {
                System.out.println(t);
            }
        }
    }

}
