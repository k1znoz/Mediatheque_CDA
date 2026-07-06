package fr.mediatheque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un éditeur de livres.
 */
@Entity
@Table(name = "editeur")
public class Editeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    // Un éditeur peut contenir plusieurs livres.
    @OneToMany(mappedBy = "editeur")
    private List<Livre> livres;

    /**
     * Constructeur vide obligatoire pour JPA.
     */
    public Editeur() {
        this.livres = new ArrayList<>();
    }

    /**
     * Constructeur pratique pour créer un éditeur sans l'id.
     */
    public Editeur(String nom) {
        this.nom = nom;
        this.livres = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Editeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
