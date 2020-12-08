package com.ocr.library.service.utilisateurbean;

import com.ocr.library.beans.UtilisateurBean;
import com.ocr.library.proxies.UtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUtilisateurBeanServiceImpl implements IUtilisateurBeanService{

    @Autowired
    UtilisateurProxy utilisateurProxy;


    @Override
    public UtilisateurBean sauverUtilisateur(UtilisateurBean utilisateurBean) {
        return utilisateurProxy.sauverUtilisateur(utilisateurBean);
    }

    @Override
    public UtilisateurBean connectionUtilisateur(String email) {
        return utilisateurProxy.connectionUtilisateur(email);
    }

    @Override
    public UtilisateurBean findById(int id) {
        return utilisateurProxy.recupererUtilisateur(id);
    }


}
