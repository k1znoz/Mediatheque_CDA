package fr.mediatheque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

/**
 * Représente un emprunt de livre.
 */
@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateEmprunt;

    @Column(nullable = false)
    private LocalDate dateRetourPrevue;

    @Column(nullable = true)
    private LocalDate dateRetourEffective;

    // Un emprunt concerne un seul livre.
    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    // Un emprunt appartient à un seul utilisateur.
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    /**
     * Constructeur vide obligatoire pour JPA.
     */
    public Emprunt() {
    }

    /**
     * Constructeur pratique pour créer un emprunt sans l'id.
     */
    public Emprunt(LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective, Livre livre, Utilisateur utilisateur) {
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.livre = livre;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourPrevue=" + dateRetourPrevue +
                ", dateRetourEffective=" + dateRetourEffective +
                '}';
    }
}
