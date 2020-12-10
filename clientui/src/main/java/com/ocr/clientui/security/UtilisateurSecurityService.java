package com.ocr.clientui.security;

import com.ocr.clientui.beans.UtilisateurBean;
import com.ocr.clientui.service.utilisateur.IUtilisateurProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurSecurityService implements UserDetailsService {

    @Autowired
    private IUtilisateurProxyService utilisateurProxyService;

    /**
     *
     * @param email
     * @return utilisateur
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurBean utilisateur = utilisateurProxyService.connectionUtilisateur(email.toLowerCase());
        if (utilisateur==null){
            throw new UsernameNotFoundException("l'utilisateur possèdant l'email "+email+" est introuvable");
        }else {
            return utilisateur;
        }
    }
}
