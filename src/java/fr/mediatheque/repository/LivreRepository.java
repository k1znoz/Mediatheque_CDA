package fr.mediatheque.repository;

import fr.mediatheque.config.JpaUtil;
import fr.mediatheque.model.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Repository JPA pour l'entité Livre.
 * Cette classe gère uniquement l'accès à la base de données.
 */
public class LivreRepository {

    public void save(Livre livre) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour insérer le livre.
            transaction.begin();
            em.persist(livre);
            transaction.commit();
        } catch (Exception e) {
            // En cas d'erreur, on annule la transaction.
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'insertion du livre.", e);
        } finally {
            // Toujours fermer l'EntityManager.
            em.close();
        }
    }

    public Livre findById(Long id) {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.find(Livre.class, id);
        } finally {
            em.close();
        }
    }

    public List<Livre> findAll() {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.createQuery("SELECT c FROM Livre c", Livre.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Livre update(Livre livre) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour modifier le livre.
            transaction.begin();
            Livre livreMaj = em.merge(livre);
            transaction.commit();
            return livreMaj;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour du livre.", e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour supprimer le livre.
            transaction.begin();
            Livre livre = em.find(Livre.class, id);

            if (livre != null) {
                em.remove(livre);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression du livre.", e);
        } finally {
            em.close();
        }
    }
}
