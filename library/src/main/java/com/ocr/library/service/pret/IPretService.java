package com.ocr.library.service.pret;

import com.ocr.library.model.Pret;
import com.ocr.library.model.ReservationListeAttente;

import java.util.Date;
import java.util.List;

public interface IPretService {

    List<Pret> listePrets();

    List<Pret> listePretsEnCours();

    Pret afficherUnPret(int idPret);

    List<Pret> afficherListePretsUtilisateur(int idUtilisateur);

    List<Pret> listeDemandesPretByUtilisateur(int idUtilisateur);




    Pret demanderPret(int idOuvrage, int idUtilisateur);

    void verifResaEnCours(int idOuvrage, int idUtilisateur);

    void verifDemandeDePret(int idOuvrage, int idUtilisateur);

    void verifPretEnCours(int idOuvrage, int idUtilisateur);


    ReservationListeAttente reserverPret(int idOuvrage, int idUtilisateur);

    Pret validerPret(int idPret);

    Pret retourPret(int idPret);

    Pret prolongationPret(int idPret);

    void annulerPret(int idPret);

    Date afficherDateRetourLaPlusProche(int idOuvrage);

}
