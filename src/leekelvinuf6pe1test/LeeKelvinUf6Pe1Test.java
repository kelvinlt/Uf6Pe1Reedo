package leekelvinuf6pe1test;

import Objectes.Client;
import Objectes.Producte;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
                            em.close();
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
                        em.close();
                        break;
                    case 3:
                        System.out.println("3-Afegir una nova Comanda");

                        break;
                    case 4:
                        System.out.println("4-Llistar tots els Productes");

                        TypedQuery<Producte> consulta = em.createQuery("SELECT t FROM Producte t", Producte.class);
                        List<Producte> Productes = consulta.getResultList();

                        for (Producte t : Productes) {
                            System.out.println(t);
                        }

                        break;
                    case 5:
                        System.out.println("5-Llistar les dades d'un Client i totes les seves Comandes");

                        break;
                    case 6:
                        System.out.println("6-Sortida del programa");

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

}
