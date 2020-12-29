package com.ocr.clientui.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

public class ListeAttenteReservationBean implements Serializable {


    private int id;

    private Date dateDemande;

    private Integer idUtilisateur;

    @JsonProperty("ouvrage")
    private OuvrageBean ouvrage;


    public ListeAttenteReservationBean() {
    }

    public ListeAttenteReservationBean(int id, Date dateDemande, Integer idUtilisateur, OuvrageBean ouvrage) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.idUtilisateur = idUtilisateur;
        this.ouvrage = ouvrage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public OuvrageBean getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(OuvrageBean ouvrage) {
        this.ouvrage = ouvrage;
    }
}
