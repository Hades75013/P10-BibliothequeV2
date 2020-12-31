package com.ocr.library.service.listeAttenteReservation;

import com.ocr.library.model.ListeAttenteReservation;

import java.util.List;

public interface IListeAttenteReservationService {

    ListeAttenteReservation save(ListeAttenteReservation listeAttenteReservation);

    void delete(ListeAttenteReservation listeAttenteReservation);

    List<ListeAttenteReservation> listeAttenteResas();

    ListeAttenteReservation afficherUneResaListeAttentePret(int idPret);

    List<ListeAttenteReservation> afficherListeAttenteResasOuvrage(int idOuvrage);

    List<ListeAttenteReservation> afficherListeAttenteResasUtilisateur(int idUtilisateur);


}
