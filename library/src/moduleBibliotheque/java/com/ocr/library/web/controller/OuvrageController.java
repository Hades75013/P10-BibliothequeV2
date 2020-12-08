package com.ocr.library.web.controller;


import com.ocr.library.model.Exemplaire;
import com.ocr.library.model.Ouvrage;
import com.ocr.library.service.exemplaire.IExemplaireService;
import com.ocr.library.service.ouvrage.IOuvrageService;
import com.ocr.library.web.exceptions.OuvrageIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OuvrageController {

    @Autowired
    private IOuvrageService ouvrageService;

    @Autowired
    private IExemplaireService exemplaireService;



    @GetMapping(value="/Ouvrages")
    public List <Ouvrage> listeOuvrages(){

        List <Ouvrage> ouvrages = ouvrageService.listeOuvrages();

        return ouvrages;
    }

    @GetMapping(value="/Ouvrage/{id}")
    public Ouvrage afficherUnOuvrage(@PathVariable int id){

        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(id);
        if (ouvrage==null) throw new OuvrageIntrouvableException("L'ouvrage avec l'id "+id+" est introuvable.");

        return ouvrage;
    }



   @PutMapping(value="/Exemplaires")
    public List<Ouvrage> Ouvrage () {

        for(Ouvrage ouvrage : ouvrageService.listeOuvrages()) {
            ouvrage.setNbExemplairesDispo(exemplaireService.getExemplairesDisponiblesByOuvrageId(ouvrage.getId()).size());
        }

       return ouvrageService.listeOuvrages();
   }

/*
       Exemplaire exemplaire1 = new Exemplaire();
        exemplaire1.setOuvrage(ouvrage);
        exemplaire1.setDisponible(true);
        exemplaires.add(exemplaire1);


        Exemplaire exemplaire2 = new Exemplaire();
        exemplaire2.setOuvrage(ouvrage);
        exemplaire2.setDisponible(true);
        exemplaires.add(exemplaire2);


        Exemplaire exemplaire3 = new Exemplaire();
        exemplaire3.setOuvrage(ouvrage);
        exemplaire3.setDisponible(true);
        exemplaires.add(exemplaire3);


        Exemplaire exemplaire4 = new Exemplaire();
        exemplaire4.setOuvrage(ouvrage);
        exemplaire4.setDisponible(true);
        exemplaires.add(exemplaire4);

        Exemplaire exemplaire5 = new Exemplaire();
        exemplaire5.setOuvrage(ouvrage);
        exemplaire5.setDisponible(true);
        exemplaires.add(exemplaire5);

        Exemplaire exemplaire6 = new Exemplaire();
        exemplaire6.setOuvrage(ouvrage);
        exemplaire6.setDisponible(true);
        exemplaires.add(exemplaire6);


        Exemplaire exemplaire7 = new Exemplaire();
        exemplaire7.setOuvrage(ouvrage);
        exemplaire7.setDisponible(true);
        exemplaires.add(exemplaire7);

        Exemplaire exemplaire8 = new Exemplaire();
        exemplaire8.setOuvrage(ouvrage);
        exemplaire8.setDisponible(true);
        exemplaires.add(exemplaire8);

        Exemplaire exemplaire9 = new Exemplaire();
        exemplaire9.setOuvrage(ouvrage);
        exemplaire9.setDisponible(true);
        exemplaires.add(exemplaire9);

        Exemplaire exemplaire10 = new Exemplaire();
        exemplaire10.setOuvrage(ouvrage);
        exemplaire10.setDisponible(true);
        exemplaires.add(exemplaire10);


    } */


    @GetMapping(value="/RechercheParTitre")
    public List<Ouvrage> rechercheByTitre(@RequestParam(name = "titre", defaultValue = "")String titre){

        List<Ouvrage> ouvrages = ouvrageService.rechercheByTitre(titre);
        if (ouvrages==null) throw new OuvrageIntrouvableException("Les ouvrages avec le titre "+titre+" est introuvable.");

        return ouvrages;
    }

    @GetMapping(value="/RechercheParAuteur")
    public List <Ouvrage> rechercheByAuteur(@RequestParam(name = "auteur", defaultValue = "") String auteur){

        List <Ouvrage> ouvrages = ouvrageService.rechercheByAuteur(auteur);
        if (ouvrages==null) throw new OuvrageIntrouvableException("Les ouvrages de l'auteur "+auteur+" sont introuvables.");

        return ouvrages;
    }

    @GetMapping(value="/RechercheParGenre")
    public List <Ouvrage> rechercheByGenre(@RequestParam(name = "genre", defaultValue = "") String genre){

        List <Ouvrage> ouvrages = ouvrageService.rechercheByGenre(genre);
        if (ouvrages==null) throw new OuvrageIntrouvableException("Les ouvrages du genre "+genre+" sont introuvables.");

        return ouvrages;

    }

    @GetMapping(value="/RechercheParDispo")
    public List <Ouvrage> rechercheByDispo(boolean statut){

        List <Ouvrage> ouvrages = ouvrageService.rechercheByDispo(statut);
        if (ouvrages==null) throw new OuvrageIntrouvableException("Les ouvrages avec le statut "+statut+" sont introuvables.");

        return ouvrages;

    }


    }




