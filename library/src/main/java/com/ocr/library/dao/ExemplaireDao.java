package com.ocr.library.dao;


import com.ocr.library.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExemplaireDao extends JpaRepository <Exemplaire,Integer> {

    //trouver les exemplaires disponibles d'un ouvrage
    @Query("select e from Exemplaire e where e.disponible = true and e.ouvrage.id = :idOuvrage ")
    List <Exemplaire> getExemplairesDisponiblesByOuvrageId(int idOuvrage);

}
