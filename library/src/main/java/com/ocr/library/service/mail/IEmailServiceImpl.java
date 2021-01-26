package com.ocr.library.service.mail;

import com.ocr.library.beans.UtilisateurBean;
import com.ocr.library.dao.MailDao;
import com.ocr.library.dao.PretDao;
import com.ocr.library.dao.ReservationListeAttenteDao;
import com.ocr.library.model.*;
import com.ocr.library.service.ouvrage.IOuvrageService;
import com.ocr.library.service.pret.IPretService;
import com.ocr.library.service.reservationListeAttente.IReservationListeAttenteService;
import com.ocr.library.service.utilisateurbean.IUtilisateurBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;



@Service
public class IEmailServiceImpl implements  IEmailService{

    @Autowired
    IUtilisateurBeanService utilisateurBeanService;

    @Autowired
    MailDao mailDao;

    @Autowired
    IPretService pretService;

    @Autowired
    IReservationListeAttenteService reservationListeAttenteService;

    @Autowired
    PretDao pretDao;



    @Override
    public Mail afficherMailByIdReservation(int idReservation) {
        return mailDao.afficherMailByIdReservation(idReservation);
    }


    @Override
    public void envoiMailRetourPret(Pret pretRendu) throws MessagingException {

        Ouvrage ouvrage = pretRendu.getOuvrage();
        List<ReservationListeAttente> resasListeAttente = reservationListeAttenteService.afficherListeAttenteResasOuvrage(ouvrage.getId());
        ReservationListeAttente reservation = resasListeAttente.get(0);
        UtilisateurBean utilisateur = utilisateurBeanService.findById(resasListeAttente.get(0).getIdUtilisateur());

        Pret pret = reservation.getPret();
        pret.setStatut(PretStatutEnum.EN_ATTENTE_RESA);
        pretDao.save(pret);



        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("p7biblioadm@gmail.com");
        mailSender.setPassword("p7biblioadm12345");

        Properties mailProperties = mailSender.getJavaMailProperties();
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.debug", "true");

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String texte = "Bonjour " +utilisateur.getPrenom()+ ", l'ouvrage " +ouvrage.getTitre()+ " est de nouveau disponible en rayon."
                + " Sachez que vous disposez de 48 heures pour venir récupérer l'exemplaire, faute de quoi votre réservation sera annulée.";

        helper.setFrom("p7biblioadm@gmail.com");
        helper.setTo(utilisateur.getEmail());
        helper.setSubject("Ouvrage " +ouvrage.getTitre()+ " disponible");
        helper.setText(texte);

        mailSender.send(message);

        Mail mail = new Mail();
        Calendar cal = Calendar.getInstance();
        Date dateEnvoi = cal.getTime();

        mail.setDateEnvoi(dateEnvoi);
        mail.setIdUtilisateur(utilisateur.getId());
        mail.setIdPretRendu(pretRendu.getId());
        mail.setIdReservation(reservation.getId());
        mail.setStatut(MailStatutEnum.ENVOYE);

        mailDao.save(mail);

    }

    @Override
    @Scheduled(cron = "0 * * * * *")
    public void envoiMailRetourPretSuivant() throws MessagingException {

        List<Mail> mails = mailDao.afficherMailsEnvoyes();

        for(Mail mail : mails) {

            Pret pret = reservationListeAttenteService.afficherUneResa(mail.getIdReservation()).getPret();
            Calendar cal = Calendar.getInstance();
            Date dateNow = cal.getTime();

            cal.setTime(mail.getDateEnvoi());
            cal.add(Calendar.DAY_OF_MONTH, 2);
            Date dateEnvoiMailPlus48H = cal.getTime();

            //Si les 48h sont passées et que le prêt est toujours en attente malgré le mail envoyé, on supprime la reservation de l'utilisateur
            if (dateNow.after(dateEnvoiMailPlus48H) && pret.getStatut().equals(PretStatutEnum.EN_ATTENTE_RESA)) {

                    reservationListeAttenteService.annulerResa(mail.getIdReservation());
                    mail.setStatut(MailStatutEnum.RESAKO);
                    mailDao.save(mail);

                    // On envoie un mail au nouveau premier utilisateur de la liste
                    Pret pretRendu = pretService.afficherUnPret(mail.getIdPretRendu());
                    envoiMailRetourPret(pretRendu);
                }

            }


        }



}



