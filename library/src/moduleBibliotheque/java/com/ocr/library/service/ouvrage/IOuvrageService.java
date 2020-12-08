package com.ocr.library.service.ouvrage;

import com.ocr.library.model.Ouvrage;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOuvrageService {

    Ouvrage save(Ouvrage ouvrage);

    List<Ouvrage> listeOuvrages();

    Ouvrage afficherUnOuvrage(int id);

    List<Ouvrage> rechercheByTitre(@Param("x") String titre);

    List <Ouvrage> rechercheByAuteur(@Param("x") String auteur);

    List <Ouvrage> rechercheByGenre(@Param("x") String genre);

    List<Ouvrage> rechercheByDispo(Boolean statut);


}
