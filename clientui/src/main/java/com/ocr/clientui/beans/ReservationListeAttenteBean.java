package com.ocr.clientui.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

public class ReservationListeAttenteBean implements Serializable {


    private int id;

    private Date dateDemande;

    private Integer idUtilisateur;

    private Integer position;

    @JsonProperty("ouvrage")
    private OuvrageBean ouvrage;

    @JsonProperty("pret")
    private PretBean pret;


    public ReservationListeAttenteBean() {
    }

    public ReservationListeAttenteBean(int id, Date dateDemande, Integer idUtilisateur, Integer position,
                                       OuvrageBean ouvrage, PretBean pret) {
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public OuvrageBean getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(OuvrageBean ouvrage) {
        this.ouvrage = ouvrage;
    }

    public PretBean getPret() {
        return pret;
    }

    public void setPret(PretBean pret) {
        this.pret = pret;
    }
}
