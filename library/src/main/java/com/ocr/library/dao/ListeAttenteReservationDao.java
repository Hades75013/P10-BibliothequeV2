package com.ocr.library.dao;


import com.ocr.library.model.ListeAttenteReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ListeAttenteReservationDao extends JpaRepository<ListeAttenteReservation,Integer> {

    @Override
    List<ListeAttenteReservation> findAll();

    @Query("select l from ListeAttenteReservation l where l.id = :id")
    ListeAttenteReservation findById(int id);

    @Query("select l from ListeAttenteReservation l where l.ouvrage.id = :idOuvrage")
    List<ListeAttenteReservation> findAllByOuvrageId(int idOuvrage);

    @Query("select l from ListeAttenteReservation l where l.idUtilisateur.id = :idUtilisateur")
    List<ListeAttenteReservation> findAllByUtilisateurId(int idUtilisateur);




}
