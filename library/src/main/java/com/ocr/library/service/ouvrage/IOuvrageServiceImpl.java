package com.ocr.library.service.ouvrage;

import com.ocr.library.dao.OuvrageDao;
import com.ocr.library.model.Ouvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOuvrageServiceImpl implements IOuvrageService{

    @Autowired
    OuvrageDao ouvrageDao;

    /**
     *
     * @param ouvrage
     * @return ouvrage
     */
    @Override
    public Ouvrage save(Ouvrage ouvrage) {

        ouvrageDao.save(ouvrage);

        return ouvrage;
    }

    /**
     *
     * @return ouvrages
     */
    public List<Ouvrage> listeOuvrages(){

        List <Ouvrage> ouvrages = ouvrageDao.findAll();

        return ouvrages;

    }

    /**
     *
     * @param id
     * @return ouvrage
     */
    public Ouvrage afficherUnOuvrage(int id){

        Ouvrage ouvrage = ouvrageDao.findById(id);

        return ouvrage;
    }

    /**
     *
     * @param titre
     * @return ouvrages
     */
    public List<Ouvrage> rechercheByTitre(@Param("x") String titre){

        List<Ouvrage> ouvrages = ouvrageDao.findByTitre("%"+titre+"%");

        return ouvrages;
    }

    /**
     *
     * @param auteur
     * @return ouvrages
     */
    public List <Ouvrage> rechercheByAuteur(@Param("x") String auteur){

        List <Ouvrage> ouvrages = ouvrageDao.findByAuteur("%"+auteur+"%");

        return ouvrages;
    }

    /**
     *
     * @param genre
     * @return ouvrages
     */
    public List <Ouvrage> rechercheByGenre(@Param("x") String genre){

        List <Ouvrage> ouvrages = ouvrageDao.findByGenre("%"+genre+"%");

        return ouvrages;

    }

    /**
     *
     * @param statut
     * @return ouvrages
     */
    public List <Ouvrage> rechercheByDispo(Boolean statut){

        List <Ouvrage> ouvrages = ouvrageDao.findByDispo(true);

        return ouvrages;

    }


}
