package com.ocr.library.web.controller;


import com.ocr.library.model.Pret;
import com.ocr.library.service.pret.IPretService;
import com.ocr.library.web.exceptions.PretIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class PretController {

    @Autowired
    private IPretService pretService;


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
    public List<Pret> listeResasByUtilisateur(@PathVariable ("idUtilisateur")int idUtilisateur){

        List<Pret> resasUtilisateur = pretService.afficherListeResasUtilisateur(idUtilisateur);

        return resasUtilisateur;
    }



    @PostMapping(value="/Pret/{idOuvrage}")
    public void demanderPret(@PathVariable("idOuvrage") int idOuvrage,@RequestParam(value="idUtilisateur",required=false)int idUtilisateur){

        pretService.demanderPret(idOuvrage,idUtilisateur);

    }

    @PostMapping(value="/ReserverPret/{idOuvrage}")
    public void reserverPret(@PathVariable("idOuvrage") int idOuvrage,@RequestParam(value="idUtilisateur",required=false)int idUtilisateur){

        pretService.reserverPret(idOuvrage,idUtilisateur);

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



}
