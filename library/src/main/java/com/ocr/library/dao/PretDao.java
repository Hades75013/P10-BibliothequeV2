package com.ocr.library.dao;


import com.ocr.library.model.Pret;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PretDao extends JpaRepository <Pret,Integer> {

    //trouver un pret par son id
    @Query("select p from Pret p where p.id = :idPret")
    Pret findById(int idPret);

    //trouver tous les prets
    List<Pret> findAll();

    //trouver toutes les reservations ( = pret en attente) par utilisateur
    @Query("select p from Pret p where p.statut = 'EN_ATTENTE' and p.idUtilisateur = :idUtilisateur")
    List<Pret> findResasByUser(int idUtilisateur);

    //trouver tous les prets par utilisateur
    @Query("select p from Pret p where p.statut != 'EN_ATTENTE' and p.idUtilisateur = :idUtilisateur")
    List<Pret> findPretsByUser(int idUtilisateur);

    //trouver tous les prets en retard
    @Query("select p from Pret p where p.dateRetour is null and trim(p.dateFin) < now()")
    Page<Pret> findPretByStatus(Long status, Long status1, Pageable pageable);

}
