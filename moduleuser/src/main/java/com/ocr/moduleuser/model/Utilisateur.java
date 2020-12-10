package com.ocr.moduleuser.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;



@Entity
public class Utilisateur implements Serializable{

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "email")
    private String email;

    @Column(name = "motDePasse")
    private String motDePasse;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @JoinTable(
            indexes = {@Index(name = "INDEX_UTILISATEUR_ROLE", columnList = "id_utilisateur")},
            name = "role",
            joinColumns = @JoinColumn(name = "id_utilisateur")
    )

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Collection<RoleEnum> roles;



    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String adresse, String email, String motDePasse, Collection<RoleEnum> roles) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.motDePasse = motDePasse;
        this.roles = roles;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Collection<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEnum> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return super.toString();
    }


}

