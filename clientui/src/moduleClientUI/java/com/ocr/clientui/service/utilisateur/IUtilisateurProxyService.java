package com.ocr.clientui.service.utilisateur;

import com.ocr.clientui.beans.UtilisateurBean;


public interface IUtilisateurProxyService {

    UtilisateurBean sauverUtilisateur(UtilisateurBean utilisateurBean);

    UtilisateurBean connectionUtilisateur(String email);

    UtilisateurBean recupererUtilisateur(int id);



}
