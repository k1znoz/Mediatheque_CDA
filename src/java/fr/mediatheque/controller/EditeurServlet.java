package fr.mediatheque.controller;

import fr.mediatheque.model.Editeur;
import fr.mediatheque.service.EditeurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editeurs")
public class EditeurServlet extends HttpServlet {

    private final EditeurService editeurService = new EditeurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                // Affiche la liste des éditeurs.
                List<Editeur> editeurs = editeurService.findAll();
                request.setAttribute("editeurs", editeurs);
                request.getRequestDispatcher("/jsp/editeurs/liste.jsp").forward(request, response);
                break;
            case "new":
                // Affiche le formulaire vide.
                request.getRequestDispatcher("/jsp/editeurs/formulaire.jsp").forward(request, response);
                break;
            case "edit":
                // Charge l'éditeur à modifier.
                Long id = parseId(request.getParameter("id"));
                if (id != null) {
                    Editeur editeur = editeurService.findById(id);
                    request.setAttribute("editeur", editeur);
                }
                request.getRequestDispatcher("/jsp/editeurs/formulaire.jsp").forward(request, response);
                break;
            default:
                // Action inconnue : retour à la liste.
                response.sendRedirect(request.getContextPath() + "/editeurs?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Création d'un nouvel éditeur.
            String nom = request.getParameter("nom");
            Editeur editeur = new Editeur(nom);
            editeurService.save(editeur);
        } else if ("update".equals(action)) {
            // Modification d'un éditeur existant.
            Long id = parseId(request.getParameter("id"));
            String nom = request.getParameter("nom");
            if (id != null) {
                Editeur editeur = new Editeur(nom);
                editeur.setId(id);
                editeurService.update(editeur);
            }
        } else if ("delete".equals(action)) {
            // Suppression d'un éditeur.
            Long id = parseId(request.getParameter("id"));
            if (id != null) {
                editeurService.delete(id);
            }
        }

        // Après chaque POST, on revient à la liste.
        response.sendRedirect(request.getContextPath() + "/editeurs?action=list");
    }

    private Long parseId(String idParam) {
        try {
            return Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
