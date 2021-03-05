package com.ocr.library.service.pret;


import com.ocr.library.beans.UtilisateurBean;
import com.ocr.library.dao.PretDao;
import com.ocr.library.dao.ReservationListeAttenteDao;
import com.ocr.library.model.*;
import com.ocr.library.service.exemplaire.IExemplaireService;
import com.ocr.library.service.mail.IEmailService;
import com.ocr.library.service.reservationListeAttente.IReservationListeAttenteService;
import com.ocr.library.service.ouvrage.IOuvrageService;
import com.ocr.library.service.utilisateurbean.IUtilisateurBeanService;
import com.ocr.library.web.exceptions.PretIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class IPretServiceImpl implements IPretService{

    @Autowired
    PretDao pretDao;

    @Autowired
    IOuvrageService ouvrageService;

    @Autowired
    IExemplaireService exemplaireService;

    @Autowired
    IUtilisateurBeanService utilisateurBeanService;

    @Autowired
    IReservationListeAttenteService listeAttenteReservationService;

    @Autowired
    IEmailService emailService;



    /**
     *
     * @param idPret
     * @return pret
     */
    @Override
    public Pret afficherUnPret(int idPret) {

        Pret pret = pretDao.findById(idPret);

        return pret;
    }

    /**
     *
     * @return prets
     */
    @Override
    public List<Pret> listePrets() {

        List <Pret> prets = pretDao.findAll();

        return prets;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Pret> listePretsEnCours() {
        return pretDao.findPretsEnCours();
    }

    /**
     *
     * @param idUtilisateur
     * @return prets
     */
    @Override
    public List<Pret> afficherListePretsUtilisateur(int idUtilisateur) {

        List<Pret> prets = pretDao.findPretsByUser(idUtilisateur);

        return prets;
    }


    /**
     *
     * @param idUtilisateur
     * @return listeDemandesPretByUtilisateur
     */
    @Override
    public List<Pret> listeDemandesPretByUtilisateur(int idUtilisateur) {

        List<Pret> listeDemandesPretByUtilisateur = pretDao.listeDemandesPretByUtilisateur(idUtilisateur);

        return listeDemandesPretByUtilisateur;
    }

    /**
     *
     * @param idOuvrage
     * @param idUtilisateur
     * @return pret
     */
    @Override
    public Pret demanderPret(int idOuvrage,int idUtilisateur) {

        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(idUtilisateur);

        verifResaEnCours(ouvrage.getId(),utilisateur.getId());
        verifDemandeDePret(ouvrage.getId(),utilisateur.getId());
        verifPretEnCours(ouvrage.getId(),utilisateur.getId());

        Pret pret = new Pret();
        Calendar cal = Calendar.getInstance();
        Date dateReservation = cal.getTime();

        pret.setDateReservation(dateReservation);
        pret.setStatut(PretStatutEnum.EN_ATTENTE);
        pret.setProlongeable(false);
        pret.setOuvrage(ouvrage);
        pret.setIdUtilisateur(utilisateur.getId());

        pretDao.save(pret);

        return pret;
    }


    @Override
    public void verifDemandeDePret(int idOuvrage, int idUtilisateur) {

        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(idUtilisateur);

        //verification si l'utilisateur n'a pas déjà une demande de pret pour cet ouvrage
        List<Pret> demandesPret = pretDao.listeDemandesPretByOuvrage(ouvrage.getId());
        for (Pret emprunt : demandesPret) {
            if (emprunt.getIdUtilisateur()==utilisateur.getId()) {
                throw new PretIntrouvableException("Vous avez déjà une demande de prêt en attente de validation" +
                        " pour l'ouvrage " + ouvrage.getTitre() + ". Veuillez patienter," +
                        " la réponse de la Bibliothèque est imminente !");
            }
        }
    }

    @Override
    public void verifResaEnCours(int idOuvrage, int idUtilisateur) {

        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(idUtilisateur);

        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<ReservationListeAttente> reservations = listeAttenteReservationService.afficherListeAttenteResasOuvrage(ouvrage.getId());
        for (ReservationListeAttente reservation : reservations) {
            if (reservation.getIdUtilisateur()==utilisateur.getId()) {
                throw new PretIntrouvableException("Vous possédez déjà une reservation en attente pour cet ouvrage; veuillez patienter, la réponse de la Bibliothèque Municipale est imminente !");
            }
        }
    }



    @Override
    public void verifPretEnCours(int idOuvrage, int idUtilisateur) {

        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(idUtilisateur);

        //verification si l'utilisateur n'a pas déjà un emprunt en cours pour cet ouvrage
        List<Pret> prets = pretDao.findPretsByOuvrage(ouvrage.getId());
        for (Pret emprunt : prets) {
            if (emprunt.getIdUtilisateur()==utilisateur.getId()){
                throw new PretIntrouvableException("Vous possédez déjà l'exemplaire N° "+emprunt.getExemplaire().getId()+
                        " appartenant à cet ouvrage, veuillez d'abord retourner l'exemplaire en votre possession"+
                        " pour pouvoir en emprunter un autre");
            }
        }
    }


    /**
     *
     * @param idOuvrage
     * @param idUtilisateur
     * @return
     */
    @Override
    public ReservationListeAttente reserverPret(int idOuvrage, int idUtilisateur) {

        ReservationListeAttente reservation = new ReservationListeAttente();
        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(idUtilisateur);



        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<ReservationListeAttente> reservations = listeAttenteReservationService.afficherListeAttenteResasOuvrage(ouvrage.getId());
        for (ReservationListeAttente resa : reservations) {
            if (resa.getIdUtilisateur()==utilisateur.getId()){
                throw new PretIntrouvableException("Vous possédez déjà une reservation en attente pour cet ouvrage."+
                        " Veuillez patienter, l'ouvrage sera bientôt de nouveau disponible !");
            }
        }

        //verification si l'utilisateur n'a pas déjà une demande de pret pour cet ouvrage
        List<Pret> demandesPret = pretDao.listeDemandesPretByOuvrage(ouvrage.getId());
        for (Pret emprunt : demandesPret) {
            if (emprunt.getIdUtilisateur()==utilisateur.getId()){
                throw new PretIntrouvableException("Vous avez déjà une demande de prêt en attente de validation"+
                        " pour l'ouvrage "+ouvrage.getTitre()+". Veuillez patienter,"+
                        " la réponse de la Bibliothèque est imminente !");
            }
        }
        //verification si l'utilisateur n'a pas déjà un emprunt en cours pour cet ouvrage
        List<Pret> prets = pretDao.findPretsByOuvrage(ouvrage.getId());
        for (Pret emprunt : prets) {
            if (emprunt.getIdUtilisateur()==utilisateur.getId()){
                throw new PretIntrouvableException("Vous possédez déjà l'exemplaire N° "+emprunt.getExemplaire().getId()+
                        " appartenant à cet ouvrage, veuillez d'abord retourner l'exemplaire en votre possession"+
                        " pour pouvoir en emprunter un autre !");
            }
        }


        // vérification si la liste d'attente de l'ouvrage n'est pas complète avant d'y ajouter la reservation
        if (ouvrage.getReservationsListeAttente().size() < ouvrage.getNbExemplairesTotal()*2) {

            Pret pret = new Pret();
            pret.setStatut(PretStatutEnum.SUR_LISTE);
            pret.setProlongeable(false);
            pret.setOuvrage(ouvrage);
            pret.setIdUtilisateur(utilisateur.getId());
            pretDao.save(pret);

            Calendar cal = Calendar.getInstance();
            Date dateDemande = cal.getTime();

            reservation.setIdUtilisateur(utilisateur.getId());
            reservation.setOuvrage(ouvrage);
            reservation.setDateDemande(dateDemande);
            reservation.setPret(pret);
            listeAttenteReservationService.save(reservation);



            List<ReservationListeAttente> resasListeAttente = listeAttenteReservationService.afficherListeAttenteResasOuvrage(idOuvrage);

            // On met à jour la date retour la plus proche
            for(ReservationListeAttente resa : resasListeAttente){
                Date dateRetourPlusProche = pretDao.findPretsByOuvrageAndDateFinIsNotNullAndDateRetourIsNullOrderByDateFin(idOuvrage).get(0).getDateFin();
                resa.getOuvrage().setDateRetourLaPlusProche(dateRetourPlusProche);
                listeAttenteReservationService.save(resa);
                ouvrageService.save(ouvrage);

            }
            // On met à jour la liste d'attente de l'ouvrage
            ouvrage.setReservationsListeAttente(resasListeAttente);
            ouvrageService.save(ouvrage);

            // On met à jour la position de l'utilisateur dans la liste d'attente
            for (int i=0; i<resasListeAttente.size(); i++){
                if(resasListeAttente.get(i).getIdUtilisateur()==idUtilisateur){
                    reservation.setPosition(i+1);
                    listeAttenteReservationService.save(reservation);
                    ouvrageService.save(ouvrage);
                }
            }

        }

        return reservation;
    }

    /**
     *
     * @param idOuvrage
     * @return dateRetourLaPlusProche
     */
    @Override
    public Date afficherDateRetourLaPlusProche(int idOuvrage) {

        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        Date dateRetourLaPlusProche = pretDao.findPretsByOuvrageAndDateFinIsNotNullAndDateRetourIsNullOrderByDateFin(idOuvrage).get(0).getDateFin();

        ouvrage.setDateRetourLaPlusProche(dateRetourLaPlusProche);

        ouvrageService.save(ouvrage);

        return dateRetourLaPlusProche;
    }


    /**
     *
     * @param idPret
     * @return pret
     */
    @Override
    public Pret validerPret(int idPret) {

        Pret pret = pretDao.findById(idPret);

        //Si l'ouvrage devient indisponible entre la demande de prêt et la validation de prêt, la demande de prêt devient une demande de reservation
        if(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).isEmpty() && pret.getReservation()==null) {

            ReservationListeAttente reservation = new ReservationListeAttente();

            Calendar cal = Calendar.getInstance();
            Date dateDemande = cal.getTime();

            reservation.setIdUtilisateur(pret.getIdUtilisateur());
            reservation.setOuvrage(pret.getOuvrage());
            reservation.setDateDemande(dateDemande);
            reservation.setPret(pret);
            listeAttenteReservationService.save(reservation);

            pret.setStatut(PretStatutEnum.SUR_LISTE);
            pretDao.save(pret);


            List<ReservationListeAttente> resasListeAttente = listeAttenteReservationService.afficherListeAttenteResasOuvrage(pret.getOuvrage().getId());
            // On met à jour la liste d'attente de l'ouvrage
            pret.getOuvrage().setReservationsListeAttente(resasListeAttente);
            ouvrageService.save(pret.getOuvrage());

            // On met à jour la position de l'utilisateur dans la liste d'attente
            for (int i=0; i<resasListeAttente.size(); i++){
                if(resasListeAttente.get(i).getIdUtilisateur().equals(pret.getIdUtilisateur())){
                    reservation.setPosition(i+1);
                    listeAttenteReservationService.save(reservation);
                    ouvrageService.save(pret.getOuvrage());
                }
            }

            // On met à jour la date retour la plus proche
            for(ReservationListeAttente resa : resasListeAttente){
                Date dateRetourPlusProche = pretDao.findPretsByOuvrageAndDateFinIsNotNullAndDateRetourIsNullOrderByDateFin(pret.getOuvrage().getId()).get(0).getDateFin();
                resa.getOuvrage().setDateRetourLaPlusProche(dateRetourPlusProche);
                listeAttenteReservationService.save(resa);
                ouvrageService.save(pret.getOuvrage());
            }


        }else {

            pret.setExemplaire(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).get(0));
            pret.getExemplaire().setDisponible(false);
            pret.getOuvrage().setNbExemplairesDispo(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).size() - 1);

            Calendar cal = Calendar.getInstance();
            Date debutPret = cal.getTime();
            pret.setDateDebut(debutPret);

            cal.add(Calendar.MONTH, 1);
            Date finPret = cal.getTime();
            pret.setDateFin(finPret);

            pret.setStatut(PretStatutEnum.EN_COURS);
            pret.setProlongeable(true);

            pretDao.save(pret);
            ouvrageService.save(pret.getOuvrage());
        }

            if(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).isEmpty()){
                pret.getOuvrage().setStatut(false);
                ouvrageService.save(pret.getOuvrage());

                List<Pret> prets = pretDao.findPretsByOuvrage(pret.getOuvrage().getId());
                // On met à jour la date retour la plus proche
                for(Pret emprunt : prets){
                    Date dateRetourPlusProche = pretDao.findPretsByOuvrageAndDateFinIsNotNullAndDateRetourIsNullOrderByDateFin(pret.getOuvrage().getId()).get(0).getDateFin();
                    emprunt.getOuvrage().setDateRetourLaPlusProche(dateRetourPlusProche);
                    pretDao.save(emprunt);
                    ouvrageService.save(emprunt.getOuvrage());
                }
            }

            //Si le pret venait d'une reservation, en le validant on supprime la reservation
            if(pret.getReservation()!=null){

                emailService.afficherMailByIdReservation(pret.getReservation().getId()).setStatut(MailStatutEnum.RESAOK);
                listeAttenteReservationService.supprimerResa(pret.getReservation().getId());

                List<ReservationListeAttente> resasListeAttente = listeAttenteReservationService.afficherListeAttenteResasOuvrage(pret.getOuvrage().getId());
                // On met à jour les positions des reservations dans la liste d'attente
                for (int i=0; i<resasListeAttente.size(); i++){
                    resasListeAttente.get(i).setPosition(i+1);
                    listeAttenteReservationService.save(resasListeAttente.get(i));
                }
                // On met à jour la liste des reservations de l'ouvrage
                pret.getOuvrage().setReservationsListeAttente(resasListeAttente);
                pretDao.save(pret);
                ouvrageService.save(pret.getOuvrage());
            }

        return pret;

    }



    /**
     *
     * @param idPret
     * @return pretProlonge
     */
    @Override
    public Pret prolongationPret(int idPret) {
        Pret pret = pretDao.findById(idPret);
        if (pret==null) throw new PretIntrouvableException("Le prêt avec l'id "+idPret+" est introuvable.");

        Date finPretInitiale = pret.getDateFin();
        Calendar cal = Calendar.getInstance();
        cal.setTime(finPretInitiale);

        cal.add(Calendar.MONTH,1);
        Date finPretProlonge = cal.getTime();
        pret.setDateFin(finPretProlonge);
        pret.setStatut(PretStatutEnum.PROLONGE);
        pret.setProlongeable(false);

        Pret pretProlonge = pretDao.save(pret);

        return pretProlonge;
    }



    /**
     *
     * @param idPret
     * @return pret
     */
    @Override
    public Pret retourPret(int idPret) {

        Pret pret = pretDao.findById(idPret);
        Ouvrage ouvrage = pret.getOuvrage();

        Calendar cal = Calendar.getInstance();
        Date retour = cal.getTime();

        pret.setDateRetour(retour);
        pret.setStatut(PretStatutEnum.TERMINE);
        pret.setProlongeable(false);
        pret.getExemplaire().setDisponible(true);
        pret.getOuvrage().setNbExemplairesDispo(exemplaireService.getExemplairesDisponiblesByOuvrageId(ouvrage.getId()).size()+1);
        pretDao.save(pret);
        ouvrageService.save(ouvrage);



        // Si la liste d'attente de l'ouvrage n'est pas vide
        if (!pret.getOuvrage().getReservationsListeAttente().isEmpty()) {

            // On met à jour la date retour la plus proche
            Date dateRetourLaPlusProche = pretDao.findPretsByOuvrageAndDateFinIsNotNullAndDateRetourIsNullOrderByDateFin(ouvrage.getId()).get(0).getDateFin();
            ouvrage.setDateRetourLaPlusProche(dateRetourLaPlusProche);
            ouvrageService.save(ouvrage);

            // on envoie un mail au premier utilisateur de la liste
            try {
                emailService.envoiMailRetourPret(pret);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }


        // si la liste d'attente de l'ouvrage est vide, l'ouvrage devient à nouveau disponible
        if (pret.getOuvrage().getReservationsListeAttente().isEmpty()) {


            pret.getOuvrage().setStatut(true);

            pretDao.save(pret);
            ouvrageService.save(ouvrage);
            }

        return pret;

    }




    /**
     *
     * @param idPret
     * @return pret
     */
    @Override
    public void annulerPret(int idPret) {

        Pret pret = pretDao.findById(idPret);

        pretDao.delete(pret);

    }




}
