package com.ocr.library.web.controller;


import com.ocr.library.model.Pret;
import com.ocr.library.model.ReservationListeAttente;
import com.ocr.library.service.pret.IPretService;
import com.ocr.library.service.reservationListeAttente.IReservationListeAttenteService;
import com.ocr.library.web.exceptions.PretIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class PretController {

    @Autowired
    private IPretService pretService;

    @Autowired
    private IReservationListeAttenteService reservationListeAttenteService;


    @GetMapping(value="/Pret/{idPret}")
    public Pret afficherUnPret(@PathVariable ("idPret") int idPret){

        Pret pret = pretService.afficherUnPret(idPret);
        if (pret==null) throw new PretIntrouvableException("Le prêt avec l'id "+idPret+" est introuvable.");

        return pret;
    }

    @GetMapping(value="/Prets")
    public List<Pret> listePrets(){

        List<Pret> prets = pretService.listePrets();

        return prets;

    }

    @GetMapping(value="/Prets/{idUtilisateur}")
    public List<Pret> listePretsByUtilisateur(@PathVariable ("idUtilisateur")int idUtilisateur){

        List<Pret> pretsUtilisateur = pretService.afficherListePretsUtilisateur(idUtilisateur);

        return pretsUtilisateur;
    }

    @GetMapping(value="/Resas/{idUtilisateur}")
    public List<ReservationListeAttente> listeResasByUtilisateur(@PathVariable ("idUtilisateur")int idUtilisateur){

        List<ReservationListeAttente> resasUtilisateur = reservationListeAttenteService.afficherListeAttenteResasUtilisateur(idUtilisateur);

        return resasUtilisateur;
    }

    @GetMapping(value="/DemandeDePretsUtilisateur/{idUtilisateur}")
    public List<Pret> listeDemandesPretByUtilisateur(@PathVariable ("idUtilisateur")int idUtilisateur){

        List<Pret> listeDemandesPretByUtilisateur = pretService.listeDemandesPretByUtilisateur(idUtilisateur);

        return listeDemandesPretByUtilisateur;
    }



    @PostMapping(value="/Pret/{idOuvrage}")
    public void demanderPret(@PathVariable("idOuvrage") int idOuvrage,@RequestParam(value="idUtilisateur",required=false)int idUtilisateur){

        pretService.demanderPret(idOuvrage,idUtilisateur);

    }

    @PostMapping(value="/ReserverPret/{idOuvrage}")
    public void reserverPret(@PathVariable("idOuvrage") int idOuvrage,@RequestParam(value="idUtilisateur",required=false)int idUtilisateur){

        pretService.reserverPret(idOuvrage,idUtilisateur);

    }

    @GetMapping(value="/DateRetourPlusProche/{idOuvrage}")
    public Date afficherDateRetourLaPlusProche(@PathVariable ("idOuvrage") int idOuvrage){

        return pretService.afficherDateRetourLaPlusProche(idOuvrage);
    }

    @PutMapping(value="/Pret/{idPret}")
    public void validerPret(@PathVariable("idPret") int idPret){

        pretService.validerPret(idPret);

    }

    @PutMapping(value="/RetourPret/{idPret}")
    public Pret retourPret(@PathVariable ("idPret") int idPret){

        Pret pret = pretService.retourPret(idPret);
        if (pret==null) throw new PretIntrouvableException("Le prêt avec l'id "+idPret+" est introuvable.");

        return pret;
    }

    @PutMapping(value="/ProlongationPret/{idPret}")
    public Pret prolongationPret(@PathVariable ("idPret") int idPret){

        Pret pret = pretService.prolongationPret(idPret);
        if (pret==null) throw new PretIntrouvableException("Le prêt avec l'id "+idPret+" est introuvable.");

        return pret;
    }

    @DeleteMapping(value="/Pret/{idPret}")
    public void annulerPret(@PathVariable("idPret") int idPret){

        pretService.annulerPret(idPret);

    }

    @DeleteMapping(value="/Resa/{idReservationListeAttente}")
    public void annulerResa(@PathVariable("idReservationListeAttente") int idReservationListeAttente){

        reservationListeAttenteService.annulerResa(idReservationListeAttente);

    }

    @GetMapping(value="/Resa/{idPret}")
    public ReservationListeAttente afficherResaByPret(@PathVariable("idPret") int idPret){

        return reservationListeAttenteService.afficherUneResaByIdPret(idPret);

    }

    @GetMapping(value="/Reservations")
    public List<ReservationListeAttente> afficherToutesReservations(){

        return reservationListeAttenteService.afficherListeAttenteResas();

    }





}
