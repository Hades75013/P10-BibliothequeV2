package com.ocr.clientui.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;



public class PretBean implements Serializable {

    private int id;

    private Integer idUtilisateur;

    private Date dateReservation;

    private Date dateDebut;

    private Date dateFin;

    private Date dateRetour;

    private boolean prolongeable;

    private PretStatutEnumBean statut;

    @JsonProperty("exemplaire")
    private ExemplaireBean exemplaire;

    @JsonProperty("ouvrage")
    private OuvrageBean ouvrage;

    @JsonProperty("reservation")
    private ReservationListeAttenteBean reservation;


    public PretBean() {
    }

    public PretBean(int id, Integer idUtilisateur, Date dateReservation, Date dateDebut, Date dateFin, Date dateRetour,
                    boolean prolongeable, PretStatutEnumBean statut, ExemplaireBean exemplaire, OuvrageBean ouvrage,
                    ReservationListeAttenteBean reservation) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateReservation = dateReservation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateRetour = dateRetour;
        this.prolongeable = prolongeable;
        this.statut = statut;
        this.exemplaire = exemplaire;
        this.ouvrage = ouvrage;
        this.reservation = reservation;
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

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public PretStatutEnumBean getStatut() {
        return statut;
    }

    public void setStatut(PretStatutEnumBean statut) {
        this.statut = statut;
    }

    public boolean isProlongeable() {
        return prolongeable;
    }

    public void setProlongeable(boolean prolongeable) {
        this.prolongeable = prolongeable;
    }

    public ExemplaireBean getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(ExemplaireBean exemplaire) {
        this.exemplaire = exemplaire;
    }

    public OuvrageBean getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(OuvrageBean ouvrage) {
        this.ouvrage = ouvrage;
    }

    public ReservationListeAttenteBean getReservation() {
        return reservation;
    }

    public void setReservation(ReservationListeAttenteBean reservation) {
        this.reservation = reservation;
    }

    public boolean isSurListe() {
        return PretStatutEnumBean.SUR_LISTE.equals(this.statut);
    }

    public boolean isEnAttente() {
        return PretStatutEnumBean.EN_ATTENTE.equals(this.statut);
    }

    public boolean isEnAttenteResa() {
        return PretStatutEnumBean.EN_ATTENTE_RESA.equals(this.statut);
    }


    public boolean isEnCours() {
        return PretStatutEnumBean.EN_COURS.equals(this.statut);
    }

    public boolean isProlonge() {
        return PretStatutEnumBean.PROLONGE.equals(this.statut);
    }

    public boolean isTermine() {
        return PretStatutEnumBean.TERMINE.equals(this.statut);
    }

    public boolean isDepasse() {
        return PretStatutEnumBean.DEPASSE.equals(this.statut);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
