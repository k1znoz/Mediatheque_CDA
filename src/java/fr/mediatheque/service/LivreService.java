package fr.mediatheque.service;

import fr.mediatheque.model.Livre;
import fr.mediatheque.repository.LivreRepository;

import java.util.List;

/**
 * Service simple pour Livre.
 * Il délègue les opérations au repository.
 */
public class LivreService {

    private final LivreRepository livreRepository = new LivreRepository();

    public void save(Livre livre) {
        // Enregistre un livre.
        livreRepository.save(livre);
    }

    public Livre findById(Long id) {
        // Recherche un livre par son id.
        return livreRepository.findById(id);
    }

    public List<Livre> findAll() {
        // Retourne tous les livres.
        return livreRepository.findAll();
    }

    public Livre update(Livre livre) {
        // Met à jour un livre.
        return livreRepository.update(livre);
    }

    public void delete(Long id) {
        // Supprime un livre par son id.
        livreRepository.delete(id);
    }
}
