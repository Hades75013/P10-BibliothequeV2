package com.ocr.clientui.service.utilisateur;

import com.ocr.clientui.beans.UtilisateurBean;
import com.ocr.clientui.proxies.UtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IUtilisateurProxyServiceImpl implements IUtilisateurProxyService {

    @Autowired
    private UtilisateurProxy utilisateurProxy;


    @Override
    public UtilisateurBean sauverUtilisateur(UtilisateurBean utilisateurBean) {
        return utilisateurProxy.sauverUtilisateur(utilisateurBean);
    }

    @Override
    public UtilisateurBean connectionUtilisateur(String email) {

        return utilisateurProxy.connectionUtilisateur(email);
    }

    @Override
    public UtilisateurBean recupererUtilisateur(int id) {
        return utilisateurProxy.recupererUtilisateur(id);
    }




}
