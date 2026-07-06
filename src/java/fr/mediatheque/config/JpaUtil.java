package fr.mediatheque.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitaire JPA pour le projet Mediatheque.
 *
 * Rôle : centraliser la création de l'EntityManagerFactory et fournir
 * des EntityManager aux Repository.
 */
public final class JpaUtil {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("mediathequePU");

    private JpaUtil() {
        // Constructeur privé : cette classe ne doit pas être instanciée.
    }

    /**
     * Cree un EntityManager.
     *
     * Chaque appel retourne un nouvel EntityManager.
     * En pratique, les Repository appelleront cette methode
     * pour demarrer leurs operations de lecture/ecriture.
     */
    public static EntityManager createEntityManager() {
        return EMF.createEntityManager();
    }

    /**
     * Ferme proprement l'EntityManagerFactory.
     *
     * Cette methode est utile a l'arret de l'application
     * (par exemple dans un ServletContextListener plus tard).
     */
    public static void close() {
        if (EMF.isOpen()) {
            EMF.close();
        }
    }
}
