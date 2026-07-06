package fr.mediatheque.service;

import fr.mediatheque.model.Editeur;
import fr.mediatheque.repository.EditeurRepository;

import java.util.List;

/**
 * Service simple pour Editeur.
 * Il délègue les opérations au repository.
 */
public class EditeurService {

    private final EditeurRepository editeurRepository = new EditeurRepository();

    public void save(Editeur editeur) {
        // Enregistre un éditeur.
        editeurRepository.save(editeur);
    }

    public Editeur findById(Long id) {
        // Recherche un éditeur par son id.
        return editeurRepository.findById(id);
    }

    public List<Editeur> findAll() {
        // Retourne tous les éditeurs.
        return editeurRepository.findAll();
    }

    public Editeur update(Editeur editeur) {
        // Met à jour un éditeur.
        return editeurRepository.update(editeur);
    }

    public void delete(Long id) {
        // Supprime un éditeur par son id.
        editeurRepository.delete(id);
    }
}
