package com.ocr.clientui.service.library;

import com.ocr.clientui.beans.OuvrageBean;
import com.ocr.clientui.beans.PretBean;
import com.ocr.clientui.beans.ReservationListeAttenteBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


public interface ILibraryProxyService {



    List <ReservationListeAttenteBean> afficherListeAttenteResasOuvrage(int idOuvrage);

    List<OuvrageBean> listeOuvrages();

    OuvrageBean afficherUnOuvrage(int id);

    List<OuvrageBean> rechercheByTitre(String titre);

    List<OuvrageBean> rechercheByAuteur(String auteur);

    List<OuvrageBean> rechercheByGenre(String genre);

    List<OuvrageBean> rechercheByDispo(Boolean statut);





    PretBean afficherUnPret(int id);

    List<PretBean> listePrets();

    List<PretBean> listePretsByUtilisateur(int idUtilisateur);

    List<ReservationListeAttenteBean> listeResasByUtilisateur(int idUtilisateur);

    List<PretBean> listeDemandesPretByUtilisateur(int idUtilisateur);

    PretBean demanderPret(int idOuvrage, int idUtilisateur);

    PretBean reserverPret(int idOuvrage, int idUtilisateur);

    Date afficherDateRetourLaPlusProche(int idOuvrage);

    PretBean validerPret(int idPret);

    PretBean retourPret(int id);

    PretBean prolongationPret(int id);

    PretBean annulerPret(int idPret);

    ReservationListeAttenteBean annulerResa(int idReservationListeAttente);

    ReservationListeAttenteBean afficherResaByPret(int idPret);

    List<ReservationListeAttenteBean> afficherToutesReservations();

}
