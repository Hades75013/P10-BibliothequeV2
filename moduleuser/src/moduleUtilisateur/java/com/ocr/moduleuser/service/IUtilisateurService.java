package com.ocr.moduleuser.service;

import com.ocr.moduleuser.model.Utilisateur;


public interface IUtilisateurService {

    Utilisateur findByEmail(String email);

    Utilisateur save(Utilisateur utilisateur);

    Utilisateur findById(int id);


}
