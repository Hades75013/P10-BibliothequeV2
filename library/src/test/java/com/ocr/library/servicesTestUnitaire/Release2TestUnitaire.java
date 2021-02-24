package com.ocr.library.servicesTestUnitaire;


import com.ocr.library.beans.UtilisateurBean;
import com.ocr.library.dao.PretDao;
import com.ocr.library.model.*;
import com.ocr.library.service.ouvrage.IOuvrageService;
import com.ocr.library.service.pret.IPretServiceImpl;
import com.ocr.library.service.reservationListeAttente.IReservationListeAttenteService;
import com.ocr.library.service.utilisateurbean.IUtilisateurBeanService;
import com.ocr.library.web.exceptions.PretIntrouvableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class Release2TestUnitaire {


    @Mock
    private IOuvrageService ouvrageServiceMock;

    @Mock
    private IUtilisateurBeanService utilisateurServiceMock;

    @Mock
    private IReservationListeAttenteService reservationServiceMock;

    @Mock
    private PretDao pretDaoMock;

    @Autowired
    @InjectMocks
    private IPretServiceImpl pretServiceImplMock;



    @Test
    void testDateDeRetourLaplusProche(){

        //GIVEN
        Date date = new Date();

        Ouvrage ouvrage = new Ouvrage();
        ouvrage.setId(1);

        Pret pret = new Pret();
        pret.setOuvrage(ouvrage);
        pret.setDateFin(date);

        List<Pret> listePrets = new ArrayList<>();
        listePrets.add(pret);
        ouvrage.setPrets(listePrets);

        //WHEN
        Mockito.when(ouvrageServiceMock.afficherUnOuvrage(ouvrage.getId())).thenReturn(ouvrage);
        Mockito.when(pretDaoMock.findPretsByOuvrageAndDateFinIsNotNullAndDateRetourIsNullOrderByDateFin(ouvrage.getId())).thenReturn(listePrets);

        //THEN
        pretServiceImplMock.afficherDateRetourLaPlusProche(ouvrage.getId());
        assertThat(ouvrage.getDateRetourLaPlusProche()).isEqualTo(date);
    }


    @Test
    void testUtilisateurADejaUneDemandeDePret(){

        //GIVEN
        UtilisateurBean utilisateur = new UtilisateurBean();
        utilisateur.setId(1);

        Ouvrage ouvrage = new Ouvrage();
        ouvrage.setId(1);

        Pret pret = new Pret();
        pret.setOuvrage(ouvrage);
        pret.setStatut(PretStatutEnum.EN_ATTENTE);
        pret.setIdUtilisateur(utilisateur.getId());

        List<Pret> listePrets = new ArrayList<>();
        listePrets.add(pret);
        ouvrage.setPrets(listePrets);

        //WHEN
        Mockito.when(ouvrageServiceMock.afficherUnOuvrage(ouvrage.getId())).thenReturn(ouvrage);
        Mockito.when(utilisateurServiceMock.findById(utilisateur.getId())).thenReturn(utilisateur);
        Mockito.when(pretDaoMock.listeDemandesPretByOuvrage(ouvrage.getId())).thenReturn(listePrets);

        //THEN
        try {
            pretServiceImplMock.verifDemandeDePret(ouvrage.getId(),utilisateur.getId());
        }catch (PretIntrouvableException e){
            assertThat(e.getMessage()).isEqualTo("Vous avez déjà une demande de prêt en attente de validation"+
                    " pour l'ouvrage "+ouvrage.getTitre()+". Veuillez patienter,"+
                    " la réponse de la Bibliothèque est imminente !");
        }

    }



    @Test
    void testUtilisateurADejaUneReservation(){

        //GIVEN
        UtilisateurBean utilisateur = new UtilisateurBean();
        utilisateur.setId(1);

        Ouvrage ouvrage = new Ouvrage();
        ouvrage.setId(1);

        ReservationListeAttente reservation = new ReservationListeAttente();
        reservation.setOuvrage(ouvrage);
        reservation.setIdUtilisateur(utilisateur.getId());

        List<ReservationListeAttente> listeReservations = new ArrayList<>();
        listeReservations.add(reservation);
        ouvrage.setReservationsListeAttente(listeReservations);

        //WHEN
        Mockito.when(ouvrageServiceMock.afficherUnOuvrage(ouvrage.getId())).thenReturn(ouvrage);
        Mockito.when(utilisateurServiceMock.findById(utilisateur.getId())).thenReturn(utilisateur);
        Mockito.when(reservationServiceMock.afficherListeAttenteResasOuvrage(ouvrage.getId())).thenReturn(listeReservations);

        //THEN
        try {
            pretServiceImplMock.verifResaEnCours(ouvrage.getId(),utilisateur.getId());
        }catch (PretIntrouvableException e){
            assertThat(e.getMessage()).isEqualTo("Vous possédez déjà une reservation en attente pour cet ouvrage; veuillez patienter, la réponse de la Bibliothèque Municipale est imminente !");
        }

    }


    @Test
    void testUtilisateurADejaUnPretEnCours(){

        //GIVEN
        UtilisateurBean utilisateur = new UtilisateurBean();
        utilisateur.setId(1);

        Ouvrage ouvrage = new Ouvrage();
        ouvrage.setId(1);

        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setId(1);

        Pret emprunt = new Pret();
        emprunt.setOuvrage(ouvrage);
        emprunt.setStatut(PretStatutEnum.EN_COURS);
        emprunt.setIdUtilisateur(utilisateur.getId());
        emprunt.setExemplaire(exemplaire);

        List<Pret> listePrets = new ArrayList<>();
        listePrets.add(emprunt);
        ouvrage.setPrets(listePrets);

        //WHEN
        Mockito.when(ouvrageServiceMock.afficherUnOuvrage(ouvrage.getId())).thenReturn(ouvrage);
        Mockito.when(utilisateurServiceMock.findById(utilisateur.getId())).thenReturn(utilisateur);
        Mockito.when(pretDaoMock.findPretsByOuvrage(ouvrage.getId())).thenReturn(listePrets);

        //THEN
        try {
            pretServiceImplMock.verifPretEnCours(ouvrage.getId(),utilisateur.getId());
        }catch (PretIntrouvableException e){
            assertThat(e.getMessage()).isEqualTo("Vous possédez déjà l'exemplaire N° "+emprunt.getExemplaire().getId()+
                    " appartenant à cet ouvrage, veuillez d'abord retourner l'exemplaire en votre possession"+
                    " pour pouvoir en emprunter un autre");
        }

    }


}
