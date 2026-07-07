package fr.mediatheque.repository;

import fr.mediatheque.config.JpaUtil;
import fr.mediatheque.model.Auteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Repository JPA pour l'entité Auteur.
 * Cette classe gère uniquement l'accès à la base de données.
 */
public class AuteurRepository {

    public void save(Auteur auteur) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour insérer l'auteur.
            transaction.begin();
            em.persist(auteur);
            transaction.commit();
        } catch (Exception e) {
            // En cas d'erreur, on annule la transaction.
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'insertion de l'auteur.", e);
        } finally {
            // Toujours fermer l'EntityManager.
            em.close();
        }
    }

    public Auteur findById(Long id) {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.find(Auteur.class, id);
        } finally {
            em.close();
        }
    }

    public List<Auteur> findAll() {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.createQuery("SELECT a FROM Auteur a", Auteur.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Auteur update(Auteur auteur) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour modifier l'auteur.
            transaction.begin();
            Auteur auteurMaj = em.merge(auteur);
            transaction.commit();
            return auteurMaj;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'auteur.", e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour supprimer l'auteur.
            transaction.begin();
            Auteur auteur = em.find(Auteur.class, id);

            if (auteur != null) {
                em.remove(auteur);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'auteur.", e);
        } finally {
            em.close();
        }
    }
}
