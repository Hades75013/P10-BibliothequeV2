package com.ocr.library.dao;

import com.ocr.library.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface MailDao extends JpaRepository<Mail,Integer> {

    @Override
    List<Mail> findAll();

    //trouver un mail par son id
    @Query("select m from Mail m where m.id = :idMail")
    Mail findById(int idMail);

    //trouver mail par reservation
    @Query("select m from Mail m where m.idReservation = :idReservation")
    Mail afficherMailByIdReservation(int idReservation);

    @Query("select m from Mail m where m.statut = 'ENVOYE' ")
    List<Mail> afficherMailsEnvoyes();



}
