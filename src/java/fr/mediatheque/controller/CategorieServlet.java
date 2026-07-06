package fr.mediatheque.controller;

import fr.mediatheque.model.Categorie;
import fr.mediatheque.service.CategorieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategorieServlet extends HttpServlet {

    private final CategorieService categorieService = new CategorieService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                // Affiche la liste des catégories.
                List<Categorie> categories = categorieService.findAll();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/jsp/categories/liste.jsp").forward(request, response);
                break;
            case "new":
                // Affiche le formulaire vide.
                request.getRequestDispatcher("/jsp/categories/formulaire.jsp").forward(request, response);
                break;
            case "edit":
                // Charge la catégorie à modifier.
                Long id = parseId(request.getParameter("id"));
                if (id != null) {
                    Categorie categorie = categorieService.findById(id);
                    request.setAttribute("categorie", categorie);
                }
                request.getRequestDispatcher("/jsp/categories/formulaire.jsp").forward(request, response);
                break;
            default:
                // Action inconnue : retour à la liste.
                response.sendRedirect(request.getContextPath() + "/categories?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Création d'une nouvelle catégorie.
            String nom = request.getParameter("nom");
            Categorie categorie = new Categorie(nom);
            categorieService.save(categorie);
        } else if ("update".equals(action)) {
            // Modification d'une catégorie existante.
            Long id = parseId(request.getParameter("id"));
            String nom = request.getParameter("nom");
            if (id != null) {
                Categorie categorie = new Categorie(nom);
                categorie.setId(id);
                categorieService.update(categorie);
            }
        } else if ("delete".equals(action)) {
            // Suppression d'une catégorie.
            Long id = parseId(request.getParameter("id"));
            if (id != null) {
                categorieService.delete(id);
            }
        }

        // Après chaque POST, on revient à la liste.
        response.sendRedirect(request.getContextPath() + "/categories?action=list");
    }

    private Long parseId(String idParam) {
        try {
            return Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
