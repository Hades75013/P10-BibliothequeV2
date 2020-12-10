package com.ocr.library.beans;

import java.util.Collection;

public class UtilisateurBean {

    private int id;

    private String nom;

    private String prenom;

    private String adresse;

    private String email;

    private String motDePasse;

    private Collection roles;

    public UtilisateurBean(int id, String nom, String prenom, String adresse, String email, String motDePasse, Collection roles) {
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

    public Collection getRoles() {
        return roles;
    }

    public void setRoles(Collection roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UtilisateurBean{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", roles=" + roles +
                '}';
    }


}
