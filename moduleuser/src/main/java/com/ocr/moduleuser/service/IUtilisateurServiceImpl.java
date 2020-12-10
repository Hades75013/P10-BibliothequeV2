package com.ocr.moduleuser.service;

import com.ocr.moduleuser.dao.UserDao;
import com.ocr.moduleuser.model.RoleEnum;
import com.ocr.moduleuser.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class IUtilisateurServiceImpl implements IUtilisateurService{

    @Autowired
    private UserDao userDao;

    /**
     *
     * @param email
     * @return utilisateur
     */
    @Override
    public Utilisateur findByEmail(String email) {

        return userDao.findByEmail(email);
    }

    /**
     *
     * @param utilisateur
     * @return utilisateur
     */
    @Override
    public Utilisateur save(Utilisateur utilisateur) {

        Collection<RoleEnum> role = new ArrayList<>();
        role.add(RoleEnum.USER);

        utilisateur.setRoles(role);

        return userDao.save(utilisateur);
    }

    /**
     *
     * @param id
     * @return utilisateur
     */
    @Override
    public Utilisateur findById(int id) {

        return userDao.findById(id);
    }


}
