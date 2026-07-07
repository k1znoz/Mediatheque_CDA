package fr.mediatheque.controller;

import fr.mediatheque.model.Auteur;
import fr.mediatheque.service.AuteurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/auteurs")
public class AuteurServlet extends HttpServlet {

    private final AuteurService auteurService = new AuteurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                // Affiche la liste des auteurs.
                List<Auteur> auteurs = auteurService.findAll();
                request.setAttribute("auteurs", auteurs);
                request.getRequestDispatcher("/jsp/auteurs/liste.jsp").forward(request, response);
                break;
            case "new":
                // Affiche le formulaire vide.
                request.getRequestDispatcher("/jsp/auteurs/formulaire.jsp").forward(request, response);
                break;
            case "edit":
                // Charge l'auteur à modifier.
                Long id = parseId(request.getParameter("id"));
                if (id != null) {
                    Auteur auteur = auteurService.findById(id);
                    request.setAttribute("auteur", auteur);
                }
                request.getRequestDispatcher("/jsp/auteurs/formulaire.jsp").forward(request, response);
                break;
            default:
                // Action inconnue : retour à la liste.
                response.sendRedirect(request.getContextPath() + "/auteurs?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Création d'un nouvel auteur.
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            Auteur auteur = new Auteur(nom, prenom);
            auteurService.save(auteur);
        } else if ("update".equals(action)) {
            // Modification d'un auteur existant.
            Long id = parseId(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            if (id != null) {
                Auteur auteur = new Auteur(nom, prenom);
                auteur.setId(id);
                auteurService.update(auteur);
            }
        } else if ("delete".equals(action)) {
            // Suppression d'un auteur.
            Long id = parseId(request.getParameter("id"));
            if (id != null) {
                auteurService.delete(id);
            }
        }

        // Après chaque POST, on revient à la liste.
        response.sendRedirect(request.getContextPath() + "/auteurs?action=list");
    }

    private Long parseId(String idParam) {
        try {
            return Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
