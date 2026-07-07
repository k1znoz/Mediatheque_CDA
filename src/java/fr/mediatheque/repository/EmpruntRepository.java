package fr.mediatheque.repository;

import fr.mediatheque.config.JpaUtil;
import fr.mediatheque.model.Emprunt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Repository JPA pour l'entité Emprunt.
 * Cette classe gère uniquement l'accès à la base de données.
 */
public class EmpruntRepository {

    public void save(Emprunt emprunt) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour insérer l'emprunt.
            transaction.begin();
            em.persist(emprunt);
            transaction.commit();
        } catch (Exception e) {
            // En cas d'erreur, on annule la transaction.
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'insertion de l'emprunt.", e);
        } finally {
            // Toujours fermer l'EntityManager.
            em.close();
        }
    }

    public Emprunt findById(Long id) {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.find(Emprunt.class, id);
        } finally {
            em.close();
        }
    }

    public List<Emprunt> findAll() {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.createQuery("SELECT e FROM Emprunt e", Emprunt.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Emprunt update(Emprunt emprunt) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour modifier l'emprunt.
            transaction.begin();
            Emprunt empruntMaj = em.merge(emprunt);
            transaction.commit();
            return empruntMaj;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'emprunt.", e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour supprimer l'emprunt.
            transaction.begin();
            Emprunt emprunt = em.find(Emprunt.class, id);

            if (emprunt != null) {
                em.remove(emprunt);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'emprunt.", e);
        } finally {
            em.close();
        }
    }
}
