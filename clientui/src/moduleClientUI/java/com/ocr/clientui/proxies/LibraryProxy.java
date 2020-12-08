package com.ocr.clientui.proxies;


import com.ocr.clientui.beans.OuvrageBean;
import com.ocr.clientui.beans.PretBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@FeignClient(name = "microservice-library", url = "localhost:8080")
public interface LibraryProxy {

    @GetMapping(value = "/Ouvrages")
    List<OuvrageBean> listeOuvrages();

    @GetMapping( value = "/Ouvrage/{id}")
    OuvrageBean afficherUnOuvrage(@PathVariable("id") int id);

    @GetMapping(value="/RechercheParTitre")
    List<OuvrageBean> rechercheByTitre(@RequestParam(name = "titre", defaultValue = "")String titre);

    @GetMapping(value="/RechercheParAuteur")
    List<OuvrageBean> rechercheByAuteur(@RequestParam(name = "auteur", defaultValue = "")String auteur);

    @GetMapping(value="/RechercheParGenre")
    List<OuvrageBean> rechercheByGenre(@RequestParam(name = "genre", defaultValue = "")String genre);

    @GetMapping(value="/RechercheParDispo")
    List<OuvrageBean> rechercheByDispo(@RequestParam(name = "statut")Boolean statut);





    @GetMapping(value="/Pret/{idPret}")
    PretBean afficherUnPret(@PathVariable("idPret") int idPret);

    @GetMapping(value="/Prets")
    List<PretBean> listePrets();

    @GetMapping(value="/Prets/{idUtilisateur}")
    List<PretBean> listePretsByUtilisateur(@PathVariable("idUtilisateur") int idUtilisateur);

    @GetMapping(value="/Resas/{idUtilisateur}")
    List<PretBean> listeResasByUtilisateur(@PathVariable("idUtilisateur") int idUtilisateur);

    @PostMapping(value="/Pret/{idOuvrage}")
    PretBean demanderPret(@PathVariable("idOuvrage") int idOuvrage, @RequestParam int idUtilisateur);

    @PutMapping(value="/Pret/{idPret}")
    PretBean validerPret(@PathVariable("idPret") int idPret);

    @PutMapping(value="/RetourPret/{idPret}")
    PretBean retourPret(@PathVariable("idPret") int idPret);

    @PutMapping(value="/ProlongationPret/{idPret}")
    PretBean prolongationPret(@PathVariable("idPret") int idPret);

}
