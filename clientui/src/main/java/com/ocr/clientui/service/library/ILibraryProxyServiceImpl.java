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


    //METHODES OUVRAGE
    /**
     *
     * @return
     */
    @Override
    public List<OuvrageBean> listeOuvrages() {
        return libraryProxy.listeOuvrages();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public OuvrageBean afficherUnOuvrage(int id) {
        return libraryProxy.afficherUnOuvrage(id);
    }

    /**
     *
     * @param titre
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByTitre(String titre) {
        return libraryProxy.rechercheByTitre(titre);
    }

    /**
     *
     * @param auteur
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByAuteur(String auteur) {
        return libraryProxy.rechercheByAuteur(auteur);
    }

    /**
     *
     * @param genre
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByGenre(String genre) {
        return libraryProxy.rechercheByGenre(genre);
    }

    /**
     *
     * @param statut
     * @return
     */
    @Override
    public List<OuvrageBean> rechercheByDispo(Boolean statut) {
        return libraryProxy.rechercheByDispo(statut);
    }

    //METHODES PRET
    /**
     *
     * @param id
     * @return
     */
    @Override
    public PretBean afficherUnPret(int id) {
        return libraryProxy.afficherUnPret(id);
    }

    /**
     *
     * @return
     */
    @Override
    public List<PretBean> listePrets() {
        return libraryProxy.listePrets();
    }

    /**
     *
     * @param idUtilisateur
     * @return
     */
    @Override
    public List<PretBean> listePretsByUtilisateur(int idUtilisateur) {
        return libraryProxy.listePretsByUtilisateur(idUtilisateur);
    }

    /**
     *
     * @param idUtilisateur
     * @return
     */
    @Override
    public List<PretBean> listeResasByUtilisateur(int idUtilisateur) {
        return libraryProxy.listeResasByUtilisateur(idUtilisateur);
    }

    /**
     *
     * @param idOuvrage
     * @param idUtilisateur
     * @return
     */
    @Override
    public PretBean demanderPret(int idOuvrage, int idUtilisateur) {
        return libraryProxy.demanderPret(idOuvrage, idUtilisateur);
    }

    /**
     *
     * @param idPret
     * @return
     */
    @Override
    public PretBean validerPret(int idPret) {
        return libraryProxy.validerPret(idPret);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PretBean retourPret(int id) {
        return libraryProxy.retourPret(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PretBean prolongationPret(int id) {
        return libraryProxy.prolongationPret(id);
    }


}
