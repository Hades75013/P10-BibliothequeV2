package com.ocr.clientui.beans;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class OuvrageBean implements Serializable {


    private int id;

    private String titre;

    private String auteur;

    private String genre;

    private String cote;

    private String anneeEdition;

    private String nombrePages;

    private int nbExemplairesTotal;

    private int nbExemplairesDispo;

    private boolean statut;

    @JsonProperty("exemplaires")
    private List<ExemplaireBean> exemplaires;

    @JsonProperty("listeAttenteReservation")
    private List <ListeAttenteReservationBean> listeAttenteReservations;

    @JsonProperty("prets")
    private List<PretBean> prets;


    public OuvrageBean() {
    }

    public OuvrageBean(int id, String titre, String auteur, String genre, String cote, String anneeEdition, String nombrePages,
                       int nbExemplairesTotal, int nbExemplairesDispo, boolean statut, List<ExemplaireBean> exemplaires,
                       List<ListeAttenteReservationBean> listeAttenteReservations, List<PretBean> prets) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.cote = cote;
        this.anneeEdition = anneeEdition;
        this.nombrePages = nombrePages;
        this.nbExemplairesTotal = nbExemplairesTotal;
        this.nbExemplairesDispo = nbExemplairesDispo;
        this.statut = statut;
        this.exemplaires = exemplaires;
        this.listeAttenteReservations = listeAttenteReservations;
        this.prets = prets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCote() {
        return cote;
    }

    public void setCote(String cote) {
        this.cote = cote;
    }

    public String getAnneeEdition() {
        return anneeEdition;
    }

    public void setAnneeEdition(String anneeEdition) {
        this.anneeEdition = anneeEdition;
    }

    public String getNombrePages() {
        return nombrePages;
    }

    public void setNombrePages(String nombrePages) {
        this.nombrePages = nombrePages;
    }

    public int getNbExemplairesTotal() {
        return nbExemplairesTotal;
    }

    public void setNbExemplairesTotal(int nbExemplairesTotal) {
        this.nbExemplairesTotal = nbExemplairesTotal;
    }

    public int getNbExemplairesDispo() {
        return nbExemplairesDispo;
    }

    public void setNbExemplairesDispo(int nbExemplairesDispo) {
        this.nbExemplairesDispo = nbExemplairesDispo;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public List<ExemplaireBean> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<ExemplaireBean> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public List<ListeAttenteReservationBean> getListeAttenteReservations() {
        return listeAttenteReservations;
    }

    public void setListeAttenteReservations(List<ListeAttenteReservationBean> listeAttenteReservations) {
        this.listeAttenteReservations = listeAttenteReservations;
    }

    public List<PretBean> getPrets() {
        return prets;
    }

    public void setPrets(List<PretBean> prets) {
        this.prets = prets;
    }


}
