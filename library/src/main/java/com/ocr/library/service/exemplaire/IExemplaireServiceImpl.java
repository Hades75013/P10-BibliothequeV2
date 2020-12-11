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


    /**
     *
     * @param idOuvrage
     * @return
     */
    @Override
    public List<Exemplaire> getExemplairesDisponiblesByOuvrageId(int idOuvrage){
        return exemplaireDao.getExemplairesDisponiblesByOuvrageId(idOuvrage);
    }

}
