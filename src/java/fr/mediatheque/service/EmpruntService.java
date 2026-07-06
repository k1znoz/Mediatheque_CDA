package fr.mediatheque.service;

import fr.mediatheque.model.Emprunt;
import fr.mediatheque.repository.EmpruntRepository;

import java.util.List;

/**
 * Service simple pour Emprunt.
 * Il délègue les opérations au repository.
 */
public class EmpruntService {

    private final EmpruntRepository empruntRepository = new EmpruntRepository();

    public void save(Emprunt emprunt) {
        // Enregistre un emprunt.
        empruntRepository.save(emprunt);
    }

    public Emprunt findById(Long id) {
        // Recherche un emprunt par son id.
        return empruntRepository.findById(id);
    }

    public List<Emprunt> findAll() {
        // Retourne tous les emprunts.
        return empruntRepository.findAll();
    }

    public Emprunt update(Emprunt emprunt) {
        // Met à jour un emprunt.
        return empruntRepository.update(emprunt);
    }

    public void delete(Long id) {
        // Supprime un emprunt par son id.
        empruntRepository.delete(id);
    }
}
