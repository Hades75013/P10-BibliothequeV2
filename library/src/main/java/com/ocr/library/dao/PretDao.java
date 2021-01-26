package com.ocr.library.dao;


import com.ocr.library.model.Pret;
import com.ocr.library.model.PretStatutEnum;
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

    //trouver toutes les demandes de pret ( = pret en attente) par utilisateur
    @Query("select p from Pret p where p.statut = 'EN_ATTENTE' and p.idUtilisateur = :idUtilisateur order by p.dateReservation")
    List<Pret> listeDemandesPretByUtilisateur(int idUtilisateur);

    //trouver toutes les demandes de pret ( = pret en attente) par ouvrage
    @Query("select p from Pret p where p.statut = 'EN_ATTENTE' and p.ouvrage.id = :idOuvrage order by p.dateReservation")
    List<Pret> listeDemandesPretByOuvrage(int idOuvrage);

    //trouver tous les prets en cours par utilisateur
    @Query("select p from Pret p where (p.statut = 'EN_COURS' or p.statut = 'PROLONGE' ) and p.idUtilisateur = :idUtilisateur order by p.dateReservation")
    List<Pret> findPretsByUser(int idUtilisateur);

    //trouver tous les prets par ouvrage
    @Query("select p from Pret p where (p.statut = 'EN_COURS' or p.statut = 'PROLONGE' ) and p.ouvrage.id = :idOuvrage")
    List<Pret> findPretsByOuvrage(int idOuvrage);

    //trouver tous les prets par ouvrage avec une date de fin
    @Query("select p from Pret p where p.ouvrage.id = :idOuvrage and p.dateFin != null and p.dateRetour = null order by p.dateFin")
    List<Pret> findPretsByOuvrageAndDateFinIsNotNullAndDateRetourIsNullOrderByDateFin(int idOuvrage);




    //trouver tous les prets en retard
    @Query("select p from Pret p where p.dateRetour is null and trim(p.dateFin) < now()")
    Page<Pret> findPretByStatus(Long status, Long status1, Pageable pageable);

}
