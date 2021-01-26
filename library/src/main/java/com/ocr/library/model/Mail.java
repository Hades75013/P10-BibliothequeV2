package com.ocr.library.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mail {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="dateEnvoi")
    private Date dateEnvoi;

    @Column(name="idUtilisateur")
    private int idUtilisateur;

    @Column(name="idPretRendu")
    private int idPretRendu;

    @Column(name="idReservation")
    private int idReservation;

    @Column(name="idPret")
    private int idPret;

    @Column(name = "statut", nullable = false)
    @Enumerated(EnumType.STRING)
    private MailStatutEnum statut;


    public Mail() {
    }

    public Mail(int id, Date dateEnvoi, int idUtilisateur, int idPretRendu, int idReservation, int idPret, MailStatutEnum statut) {
        this.id = id;
        this.dateEnvoi = dateEnvoi;
        this.idUtilisateur = idUtilisateur;
        this.idPretRendu = idPretRendu;
        this.idReservation = idReservation;
        this.idPret = idPret;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdPretRendu() {
        return idPretRendu;
    }

    public void setIdPretRendu(int idPretRendu) {
        this.idPretRendu = idPretRendu;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdPret() {
        return idPret;
    }

    public void setIdPret(int idPret) {
        this.idPret = idPret;
    }

    public MailStatutEnum getStatut() {
        return statut;
    }

    public void setStatut(MailStatutEnum statut) {
        this.statut = statut;
    }
}
