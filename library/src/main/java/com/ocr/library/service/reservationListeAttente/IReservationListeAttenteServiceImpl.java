package com.ocr.library.service.reservationListeAttente;

import com.ocr.library.dao.ReservationListeAttenteDao;
import com.ocr.library.model.Ouvrage;
import com.ocr.library.model.Pret;
import com.ocr.library.model.ReservationListeAttente;
import com.ocr.library.service.ouvrage.IOuvrageService;
import com.ocr.library.service.pret.IPretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IReservationListeAttenteServiceImpl implements IReservationListeAttenteService {

    @Autowired
    ReservationListeAttenteDao listeAttenteReservationDao;

    @Autowired
    IOuvrageService ouvrageService;

    @Autowired
    IPretService pretService;


    @Override
    public ReservationListeAttente save(ReservationListeAttente reservationListeAttente) {
        return listeAttenteReservationDao.save(reservationListeAttente);
    }

    @Override
    public ReservationListeAttente afficherUneResa(int idReservationListeAttente) {
        return listeAttenteReservationDao.findById(idReservationListeAttente);
    }

    @Override
    public ReservationListeAttente afficherUneResaByIdPret(int idPret) {
        return listeAttenteReservationDao.findByIdPret(idPret);
    }

    @Override
    public void supprimerResa(int idReservationListeAttente) {
        listeAttenteReservationDao.deleteById(idReservationListeAttente);
    }

    @Override
    public void annulerResa(int idReservationListeAttente) {

        ReservationListeAttente reservation = listeAttenteReservationDao.findById(idReservationListeAttente);
        Ouvrage ouvrage = reservation.getOuvrage();
        Pret pret = reservation.getPret();

        listeAttenteReservationDao.delete(reservation);
        pretService.annulerPret(pret.getId());

        List<ReservationListeAttente> resasListeAttente = listeAttenteReservationDao.findAllByOuvrageId(ouvrage.getId());

        // On met à jour les positions des reservations dans la liste d'attente
        for (int i=0; i<resasListeAttente.size(); i++){
                resasListeAttente.get(i).setPosition(i+1);
                listeAttenteReservationDao.save(resasListeAttente.get(i));
            }

        // On met à jour la liste des reservations de l'ouvrage
        ouvrage.setReservationsListeAttente(resasListeAttente);
        ouvrageService.save(ouvrage);

    }

    @Override
    public List<ReservationListeAttente> afficherListeAttenteResas() {
        return listeAttenteReservationDao.findAll();
    }


    @Override
    public List<ReservationListeAttente> afficherListeAttenteResasOuvrage(int idOuvrage) {
        return listeAttenteReservationDao.findAllByOuvrageId(idOuvrage);
    }

    @Override
    public List<ReservationListeAttente> afficherListeAttenteResasUtilisateur(int idUtilisateur) {
        return listeAttenteReservationDao.findAllByUtilisateurId(idUtilisateur);
    }
}
