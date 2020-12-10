package com.ocr.moduleuser.dao;

import com.ocr.moduleuser.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao extends JpaRepository<Utilisateur,Integer> {

    //sauvegarder un utilisateur
    Utilisateur save(Utilisateur utilisateur );

    //trouver un utilisateur par son email
    Utilisateur findByEmail(String email);

    //trouver un utilisateur par son id
    Utilisateur findById(int id);

}
