package com.ocr.library.batch.step;

import com.ocr.library.beans.UtilisateurBean;
import com.ocr.library.model.Pret;
import com.ocr.library.model.PretStatutEnum;
import com.ocr.library.service.utilisateurbean.IUtilisateurBeanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class PretFilterProcessorMail implements ItemProcessor<Pret, MimeMessage> {


    private static final Logger log = LoggerFactory.getLogger(PretFilterProcessorMail.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    IUtilisateurBeanService iUtilisateurBeanService;

    private String sender;
    private String attachment;


    public PretFilterProcessorMail() {
        this.sender = sender;
        this.attachment = attachment;
    }

    @Override
    public MimeMessage process(Pret pret) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        pret.setStatut(PretStatutEnum.DEPASSE);

        UtilisateurBean utilisateur = iUtilisateurBeanService.findById(pret.getIdUtilisateur());

        String texte = "Bonjour "+utilisateur.getPrenom()+
                       ", vous etes en retard pour le retour de votre prêt No "+pret.getId()+
                       " concernant l'ouvrage "+pret.getOuvrage().getTitre()+
                       ". En effet, votre date échéance pour ce prêt était le "+pret.getDateFin()+
                       ", merci de contacter la Biblliothèque Municipale.";

        helper.setFrom("p7biblioadm@gmail.com");
        helper.setTo(utilisateur.getEmail());
        helper.setSubject("Retard de retour de prêt");
        helper.setText(texte);

        return message;
    }


}
