package fr.mediatheque.repository;

import fr.mediatheque.config.JpaUtil;
import fr.mediatheque.model.Editeur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Repository JPA pour l'entité Editeur.
 * Cette classe gère uniquement l'accès à la base de données.
 */
public class EditeurRepository {

    public void save(Editeur editeur) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour insérer l'éditeur.
            transaction.begin();
            em.persist(editeur);
            transaction.commit();
        } catch (Exception e) {
            // En cas d'erreur, on annule la transaction.
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'insertion de l'éditeur.", e);
        } finally {
            // Toujours fermer l'EntityManager.
            em.close();
        }
    }

    public Editeur findById(Long id) {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.find(Editeur.class, id);
        } finally {
            em.close();
        }
    }

    public List<Editeur> findAll() {
        EntityManager em = JpaUtil.createEntityManager();

        try {
            return em.createQuery("SELECT e FROM Editeur e", Editeur.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Editeur update(Editeur editeur) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour modifier l'éditeur.
            transaction.begin();
            Editeur editeurMaj = em.merge(editeur);
            transaction.commit();
            return editeurMaj;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'éditeur.", e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour supprimer l'éditeur.
            transaction.begin();
            Editeur editeur = em.find(Editeur.class, id);

            if (editeur != null) {
                em.remove(editeur);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'éditeur.", e);
        } finally {
            em.close();
        }
    }
}
