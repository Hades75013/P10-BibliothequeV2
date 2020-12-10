package com.ocr.library.dao;

import com.ocr.library.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OuvrageDao extends JpaRepository<Ouvrage,Integer> {

    //sauvegarder un ouvrage
    Ouvrage save(Ouvrage ouvrage);

    //trouver un ouvrage par son id
    @Query("select o from Ouvrage o where o.id = :id")
    Ouvrage findById(int id);

    //trouver tous les ouvrages
    @Override
    List<Ouvrage> findAll();

    //trouver les ouvrages par titre
    @Query("select o from Ouvrage o where o.titre like :x")
    List<Ouvrage> findByTitre(@Param("x") String motCle);

    //trouver les ouvrages par auteur
    @Query("select o from Ouvrage o where o.auteur like :x")
    List <Ouvrage> findByAuteur(@Param("x") String motCle);

    //trouver les ouvrages par genre
    @Query("select o from Ouvrage o where o.genre like :x")
    List <Ouvrage> findByGenre(@Param("x") String motCle);

    //trouver les ouvrages par disponibilité
    @Query("select o from Ouvrage o where o.statut=:statut")
    List <Ouvrage> findByDispo(boolean statut);


}
