package fr.mediatheque.controller;

import fr.mediatheque.model.Emprunt;
import fr.mediatheque.model.Livre;
import fr.mediatheque.model.Utilisateur;
import fr.mediatheque.service.EmpruntService;
import fr.mediatheque.service.LivreService;
import fr.mediatheque.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/emprunts")
public class EmpruntServlet extends HttpServlet {

    private final EmpruntService empruntService = new EmpruntService();
    private final UtilisateurService utilisateurService = new UtilisateurService();
    private final LivreService livreService = new LivreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                // Affiche la liste des emprunts.
                List<Emprunt> emprunts = empruntService.findAll();
                request.setAttribute("emprunts", emprunts);
                request.getRequestDispatcher("/jsp/emprunts/liste.jsp").forward(request, response);
                break;
            case "new":
                // Affiche le formulaire vide.
                request.setAttribute("utilisateurs", utilisateurService.findAll());
                request.setAttribute("livres", livreService.findAll());
                request.getRequestDispatcher("/jsp/emprunts/formulaire.jsp").forward(request, response);
                break;
            case "edit":
                // Charge l'emprunt a modifier.
                Long id = parseId(request.getParameter("id"));
                if (id != null) {
                    Emprunt emprunt = empruntService.findById(id);
                    request.setAttribute("emprunt", emprunt);
                }
                request.setAttribute("utilisateurs", utilisateurService.findAll());
                request.setAttribute("livres", livreService.findAll());
                request.getRequestDispatcher("/jsp/emprunts/formulaire.jsp").forward(request, response);
                break;
            default:
                // Action inconnue : retour a la liste.
                response.sendRedirect(request.getContextPath() + "/emprunts?action=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Creation d'un nouvel emprunt.
            Long utilisateurId = parseId(request.getParameter("utilisateurId"));
            Long livreId = parseId(request.getParameter("livreId"));
            LocalDate dateEmprunt = parseDate(request.getParameter("dateEmprunt"));
            LocalDate dateRetourPrevue = parseDate(request.getParameter("dateRetourPrevue"));
            LocalDate dateRetourEffective = parseDate(request.getParameter("dateRetourEffective"));

            Emprunt emprunt = new Emprunt();
            emprunt.setDateEmprunt(dateEmprunt);
            emprunt.setDateRetourPrevue(dateRetourPrevue);
            emprunt.setDateRetourEffective(dateRetourEffective);
            emprunt.setUtilisateur(utilisateurService.findById(utilisateurId));
            emprunt.setLivre(livreService.findById(livreId));
            empruntService.save(emprunt);
        } else if ("update".equals(action)) {
            // Modification d'un emprunt existant.
            Long id = parseId(request.getParameter("id"));
            Long utilisateurId = parseId(request.getParameter("utilisateurId"));
            Long livreId = parseId(request.getParameter("livreId"));
            LocalDate dateEmprunt = parseDate(request.getParameter("dateEmprunt"));
            LocalDate dateRetourPrevue = parseDate(request.getParameter("dateRetourPrevue"));
            LocalDate dateRetourEffective = parseDate(request.getParameter("dateRetourEffective"));

            if (id != null) {
                Emprunt emprunt = new Emprunt();
                emprunt.setId(id);
                emprunt.setDateEmprunt(dateEmprunt);
                emprunt.setDateRetourPrevue(dateRetourPrevue);
                emprunt.setDateRetourEffective(dateRetourEffective);
                emprunt.setUtilisateur(utilisateurService.findById(utilisateurId));
                emprunt.setLivre(livreService.findById(livreId));
                empruntService.update(emprunt);
            }
        } else if ("delete".equals(action)) {
            // Suppression d'un emprunt.
            Long id = parseId(request.getParameter("id"));
            if (id != null) {
                empruntService.delete(id);
            }
        }

        // Apres chaque POST, on revient a la liste.
        response.sendRedirect(request.getContextPath() + "/emprunts?action=list");
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
}
