package com.ocr.moduleuser.controller;

import com.ocr.moduleuser.model.Utilisateur;
import com.ocr.moduleuser.service.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @PostMapping(value="/saveUser")
    public Utilisateur sauverUtilisateur(@RequestBody Utilisateur utilisateur) {

        utilisateurService.save(utilisateur);

        return utilisateur;

    }

    @GetMapping(value = "/login/{email}")
    public Utilisateur connectionUtilisateur(@PathVariable String email) {

        Utilisateur utilisateur = utilisateurService.findByEmail(email);

        return utilisateur;
    }


   @GetMapping(value = "/user/{id}")
    public Utilisateur recupererUtilisateur(@PathVariable ("id") int id) {

        Utilisateur utilisateur = utilisateurService.findById(id);

        return utilisateur;
    }


}
