package com.ocr.library.service.pret;


import com.ocr.library.beans.UtilisateurBean;
import com.ocr.library.dao.PretDao;
import com.ocr.library.model.*;
import com.ocr.library.service.exemplaire.IExemplaireService;
import com.ocr.library.service.listeAttenteReservation.IListeAttenteReservationService;
import com.ocr.library.service.ouvrage.IOuvrageService;
import com.ocr.library.service.utilisateurbean.IUtilisateurBeanService;
import com.ocr.library.web.exceptions.PretIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    IListeAttenteReservationService listeAttenteReservationService;



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
     * @return resas
     */
    @Override
    public List<Pret> afficherListeResasUtilisateur(int idUtilisateur) {

        List<Pret> resas = pretDao.findResasByUser(idUtilisateur);

        return resas;
    }

    /**
     *
     * @param idOuvrage
     * @param idUtilisateur
     * @return pret
     */
    @Override
    public Pret demanderPret(int idOuvrage,int idUtilisateur) {

        Pret pret = new Pret();
        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(idUtilisateur);

        Calendar cal = Calendar.getInstance();
        Date dateReservation = cal.getTime();

        pret.setDateReservation(dateReservation);
        pret.setStatut(PretStatutEnum.EN_ATTENTE);
        pret.setProlongeable(false);
        pret.setOuvrage(ouvrage);
        pret.setIdUtilisateur(utilisateur.getId());


        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<Pret> reservations = pretDao.findResasByUser(utilisateur.getId());
        for (Pret resa : reservations) {
            if (resa.getOuvrage().getTitre().equals(pret.getOuvrage().getTitre())){
                throw new PretIntrouvableException("Vous possédez déjà une reservation en attente pour cet ouvrage, datant du "
                        +resa.getDateReservation()+ "veuillez patienter, la réponse de la Bibliothèque Municipale est imminente !");
            }
        }


        //verification si l'utilisateur n'a pas déjà un emprunt en cours pour cet ouvrage
        List<Pret> prets = pretDao.findPretsByUser(utilisateur.getId());
        for (Pret emprunt : prets) {
            if (emprunt.getOuvrage().getTitre().equals(pret.getOuvrage().getTitre())){
                throw new PretIntrouvableException("Vous possédez déjà l'exemplaire N° "+emprunt.getExemplaire().getId()+
                        " appartenant à cet ouvrage, veuillez d'abord retourner l'exemplaire en votre possession"+
                        " pour pouvoir en emprunter un autre");
            }
        }

        pretDao.save(pret);


        return pret;
    }

    /**
     *
     * @param idOuvrage
     * @param idUtilisateur
     * @return
     */
    @Override
    public Pret reserverPret(int idOuvrage, int idUtilisateur) {

        Pret pret = new Pret();
        Ouvrage ouvrage = ouvrageService.afficherUnOuvrage(idOuvrage);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(idUtilisateur);

        pret.setOuvrage(ouvrage);
        pret.setIdUtilisateur(utilisateur.getId());
        pret.setStatut(PretStatutEnum.SUR_LISTE);
        pret.setProlongeable(false);


        //verification si l'utilisateur n'a pas déjà une réservation en cours pour cet ouvrage
        List<Pret> reservations = pretDao.findResasByUser(utilisateur.getId());
        for (Pret resa : reservations) {
            if (resa.getOuvrage().getTitre().equals(pret.getOuvrage().getTitre())){
                throw new PretIntrouvableException("Vous possédez déjà une reservation en attente pour cet ouvrage; veuillez patienter, la réponse de la Bibliothèque Municipale est imminente !");
            }
        }


        //verification si l'utilisateur n'a pas déjà un emprunt en cours pour cet ouvrage
        List<Pret> prets = pretDao.findPretsByUser(utilisateur.getId());
        for (Pret emprunt : prets) {
            if (emprunt.getOuvrage().getTitre().equals(pret.getOuvrage().getTitre())){
                throw new PretIntrouvableException("Vous possédez déjà l'exemplaire N° "+emprunt.getExemplaire().getId()+
                        " appartenant à cet ouvrage, veuillez d'abord retourner l'exemplaire en votre possession"+
                        " pour pouvoir en emprunter un autre");
            }
        }

        pretDao.save(pret);

        // on vérifie que la liste d'attente de l'ouvrage n'est pas complète avant d'y ajouter la reservation
        if (ouvrage.getListeAttenteReservations().size() < ouvrage.getNbExemplairesTotal()*2) {

            Calendar cal = Calendar.getInstance();
            Date dateDemande = cal.getTime();

            ListeAttenteReservation resa = new ListeAttenteReservation();
            resa.setIdUtilisateur(utilisateur.getId());
            resa.setOuvrage(ouvrage);
            resa.setPret(pret);
            resa.setDateDemande(dateDemande);
            listeAttenteReservationService.save(resa);

            ouvrage.getListeAttenteReservations().add(resa);
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
    public Pret validerPret(int idPret) {

        Pret pret = pretDao.findById(idPret);

        pret.setExemplaire(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).get(0));
        pret.getExemplaire().setDisponible(false);
        pret.getOuvrage().setNbExemplairesDispo(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).size()-1);

        Calendar cal = Calendar.getInstance();
        Date debutPret = cal.getTime();
        pret.setDateDebut(debutPret);

        cal.add(Calendar.MONTH, 1);
        Date finPret = cal.getTime();
        pret.setDateFin(finPret);

        pret.setStatut(PretStatutEnum.EN_COURS);
        pret.setProlongeable(true);

        pretDao.save(pret);

        if(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).isEmpty()){
            pret.getOuvrage().setStatut(false);
            ouvrageService.save(pret.getOuvrage());
        }


        return pret;

    }

    /**
     *
     * @param idPret
     * @return pret
     */
    @Override
    public Pret retourPret(int idPret) {

        Pret pret = pretDao.findById(idPret);
        if (pret==null) throw new PretIntrouvableException("Le prêt avec l'id "+idPret+" est introuvable.");

        Calendar cal = Calendar.getInstance();
        Date retour = cal.getTime();

        pret.setDateRetour(retour);
        pret.setStatut(PretStatutEnum.TERMINE);
        pret.setProlongeable(false);
        pret.getOuvrage().setNbExemplairesDispo(exemplaireService.getExemplairesDisponiblesByOuvrageId(pret.getOuvrage().getId()).size()+1);
        pret.getExemplaire().setDisponible(true);
        pret.getOuvrage().setStatut(true);

        pretDao.save(pret);

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
    public void annulerPret(int idPret) {

        Pret pret = pretDao.findById(idPret);
        ListeAttenteReservation resa = listeAttenteReservationService.afficherUneResaListeAttentePret(pret.getId());

        pret.getOuvrage().getListeAttenteReservations().remove(resa);
        listeAttenteReservationService.delete(resa);
        pretDao.delete(pret);

    }
}
