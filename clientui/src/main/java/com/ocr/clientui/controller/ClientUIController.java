package com.ocr.clientui.controller;


import com.ocr.clientui.beans.*;
import com.ocr.clientui.security.BCryptManager;
import com.ocr.clientui.service.library.ILibraryProxyService;
import com.ocr.clientui.service.utilisateur.IUtilisateurProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
public class ClientUIController {

    @Autowired
    ILibraryProxyService libraryProxyService;

    @Autowired
    IUtilisateurProxyService utilisateurProxyService;



    // UTILISATEUR CONTROLLER

    @GetMapping(value="/Accueil")
    public String Accueil (){

        return "accueil";
    }

    @GetMapping(value="/Inscription")
    public String Inscription (Model model){

        model.addAttribute("utilisateur",new UtilisateurBean());

        return "forminscription";
    }


    @PostMapping(value="/SauvegarderUser")
    public String SaveUtilisateur(UtilisateurBean utilisateur, Model model){

        Collection<RoleEnumBean> role = new ArrayList<>();
        role.add(RoleEnumBean.USER);
        utilisateur.setRoles(role);

        utilisateur.setMotDePasse(BCryptManager.passwordEncoder().encode(utilisateur.getMotDePasse()));
        utilisateurProxyService.sauverUtilisateur(utilisateur);

        model.addAttribute("utilisateur", utilisateur);

        return "confirmationinscription";
    }


    @GetMapping(value="/Connection")
    public String Connection (){

        return "formconnection";
    }


    @PostMapping(value="/Login")
    public ModelAndView ConnecterUtilisateur (){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/espaceperso");
        }
        return new ModelAndView("formconnection");
    }

    @GetMapping(value="/Deconnection")
    public String DeconnecterUtilisateur (){

        SecurityContextHolder.clearContext();

        return "redirect:/Connection";
    }




    // OUVRAGE CONTROLLER

    @GetMapping(value="/Ouvrages")
    public String Ouvrages (Model model){

        List<OuvrageBean> ouvrages = libraryProxyService.listeOuvrages();

        model.addAttribute("listeOuvrages", ouvrages);

        return "listeouvrages";
    }


    @GetMapping(value="/OuvrageDetails")
    public String OuvragesDetails (int idOuvrage, Model model){

        OuvrageBean ouvrage = libraryProxyService.afficherUnOuvrage(idOuvrage);

        model.addAttribute("ouvrage",ouvrage);

        return "ouvragedetails";
    }


    @GetMapping(value="/RechercheParTitre")
    public String OuvragesByTitre (Model model, @RequestParam(name = "titre", defaultValue="") String titre){

        List<OuvrageBean> ouvragesByTitre = libraryProxyService.rechercheByTitre(titre);

        model.addAttribute("listeOuvrages", ouvragesByTitre);
        model.addAttribute("titre", titre);

        return "listeouvrages";
    }

    @GetMapping(value="/RechercheParAuteur")
    public String OuvragesByAuteur (Model model, @RequestParam(name = "auteur", defaultValue="") String auteur){

        List<OuvrageBean> ouvragesByAuteur = libraryProxyService.rechercheByAuteur(auteur);

        model.addAttribute("listeOuvrages", ouvragesByAuteur);
        model.addAttribute("auteur", auteur);

        return "listeouvrages";
    }

    @GetMapping(value="/RechercheParGenre")
    public String OuvragesByGenre (Model model, @RequestParam(name = "genre") String genre){

        List<OuvrageBean> ouvragesByGenre = libraryProxyService.rechercheByGenre(genre);

        model.addAttribute("listeOuvrages", ouvragesByGenre);
        model.addAttribute("genre", genre);

        return "listeouvrages";
    }

    @GetMapping(value="/RechercheParDispo")
    public String OuvragesByDispo (Model model){

        List<OuvrageBean> ouvragesByDispo = libraryProxyService.rechercheByDispo(true);

        model.addAttribute("listeOuvrages", ouvragesByDispo);

        return "listeouvrages";
    }




    // PRET CONTROLLER

    @GetMapping(value="/EspacePerso")
    public String EspacePerso (Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PretBean> prets = libraryProxyService.listePrets();

        model.addAttribute("listePrets", prets);
        model.addAttribute("utilisateur", utilisateur);

        return "espaceperso";
    }

    @GetMapping(value="/ResasUtilisateur")
    public String listeResaByUser ( Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PretBean> resas = libraryProxyService.listeResasByUtilisateur(utilisateur.getId());

        model.addAttribute("listeResasUtilisateur", resas);
        model.addAttribute("utilisateur",utilisateur );

        return "listereservations";
    }

    @GetMapping(value="/PretsUtilisateur")
    public String listePretsByUser ( Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PretBean> prets = libraryProxyService.listePretsByUtilisateur(utilisateur.getId());

        model.addAttribute("listePretsUtilisateur", prets);
        model.addAttribute("utilisateur",utilisateur );

        return "listeprets";
    }

    @GetMapping(value="/Prets")
    public String AllPrets (Model model){

        List<PretBean> prets = libraryProxyService.listePrets();

        model.addAttribute("listePrets", prets);

        return "listeprets";
    }

    @GetMapping(value="/DemanderPret")
    public String DemandePret( int idOuvrage, Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        libraryProxyService.demanderPret(idOuvrage, utilisateur.getId());

        OuvrageBean ouvrage = libraryProxyService.afficherUnOuvrage(idOuvrage);
        model.addAttribute("ouvrage", ouvrage);

        return "confirmationresa";
    }

    @GetMapping(value="/ReserverPret")
    public String ReservePret( int idOuvrage, Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OuvrageBean ouvrage = libraryProxyService.afficherUnOuvrage(idOuvrage);

        libraryProxyService.reserverPret(idOuvrage, utilisateur.getId());

        model.addAttribute("ouvrage", ouvrage);

        return "confirmationdemanderesa";
    }

    @GetMapping(value="/ValiderPret")
    public String ValidationPret(int idPret, Model model){

        libraryProxyService.validerPret(idPret);
        PretBean pret = libraryProxyService.afficherUnPret(idPret);
        UtilisateurBean utilisateur = utilisateurProxyService.recupererUtilisateur(pret.getIdUtilisateur());

        model.addAttribute("pret", pret);
        model.addAttribute("utilisateur", utilisateur);


        return "confirmationpret";
    }

    @GetMapping(value="/ProlongerPret")
    public String prolongationPret(int idPret, Model model){

        PretBean pret = libraryProxyService.prolongationPret(idPret);

        model.addAttribute("pret", pret);

        return "confirmationprolongation";

    }

    @GetMapping(value="/RetourPret")
    public String finPret(int idPret, Model model){

        PretBean pret = libraryProxyService.retourPret(idPret);
        UtilisateurBean utilisateur = utilisateurProxyService.recupererUtilisateur(pret.getIdUtilisateur());

        model.addAttribute("pret", pret);
        model.addAttribute("utilisateur", utilisateur);

        return "confirmationretour";

    }

    @GetMapping(value="/SupprimerResa")
    public String SuppressionResa(int idPret){

        libraryProxyService.annulerPret(idPret);

        return "redirect:/ResasUtilisateur";
    }

}