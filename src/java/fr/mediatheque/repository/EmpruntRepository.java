package fr.mediatheque.repository;

import fr.mediatheque.config.JpaUtil;
import fr.mediatheque.model.Emprunt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Repository JPA pour l'entité Categorie.
 * Cette classe gère uniquement l'accès à la base de données.
 */
public class EmpruntRepository {

    public void save(Emprunt categorie) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour insérer la catégorie.
            transaction.begin();
            em.persist(categorie);
            transaction.commit();
        } catch (Exception e) {
            // En cas d'erreur, on annule la transaction.
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'insertion de la catégorie.", e);
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
            return em.createQuery("SELECT c FROM Emprunt c", Emprunt.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Emprunt update(Emprunt categorie) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour modifier la catégorie.
            transaction.begin();
            Emprunt categorieMaj = em.merge(categorie);
            transaction.commit();
            return categorieMaj;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de la catégorie.", e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            // Début de transaction pour supprimer la catégorie.
            transaction.begin();
            Emprunt categorie = em.find(Emprunt.class, id);

            if (categorie != null) {
                em.remove(categorie);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de la catégorie.", e);
        } finally {
            em.close();
        }
    }
}
