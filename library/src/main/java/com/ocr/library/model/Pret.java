package com.ocr.library.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pret {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private Integer idUtilisateur;

    @Column(name="dateReservation")
    private Date dateReservation;

    @Column(name="dateDebut")
    private Date dateDebut;

    @Column(name="dateFin")
    private Date dateFin;

    @Column(name="dateRetour")
    private Date dateRetour;

    @Column(name="prolongeable")
    private boolean prolongeable;

    @Column(name = "statut", nullable = false)
    @Enumerated(EnumType.STRING)
    private PretStatutEnum statut;

    @JsonManagedReference
    @ManyToOne
    private Exemplaire exemplaire;

    @JsonManagedReference
    @ManyToOne
    private Ouvrage ouvrage;

    @JsonBackReference
    @OneToOne(mappedBy = "pret",targetEntity=ReservationListeAttente.class)
    private ReservationListeAttente reservation;


    public Pret() {
    }

    public Pret(int id, Integer idUtilisateur, Date dateReservation, Date dateDebut, Date dateFin, Date dateRetour,
                boolean prolongeable, PretStatutEnum statut, Exemplaire exemplaire, Ouvrage ouvrage,
                ReservationListeAttente reservation) {
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

    public boolean isProlongeable() {
        return prolongeable;
    }

    public void setProlongeable(boolean prolongeable) {
        this.prolongeable = prolongeable;
    }

    public PretStatutEnum getStatut() {
        return statut;
    }

    public void setStatut(PretStatutEnum statut) {
        this.statut = statut;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public ReservationListeAttente getReservation() {
        return reservation;
    }

    public void setReservation(ReservationListeAttente reservation) {
        this.reservation = reservation;
    }

    public boolean isSurListe() {
        return PretStatutEnum.SUR_LISTE.equals(this.statut);
    }

    public boolean isEnAttente() {
        return PretStatutEnum.EN_ATTENTE.equals(this.statut);
    }

    public boolean isEnAttenteResa() {
        return PretStatutEnum.EN_ATTENTE_RESA.equals(this.statut);
    }


    public boolean isEnCours() {
        return PretStatutEnum.EN_COURS.equals(this.statut);
    }

    public boolean isProlonge() {
        return PretStatutEnum.PROLONGE.equals(this.statut);
    }

    public boolean isTermine() {
        return PretStatutEnum.TERMINE.equals(this.statut);
    }

    public boolean isDepasse() {
        return PretStatutEnum.DEPASSE.equals(this.statut);
    }

}
