package fr.mediatheque.repository;

import fr.mediatheque.config.JpaUtil;
import fr.mediatheque.model.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Repository JPA pour l'entité Utilisateur.
 * Cette classe gère uniquement l'accès à la base de données.
 */
public class UtilisateurRepository {

    public void save(Utilisateur utilisateur) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour insérer l'utilisateur.
            transaction.begin();
            em.persist(utilisateur);
            transaction.commit();
        } catch (Exception e) {
            // En cas d'erreur, on annule la transaction.
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'insertion de l'utilisateur.", e);
        } finally {
            // Toujours fermer l'EntityManager.
            em.close();
        }
    }

    public Utilisateur findById(Long id) {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.find(Utilisateur.class, id);
        } finally {
            em.close();
        }
    }

    public List<Utilisateur> findAll() {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.createQuery("SELECT c FROM Utilisateur c", Utilisateur.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Utilisateur update(Utilisateur utilisateur) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour modifier l'utilisateur.
            transaction.begin();
            Utilisateur utilisateurMaj = em.merge(utilisateur);
            transaction.commit();
            return utilisateurMaj;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur.", e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour supprimer l'utilisateur.
            transaction.begin();
            Utilisateur utilisateur = em.find(Utilisateur.class, id);

            if (utilisateur != null) {
                em.remove(utilisateur);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur.", e);
        } finally {
            em.close();
        }
    }
}
