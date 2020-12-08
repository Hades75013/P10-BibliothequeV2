package com.ocr.library.service.pret;

import com.ocr.library.model.Pret;

import java.util.Date;
import java.util.List;

public interface IPretService {

    List<Pret> listePrets();

    Pret afficherUnPret(int idPret);

    List<Pret> afficherListePretsUtilisateur(int idUtilisateur);

    List<Pret> afficherListeResasUtilisateur(int idUtilisateur);



    Pret demanderPret(int idOuvrage, int idUtilisateur);

    Pret validerPret(int idPret);

    Pret retourPret(int idPret);

    Pret prolongationPret(int idPret);
}
