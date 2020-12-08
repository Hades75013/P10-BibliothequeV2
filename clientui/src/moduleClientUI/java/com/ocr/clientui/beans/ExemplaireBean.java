package com.ocr.clientui.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExemplaireBean {

    private int id;

    private Boolean disponible;

    @JsonProperty("ouvrage")
    private OuvrageBean ouvrage;

    @JsonProperty("prets")
    private List<PretBean> prets;


    public ExemplaireBean() {
    }

    public ExemplaireBean(int id, Boolean disponible, OuvrageBean ouvrage, List<PretBean> prets) {
        this.id = id;
        this.disponible = disponible;
        this.ouvrage = ouvrage;
        this.prets = prets;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public OuvrageBean getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(OuvrageBean ouvrage) {
        this.ouvrage = ouvrage;
    }

    public List<PretBean> getPrets() {
        return prets;
    }

    public void setPrets(List<PretBean> prets) {
        this.prets = prets;
    }

    public boolean isDisponible() {
        return true;
    }
}
