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
import java.util.Date;
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
        List<ReservationListeAttenteBean> listeAttente = libraryProxyService.afficherListeAttenteResasOuvrage(idOuvrage);

        model.addAttribute("ouvrage",ouvrage);
        model.addAttribute("nbPersonnesListeAttente",listeAttente.size());



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
    public String listeResaByUser (Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReservationListeAttenteBean> resas = libraryProxyService.listeResasByUtilisateur(utilisateur.getId());

        model.addAttribute("listeResasUtilisateur", resas);
        model.addAttribute("utilisateur",utilisateur );


        return "listereservations";
    }

    @GetMapping(value="/DemandeDePretsUtilisateur")
    public String listeDemandesPretByUser (Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PretBean> demandesPret = libraryProxyService.listeDemandesPretByUtilisateur(utilisateur.getId());

        model.addAttribute("listeDemandePretByUtilisateur", demandesPret);
        model.addAttribute("utilisateur",utilisateur );


        return "listedemandespret";
    }

    @GetMapping(value="/PretsUtilisateur")
    public String listePretsByUser ( Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PretBean> prets = libraryProxyService.listePretsByUtilisateur(utilisateur.getId());

        model.addAttribute("listePretsUtilisateur", prets);
        model.addAttribute("utilisateur",utilisateur );

        return "listeprets";
    }

    @GetMapping(value="/DemandeDePretsAdmin")
    public String listeDemandesPretAdmin (Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PretBean> demandesPretAdmin = libraryProxyService.listePrets();

        model.addAttribute("listeDemandesPretAdmin", demandesPretAdmin);
        model.addAttribute("utilisateur",utilisateur );


        return "listedemandepretsadmin";
    }

    @GetMapping(value="/DemandeDeReservationsAdmin")
    public String listeDemandesResaAdmin (Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReservationListeAttenteBean> demandesReservationsAdmin = libraryProxyService.afficherToutesReservations();

        model.addAttribute("listeDemandesReservationsAdmin", demandesReservationsAdmin);
        model.addAttribute("utilisateur",utilisateur );


        return "listedemandereservationsadmin";
    }
    @GetMapping(value="/DemanderPret")
    public String DemandePret( int idOuvrage, Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        libraryProxyService.demanderPret(idOuvrage, utilisateur.getId());

        OuvrageBean ouvrage = libraryProxyService.afficherUnOuvrage(idOuvrage);
        model.addAttribute("ouvrage", ouvrage);

        return "confirmationdemandepret";
    }

    @GetMapping(value="/ReserverPret")
    public String ReservePret( int idOuvrage, Model model){

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OuvrageBean ouvrage = libraryProxyService.afficherUnOuvrage(idOuvrage);

        libraryProxyService.reserverPret(idOuvrage, utilisateur.getId());

        List<ReservationListeAttenteBean> listeAttente = libraryProxyService.afficherListeAttenteResasOuvrage(idOuvrage);

        model.addAttribute("ouvrage", ouvrage);
        model.addAttribute("listeAttente", listeAttente);

        return "confirmationdemanderesa";
    }

    @GetMapping(value="/ValiderPret")
    public String ValidationPret(int idPret, Model model){

        libraryProxyService.validerPret(idPret);

        PretBean pret = libraryProxyService.afficherUnPret(idPret);
        UtilisateurBean utilisateur = utilisateurProxyService.recupererUtilisateur(pret.getIdUtilisateur());
        List<ReservationListeAttenteBean> resas = libraryProxyService.afficherListeAttenteResasOuvrage(pret.getOuvrage().getId());
        ReservationListeAttenteBean reservation = libraryProxyService.afficherResaByPret(idPret);

        model.addAttribute("pret", pret);
        model.addAttribute("reservation", reservation);
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("listeAttenteResasByOuvrage", resas);

        return "confirmationpret";
    }

    @GetMapping(value="/ValiderReservation")
    public String ValidationResa(int idPret, Model model){

        libraryProxyService.validerPret(idPret);

        PretBean pret = libraryProxyService.afficherUnPret(idPret);
        UtilisateurBean utilisateur = utilisateurProxyService.recupererUtilisateur(pret.getIdUtilisateur());

        model.addAttribute("pret", pret);
        model.addAttribute("utilisateur", utilisateur);

        return "confirmationresa";
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

    @GetMapping(value="/AnnulerPret")
    public String SuppressionPret(int idPret){

        libraryProxyService.annulerPret(idPret);

        return "redirect:/DemandeDePretsUtilisateur";
    }

    @GetMapping(value="/AnnulerResa")
    public String SuppressionResa(int idReservationListeAttente){

        libraryProxyService.annulerResa(idReservationListeAttente);

        return "redirect:/ResasUtilisateur";
    }

}