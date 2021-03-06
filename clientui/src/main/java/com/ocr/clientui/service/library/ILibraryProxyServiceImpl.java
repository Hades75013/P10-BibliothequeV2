package com.ocr.clientui.service.library;

import com.ocr.clientui.beans.OuvrageBean;
import com.ocr.clientui.beans.PretBean;
import com.ocr.clientui.beans.ReservationListeAttenteBean;
import com.ocr.clientui.proxies.LibraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class ILibraryProxyServiceImpl implements ILibraryProxyService{

    @Autowired
    private LibraryProxy libraryProxy;


    //METHODES OUVRAGE


    /**
     *
     * @param idOuvrage
     * @return
     */
    @Override
    public List<ReservationListeAttenteBean> afficherListeAttenteResasOuvrage(int idOuvrage) {
        return libraryProxy.afficherListeAttenteResasOuvrage(idOuvrage);
    }

    /**
     *
     * @return
     */
    @Override
    public List<OuvrageBean> listeOuvrages() {
        return libraryProxy.listeOuvrages();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public OuvrageBean afficherUnOuvrage(int id) {
        return libraryProxy.afficherUnOuvrage(id);
    }

    /**
     *
     * @param titre
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByTitre(String titre) {
        return libraryProxy.rechercheByTitre(titre);
    }

    /**
     *
     * @param auteur
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByAuteur(String auteur) {
        return libraryProxy.rechercheByAuteur(auteur);
    }

    /**
     *
     * @param genre
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByGenre(String genre) {
        return libraryProxy.rechercheByGenre(genre);
    }

    /**
     *
     * @param statut
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByDispo(Boolean statut) {
        return libraryProxy.rechercheByDispo(statut);
    }

    //METHODES PRET
    /**
     *
     * @param idPret
     * @return
     */
    @Override
    public PretBean afficherUnPret(int idPret) {
        return libraryProxy.afficherUnPret(idPret);
    }

    /**
     *
     * @return
     */
    @Override
    public List<PretBean> listePrets() {
        return libraryProxy.listePrets();
    }

    /**
     *
     * @param idUtilisateur
     * @return
     */
    @Override
    public List<PretBean> listePretsByUtilisateur(int idUtilisateur) {
        return libraryProxy.listePretsByUtilisateur(idUtilisateur);
    }

    /**
     *
     * @param idUtilisateur
     * @return
     */
    @Override
    public List<ReservationListeAttenteBean> listeResasByUtilisateur(int idUtilisateur) {
        return libraryProxy.listeResasByUtilisateur(idUtilisateur);
    }

    /**
     *
     * @param idUtilisateur
     * @return
     */
    @Override
    public List<PretBean> listeDemandesPretByUtilisateur(int idUtilisateur) {
        return libraryProxy.listeDemandesPretByUtilisateur(idUtilisateur);
    }

    /**
     *
     * @param idOuvrage
     * @param idUtilisateur
     * @return
     */
    @Override
    public PretBean demanderPret(int idOuvrage, int idUtilisateur) {
        return libraryProxy.demanderPret(idOuvrage, idUtilisateur);
    }

    /**
     *
     * @param idOuvrage
     * @param idUtilisateur
     * @return
     */
    @Override
    public PretBean reserverPret(int idOuvrage, int idUtilisateur) {
        return libraryProxy.reserverPret(idOuvrage, idUtilisateur);
    }

    /**
     *
     * @param idPret
     * @return
     */
    @Override
    public PretBean validerPret(int idPret) {
        return libraryProxy.validerPret(idPret);
    }

    /**
     *
     * @param idPret
     * @return
     */
    @Override
    public PretBean retourPret(int idPret) {
        return libraryProxy.retourPret(idPret);
    }

    /**
     *
     * @param idPret
     * @return
     */
    @Override
    public PretBean prolongationPret(int idPret) {
        return libraryProxy.prolongationPret(idPret);
    }

    /**
     *
     * @param idPret
     * @return
     */
    @Override
    public PretBean annulerPret(int idPret) {

        return libraryProxy.annulerPret(idPret);
    }


    /**
     *
     * @param idReservationListeAttente
     * @return
     */
    @Override
    public ReservationListeAttenteBean annulerResa(int idReservationListeAttente) {

        return libraryProxy.annulerResa(idReservationListeAttente);
    }

    /**
     *
     * @param idOuvrage
     * @return
     */
    @Override
    public Date afficherDateRetourLaPlusProche(int idOuvrage) {
        return libraryProxy.afficherDateRetourLaPlusProche(idOuvrage);
    }

    /**
     *
     * @param idPret
     * @return
     */
    @Override
    public ReservationListeAttenteBean afficherResaByPret(int idPret) {
        return libraryProxy.afficherResaByPret(idPret);
    }

    /**
     *
     * @return
     */
    @Override
    public List<ReservationListeAttenteBean> afficherToutesReservations() {
        return libraryProxy.afficherToutesReservations();
    }


}
