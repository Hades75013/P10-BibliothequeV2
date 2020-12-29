package com.ocr.library.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ListeAttenteReservation {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="dateDemande")
    private Date dateDemande;

    @Column
    private Integer idUtilisateur;

    @JsonManagedReference
    @ManyToOne
    private Ouvrage ouvrage;


    public ListeAttenteReservation() {
    }

    public ListeAttenteReservation(int id, Integer idUtilisateur, Ouvrage ouvrage) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.ouvrage = ouvrage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }


}
