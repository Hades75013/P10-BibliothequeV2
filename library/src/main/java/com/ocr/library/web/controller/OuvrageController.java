package com.ocr.library.web.controller;


import com.ocr.library.model.Ouvrage;
import com.ocr.library.service.exemplaire.IExemplaireService;
import com.ocr.library.service.ouvrage.IOuvrageService;
import com.ocr.library.web.exceptions.OuvrageIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value="/RechercheParTitre")
    public List<Ouvrage> rechercheByTitre(@RequestParam(name = "titre", defaultValue = "")String titre){

        List<Ouvrage> ouvrages = ouvrageService.rechercheByTitre(titre);
        if (ouvrages==null) throw new OuvrageIntrouvableException("Les ouvrages avec le titre "+titre+" sont introuvables.");

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




