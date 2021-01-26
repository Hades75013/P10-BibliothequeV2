package com.ocr.library.dao;


import com.ocr.library.model.ReservationListeAttente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationListeAttenteDao extends JpaRepository<ReservationListeAttente,Integer> {


    @Override
    List<ReservationListeAttente> findAll();

    @Query("select r from ReservationListeAttente r where r.id = :idReservationListeAttente")
    ReservationListeAttente findById(int idReservationListeAttente);

    @Query("select r from ReservationListeAttente r where r.pret.id = :idPret")
    ReservationListeAttente findByIdPret(int idPret);

    @Query("select r from ReservationListeAttente r where r.ouvrage.id = :idOuvrage order by r.dateDemande")
    List<ReservationListeAttente> findAllByOuvrageId(int idOuvrage);

    @Query("select r from ReservationListeAttente r where r.idUtilisateur = :idUtilisateur")
    List<ReservationListeAttente> findAllByUtilisateurId(int idUtilisateur);



}
