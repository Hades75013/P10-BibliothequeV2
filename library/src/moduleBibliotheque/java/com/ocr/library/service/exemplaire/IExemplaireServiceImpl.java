package com.ocr.library.service.exemplaire;

import com.ocr.library.dao.ExemplaireDao;
import com.ocr.library.model.Exemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IExemplaireServiceImpl implements IExemplaireService{

    @Autowired
    ExemplaireDao exemplaireDao;

    @Override
    public List<Exemplaire> findAll() {
        return exemplaireDao.findAll();
    }

    @Override
    public List<Exemplaire> save(List<Exemplaire> exemplaires) {
        return exemplaireDao.saveAll(exemplaires);
    }

    @Override
    public Exemplaire findById(int idExemplaire) {
        return exemplaireDao.findById(idExemplaire);
    }

    @Override
    public List<Exemplaire> findAllByOuvrageId(int idOuvrage) {
        return exemplaireDao.findAllByOuvrageId(idOuvrage);
    }

    @Override
    public List<Exemplaire> getExemplairesDisponiblesByOuvrageId(int idOuvrage){
        return exemplaireDao.getExemplairesDisponiblesByOuvrageId(idOuvrage);
    }

}
