package fr.mediatheque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un livre de la médiathèque.
 */
@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titre;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false)
    private LocalDate datePublication;

    // Un livre appartient à une seule catégorie.
    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    // Un livre possède un seul éditeur.
    @ManyToOne
    @JoinColumn(name = "editeur_id", nullable = false)
    private Editeur editeur;

    // Un livre peut avoir plusieurs auteurs.
    @ManyToMany
    @JoinTable(
            name = "livre_auteur",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "auteur_id")
    )
    private List<Auteur> auteurs = new ArrayList<>();

    // Un livre peut avoir plusieurs emprunts.
    @OneToMany(mappedBy = "livre")
    private List<Emprunt> emprunts = new ArrayList<>();

    /**
     * Constructeur vide obligatoire pour JPA.
     */
    public Livre() {
    }

    /**
     * Constructeur pratique pour créer un livre sans l'id.
     */
    public Livre(String titre, String isbn, LocalDate datePublication, Categorie categorie, Editeur editeur) {
        this.titre = titre;
        this.isbn = isbn;
        this.datePublication = datePublication;
        this.categorie = categorie;
        this.editeur = editeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", isbn='" + isbn + '\'' +
                ", datePublication=" + datePublication +
                '}';
    }
}
