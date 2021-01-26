package com.ocr.library.service.mail;

import com.ocr.library.model.Mail;
import com.ocr.library.model.Pret;

import javax.mail.MessagingException;

public interface IEmailService {

    Mail afficherMailByIdReservation(int idReservation);

    void envoiMailRetourPret (Pret pretRendu) throws MessagingException;

    void envoiMailRetourPretSuivant () throws MessagingException;



}
