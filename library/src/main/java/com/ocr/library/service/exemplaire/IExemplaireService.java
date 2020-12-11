package com.ocr.library.service.exemplaire;

import com.ocr.library.model.Exemplaire;

import java.util.List;


public interface IExemplaireService {

    List<Exemplaire> getExemplairesDisponiblesByOuvrageId(int idOuvrage);

}
