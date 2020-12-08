package com.ocr.library.service.exemplaire;

import com.ocr.library.model.Exemplaire;

import java.util.List;


public interface IExemplaireService {

    List<Exemplaire> findAll();

    List<Exemplaire> save(List<Exemplaire> exemplaires);


    Exemplaire findById(int idExemplaire);

    List<Exemplaire> findAllByOuvrageId(int idOuvrage);

    List<Exemplaire> getExemplairesDisponiblesByOuvrageId(int idOuvrage);


}
