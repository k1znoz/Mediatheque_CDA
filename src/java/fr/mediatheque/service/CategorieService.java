package fr.mediatheque.service;

import fr.mediatheque.model.Categorie;
import fr.mediatheque.repository.CategorieRepository;

import java.util.List;

/**
 * Service simple pour Categorie.
 * Il délègue les opérations au repository.
 */
public class CategorieService {

    private final CategorieRepository categorieRepository = new CategorieRepository();

    public void save(Categorie categorie) {
        // Enregistre une catégorie.
        categorieRepository.save(categorie);
    }

    public Categorie findById(Long id) {
        // Recherche une catégorie par son id.
        return categorieRepository.findById(id);
    }

    public List<Categorie> findAll() {
        // Retourne toutes les catégories.
        return categorieRepository.findAll();
    }

    public Categorie update(Categorie categorie) {
        // Met à jour une catégorie.
        return categorieRepository.update(categorie);
    }

    public void delete(Long id) {
        // Supprime une catégorie par son id.
        categorieRepository.delete(id);
    }
}
