package fr.mediatheque.controller;

import fr.mediatheque.model.Utilisateur;
import fr.mediatheque.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/utilisateurs")
public class UtilisateurServlet extends HttpServlet {

    private final UtilisateurService utilisateurService = new UtilisateurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                // Affiche la liste des utilisateurs.
                List<Utilisateur> utilisateurs = utilisateurService.findAll();
                request.setAttribute("utilisateurs", utilisateurs);
                request.getRequestDispatcher("/jsp/utilisateurs/liste.jsp").forward(request, response);
                break;
            case "new":
                // Affiche le formulaire vide.
                request.getRequestDispatcher("/jsp/utilisateurs/formulaire.jsp").forward(request, response);
                break;
            case "edit":
                // Charge l'utilisateur à modifier.
                Long id = parseId(request.getParameter("id"));
                if (id != null) {
                    Utilisateur utilisateur = utilisateurService.findById(id);
                    request.setAttribute("utilisateur", utilisateur);
                }
                request.getRequestDispatcher("/jsp/utilisateurs/formulaire.jsp").forward(request, response);
                break;
            default:
                // Action inconnue : retour à la liste.
                response.sendRedirect(request.getContextPath() + "/utilisateurs?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Création d'un nouvel utilisateur.
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            Utilisateur utilisateur = new Utilisateur(nom, prenom, email);
            utilisateurService.save(utilisateur);
        } else if ("update".equals(action)) {
            // Modification d'un utilisateur existant.
            Long id = parseId(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            if (id != null) {
                Utilisateur utilisateur = new Utilisateur(nom, prenom, email);
                utilisateur.setId(id);
                utilisateurService.update(utilisateur);
            }
        } else if ("delete".equals(action)) {
            // Suppression d'un utilisateur.
            Long id = parseId(request.getParameter("id"));
            if (id != null) {
                utilisateurService.delete(id);
            }
        }

        // Après chaque POST, on revient à la liste.
        response.sendRedirect(request.getContextPath() + "/utilisateurs?action=list");
    }

    private Long parseId(String idParam) {
        try {
            return Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
