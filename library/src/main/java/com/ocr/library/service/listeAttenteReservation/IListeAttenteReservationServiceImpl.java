package com.ocr.library.service.listeAttenteReservation;

import com.ocr.library.dao.ListeAttenteReservationDao;
import com.ocr.library.model.ListeAttenteReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IListeAttenteReservationServiceImpl implements IListeAttenteReservationService{

    @Autowired
    ListeAttenteReservationDao listeAttenteReservationDao;


    @Override
    public ListeAttenteReservation save(ListeAttenteReservation listeAttenteReservation) {
        return listeAttenteReservationDao.save(listeAttenteReservation);
    }


    @Override
    public List<ListeAttenteReservation> listeAttenteResas() {
        return listeAttenteReservationDao.findAll();
    }

    @Override
    public ListeAttenteReservation afficherUneResaListeAttente(int idListeAttenteReservation) {
        return listeAttenteReservationDao.findById(idListeAttenteReservation);
    }

    @Override
    public List<ListeAttenteReservation> afficherListeAttenteResasOuvrage(int idOuvrage) {
        return listeAttenteReservationDao.findAllByOuvrageId(idOuvrage);
    }

    @Override
    public List<ListeAttenteReservation> afficherListeAttenteResasUtilisateur(int idUtilisateur) {
        return listeAttenteReservationDao.findAllByUtilisateurId(idUtilisateur);
    }
}
