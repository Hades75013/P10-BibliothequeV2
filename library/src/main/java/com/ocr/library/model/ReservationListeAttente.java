package com.ocr.library.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ReservationListeAttente {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="dateDemande")
    private Date dateDemande;


    @Column
    private Integer idUtilisateur;

    @Column
    private Integer position;


    @JsonManagedReference
    @OneToOne
    private Pret pret;


    @JsonManagedReference
    @ManyToOne
    private Ouvrage ouvrage;


    public ReservationListeAttente() {
    }

    public ReservationListeAttente(int id, Date dateDemande, Integer idUtilisateur, Integer position, Ouvrage ouvrage, Pret pret) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.idUtilisateur = idUtilisateur;
        this.position = position;
        this.ouvrage = ouvrage;
        this.pret = pret;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
