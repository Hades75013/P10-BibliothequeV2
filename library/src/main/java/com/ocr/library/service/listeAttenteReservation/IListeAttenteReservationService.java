package com.ocr.library.service.listeAttenteReservation;

import com.ocr.library.model.ListeAttenteReservation;

import java.util.List;

public interface IListeAttenteReservationService {

    ListeAttenteReservation save(ListeAttenteReservation listeAttenteReservation);

    List<ListeAttenteReservation> listeAttenteResas();

    ListeAttenteReservation afficherUneResaListeAttente(int idListeAttenteReservation);

    List<ListeAttenteReservation> afficherListeAttenteResasOuvrage(int idOuvrage);

    List<ListeAttenteReservation> afficherListeAttenteResasUtilisateur(int idUtilisateur);


}
