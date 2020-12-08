package com.ocr.clientui.service.library;

import com.ocr.clientui.beans.OuvrageBean;
import com.ocr.clientui.beans.PretBean;
import com.ocr.clientui.proxies.LibraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class ILibraryProxyServiceImpl implements ILibraryProxyService{

    @Autowired
    private LibraryProxy libraryProxy;


    @Override
    public List<OuvrageBean> listeOuvrages() {
        return libraryProxy.listeOuvrages();
    }

    @Override
    public OuvrageBean afficherUnOuvrage(int id) {
        return libraryProxy.afficherUnOuvrage(id);
    }

    @Override
    public List<OuvrageBean> rechercheByTitre(String titre) {
        return libraryProxy.rechercheByTitre(titre);
    }

    @Override
    public List<OuvrageBean> rechercheByAuteur(String auteur) {
        return libraryProxy.rechercheByAuteur(auteur);
    }

    @Override
    public List<OuvrageBean> rechercheByGenre(String genre) {
        return libraryProxy.rechercheByGenre(genre);
    }

    @Override
    public List<OuvrageBean> rechercheByDispo(Boolean statut) {
        return libraryProxy.rechercheByDispo(statut);
    }




    @Override
    public PretBean afficherUnPret(int id) {
        return libraryProxy.afficherUnPret(id);
    }

    @Override
    public List<PretBean> listePrets() {
        return libraryProxy.listePrets();
    }

    @Override
    public List<PretBean> listePretsByUtilisateur(int idUtilisateur) {
        return libraryProxy.listePretsByUtilisateur(idUtilisateur);
    }

    @Override
    public List<PretBean> listeResasByUtilisateur(int idUtilisateur) {
        return libraryProxy.listeResasByUtilisateur(idUtilisateur);
    }

    @Override
    public PretBean demanderPret(int idOuvrage, int idUtilisateur) {
        return libraryProxy.demanderPret(idOuvrage, idUtilisateur);
    }

    @Override
    public PretBean validerPret(int idPret) {
        return libraryProxy.validerPret(idPret);
    }

    @Override
    public PretBean retourPret(int id) {
        return libraryProxy.retourPret(id);
    }

    @Override
    public PretBean prolongationPret(int id) {
        return libraryProxy.prolongationPret(id);
    }


}
