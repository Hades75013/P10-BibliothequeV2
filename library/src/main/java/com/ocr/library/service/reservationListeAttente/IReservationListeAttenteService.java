package com.ocr.library.service.reservationListeAttente;

import com.ocr.library.model.ReservationListeAttente;

import java.util.List;

public interface IReservationListeAttenteService {

    ReservationListeAttente save(ReservationListeAttente reservationListeAttente);

    ReservationListeAttente afficherUneResa(int idReservationListeAttente);

    ReservationListeAttente afficherUneResaByIdPret(int idPret);


    void annulerResa(int idReservationListeAttente);

    void supprimerResa(int idReservationListeAttente);

    List<ReservationListeAttente> afficherListeAttenteResas();

    List<ReservationListeAttente> afficherListeAttenteResasOuvrage(int idOuvrage);

    List<ReservationListeAttente> afficherListeAttenteResasUtilisateur(int idUtilisateur);


}
