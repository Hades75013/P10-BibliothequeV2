package com.ocr.clientui.proxies;


import com.ocr.clientui.beans.OuvrageBean;
import com.ocr.clientui.beans.PretBean;
import com.ocr.clientui.beans.ReservationListeAttenteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface LibraryProxy {


    //OuvrageProxies

    @GetMapping(value="/ResaListeAttente/{idOuvrage}")
    List afficherListeAttenteResasOuvrage(@PathVariable ("idOuvrage") int idOuvrage);

    @GetMapping(value = "/Ouvrages")
    List<OuvrageBean> listeOuvrages();

    @GetMapping( value = "/Ouvrage/{idOuvrage}")
    OuvrageBean afficherUnOuvrage(@PathVariable("idOuvrage") int idOuvrage);

    @GetMapping(value="/RechercheParTitre")
    List<OuvrageBean> rechercheByTitre(@RequestParam(name = "titre", defaultValue = "")String titre);

    @GetMapping(value="/RechercheParAuteur")
    List<OuvrageBean> rechercheByAuteur(@RequestParam(name = "auteur", defaultValue = "")String auteur);

    @GetMapping(value="/RechercheParGenre")
    List<OuvrageBean> rechercheByGenre(@RequestParam(name = "genre", defaultValue = "")String genre);

    @GetMapping(value="/RechercheParDispo")
    List<OuvrageBean> rechercheByDispo(@RequestParam(name = "statut")Boolean statut);



    //PretProxies

    @GetMapping(value="/Pret/{idPret}")
    PretBean afficherUnPret(@PathVariable("idPret") int idPret);

    @GetMapping(value="/Prets")
    List<PretBean> listePrets();

    @GetMapping(value="/Prets/{idUtilisateur}")
    List<PretBean> listePretsByUtilisateur(@PathVariable("idUtilisateur") int idUtilisateur);

    @GetMapping(value="/Resas/{idUtilisateur}")
    List<ReservationListeAttenteBean> listeResasByUtilisateur(@PathVariable("idUtilisateur") int idUtilisateur);

    @GetMapping(value="/DemandeDePretsUtilisateur/{idUtilisateur}")
    List<PretBean> listeDemandesPretByUtilisateur(@PathVariable("idUtilisateur") int idUtilisateur);

    @PostMapping(value="/Pret/{idOuvrage}")
    PretBean demanderPret(@PathVariable("idOuvrage") int idOuvrage, @RequestParam int idUtilisateur);

    @PostMapping(value="/ReserverPret/{idOuvrage}")
    PretBean reserverPret(@PathVariable("idOuvrage") int idOuvrage, @RequestParam int idUtilisateur);

    @PutMapping(value="/Pret/{idPret}")
    PretBean validerPret(@PathVariable("idPret") int idPret);

    @PutMapping(value="/RetourPret/{idPret}")
    PretBean retourPret(@PathVariable("idPret") int idPret);

    @PutMapping(value="/ProlongationPret/{idPret}")
    PretBean prolongationPret(@PathVariable("idPret") int idPret);

    @GetMapping(value="/DateRetourPlusProche/{idOuvrage}")
    Date afficherDateRetourLaPlusProche(@PathVariable ("idOuvrage") int idOuvrage);

    @DeleteMapping(value="/Pret/{idPret}")
    PretBean annulerPret(@PathVariable("idPret") int idPret);

    @DeleteMapping(value="/Resa/{idReservationListeAttente}")
    ReservationListeAttenteBean annulerResa(@PathVariable("idReservationListeAttente") int idReservationListeAttente);

    @GetMapping(value="/Resa/{idPret}")
    ReservationListeAttenteBean afficherResaByPret(@PathVariable("idPret") int idPret);

    @GetMapping(value="/Reservations")
    List<ReservationListeAttenteBean> afficherToutesReservations();

    }
