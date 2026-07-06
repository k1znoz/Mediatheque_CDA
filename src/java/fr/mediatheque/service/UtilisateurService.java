package fr.mediatheque.service;

import fr.mediatheque.model.Utilisateur;
import fr.mediatheque.repository.UtilisateurRepository;

import java.util.List;

/**
 * Service simple pour Utilisateur.
 * Il délègue les opérations au repository.
 */
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    public void save(Utilisateur utilisateur) {
        // Enregistre un utilisateur.
        utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findById(Long id) {
        // Recherche un utilisateur par son id.
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> findAll() {
        // Retourne tous les utilisateurs.
        return utilisateurRepository.findAll();
    }

    public Utilisateur update(Utilisateur utilisateur) {
        // Met à jour un utilisateur.
        return utilisateurRepository.update(utilisateur);
    }

    public void delete(Long id) {
        // Supprime un utilisateur par son id.
        utilisateurRepository.delete(id);
    }
}
