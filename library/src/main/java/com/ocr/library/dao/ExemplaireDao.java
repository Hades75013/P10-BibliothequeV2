package com.ocr.library.dao;


import com.ocr.library.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExemplaireDao extends JpaRepository <Exemplaire,Integer> {

    //trouver tous les exemplaires
    List <Exemplaire> findAll();

    //trouver un exemplaire par son id
    Exemplaire findById(int idExemplaire);

    //trouver tous les exemplaires d'un ouvrage
    List <Exemplaire> findAllByOuvrageId(int idOuvrage);

    //trouver les exemplaires disponibles d'un ouvrage
    @Query("select e from Exemplaire e where e.disponible = true and e.ouvrage.id = :idOuvrage ")
    List <Exemplaire> getExemplairesDisponiblesByOuvrageId(int idOuvrage);

}
