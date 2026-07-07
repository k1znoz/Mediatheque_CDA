package fr.mediatheque.controller;

import fr.mediatheque.model.Auteur;
import fr.mediatheque.model.Categorie;
import fr.mediatheque.model.Editeur;
import fr.mediatheque.model.Livre;
import fr.mediatheque.service.AuteurService;
import fr.mediatheque.service.CategorieService;
import fr.mediatheque.service.EditeurService;
import fr.mediatheque.service.LivreService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/livres")
public class LivreServlet extends HttpServlet {

    private final LivreService livreService = new LivreService();
    private final CategorieService categorieService = new CategorieService();
    private final EditeurService editeurService = new EditeurService();
    private final AuteurService auteurService = new AuteurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                // Affiche la liste des livres.
                List<Livre> livres = livreService.findAll();
                request.setAttribute("livres", livres);
                request.getRequestDispatcher("/jsp/livres/liste.jsp").forward(request, response);
                break;
            case "new":
                // Affiche le formulaire vide.
                request.setAttribute("categories", categorieService.findAll());
                request.setAttribute("editeurs", editeurService.findAll());
                request.setAttribute("auteurs", auteurService.findAll());
                request.getRequestDispatcher("/jsp/livres/formulaire.jsp").forward(request, response);
                break;
            case "edit":
                // Charge le livre à modifier.
                Long id = parseId(request.getParameter("id"));
                if (id != null) {
                    Livre livre = livreService.findById(id);
                    request.setAttribute("livre", livre);
                }
                request.setAttribute("categories", categorieService.findAll());
                request.setAttribute("editeurs", editeurService.findAll());
                request.setAttribute("auteurs", auteurService.findAll());
                request.getRequestDispatcher("/jsp/livres/formulaire.jsp").forward(request, response);
                break;
            default:
                // Action inconnue : retour à la liste.
                response.sendRedirect(request.getContextPath() + "/livres?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Création d'un nouveau livre.
            String titre = request.getParameter("titre");
            String isbn = request.getParameter("isbn");
            LocalDate datePublication = parseDate(request.getParameter("datePublication"));
            Long categorieId = parseId(request.getParameter("categorieId"));
            Long editeurId = parseId(request.getParameter("editeurId"));
            String[] auteurIdsParam = request.getParameterValues("auteurIds");

            Livre livre = new Livre();
            livre.setTitre(titre);
            livre.setIsbn(isbn);
            livre.setDatePublication(datePublication);
            livre.setCategorie(categorieService.findById(categorieId));
            livre.setEditeur(editeurService.findById(editeurId));
            livre.setAuteurs(chargerAuteursSelectionnes(auteurIdsParam));
            livreService.save(livre);
        } else if ("update".equals(action)) {
            // Modification d'un livre existant.
            Long id = parseId(request.getParameter("id"));
            String titre = request.getParameter("titre");
            String isbn = request.getParameter("isbn");
            LocalDate datePublication = parseDate(request.getParameter("datePublication"));
            Long categorieId = parseId(request.getParameter("categorieId"));
            Long editeurId = parseId(request.getParameter("editeurId"));
            String[] auteurIdsParam = request.getParameterValues("auteurIds");

            if (id != null) {
                Livre livre = new Livre();
                livre.setId(id);
                livre.setTitre(titre);
                livre.setIsbn(isbn);
                livre.setDatePublication(datePublication);
                livre.setCategorie(categorieService.findById(categorieId));
                livre.setEditeur(editeurService.findById(editeurId));
                livre.setAuteurs(chargerAuteursSelectionnes(auteurIdsParam));
                livreService.update(livre);
            }
        } else if ("delete".equals(action)) {
            // Suppression d'un livre.
            Long id = parseId(request.getParameter("id"));
            if (id != null) {
                livreService.delete(id);
            }
        }

        // Après chaque POST, on revient à la liste.
        response.sendRedirect(request.getContextPath() + "/livres?action=list");
    }

    private Long parseId(String idParam) {
        try {
            return Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private LocalDate parseDate(String dateParam) {
        try {
            return LocalDate.parse(dateParam);
        } catch (Exception e) {
            return null;
        }
    }

    private void chargerListesFormulaire(HttpServletRequest request) {
        request.setAttribute("categories", categorieService.findAll());
        request.setAttribute("editeurs", editeurService.findAll());
        request.setAttribute("auteurs", auteurService.findAll());
    }

    private List<Auteur> chargerAuteursSelectionnes(String[] auteurIdsParam) {
        List<Auteur> auteurs = new ArrayList<>();

        if (auteurIdsParam == null) {
            return auteurs;
        }

        for (String auteurIdParam : auteurIdsParam) {
            Long auteurId = parseId(auteurIdParam);
            if (auteurId != null) {
                Auteur auteur = auteurService.findById(auteurId);
                if (auteur != null) {
                    auteurs.add(auteur);
                }
            }
        }

        return auteurs;
    }
}
