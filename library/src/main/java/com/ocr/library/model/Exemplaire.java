package com.ocr.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class Exemplaire {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="disponible")
    private Boolean disponible;

    @JsonBackReference
    @ManyToOne
    private Ouvrage ouvrage;

    @JsonBackReference
    @OneToMany(mappedBy = "exemplaire", fetch = FetchType.EAGER)
    private List<Pret> prets;


    public Exemplaire() {
    }

    public Exemplaire(int id, Boolean disponible, Ouvrage ouvrage, List<Pret> prets) {
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

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public List<Pret> getPrets() {
        return prets;
    }

    public void setPrets(List<Pret> prets) {
        this.prets = prets;
    }

    public boolean isDisponible() {
        return true;
    }


}
