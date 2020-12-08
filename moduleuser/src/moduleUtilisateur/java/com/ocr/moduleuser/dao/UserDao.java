package com.ocr.moduleuser.dao;

import com.ocr.moduleuser.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao extends JpaRepository<Utilisateur,Integer> {

    Utilisateur findByEmail(String email);

    Utilisateur save(Utilisateur utilisateur );

    Utilisateur findById(int id);


}
