package fr.mediatheque.service;

import fr.mediatheque.model.Auteur;
import fr.mediatheque.repository.AuteurRepository;

import java.util.List;

/**
 * Service simple pour Auteur.
 * Il délègue les opérations au repository.
 */
public class AuteurService {

    private final AuteurRepository auteurRepository = new AuteurRepository();

    public void save(Auteur auteur) {
        // Enregistre un auteur.
        auteurRepository.save(auteur);
    }

    public Auteur findById(Long id) {
        // Recherche un auteur par son id.
        return auteurRepository.findById(id);
    }

    public List<Auteur> findAll() {
        // Retourne tous les auteurs.
        return auteurRepository.findAll();
    }

    public Auteur update(Auteur auteur) {
        // Met à jour un auteur.
        return auteurRepository.update(auteur);
    }

    public void delete(Long id) {
        // Supprime un auteur par son id.
        auteurRepository.delete(id);
    }
}
