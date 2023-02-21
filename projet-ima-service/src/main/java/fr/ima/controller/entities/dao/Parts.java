package fr.ima.controller.entities.dao;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "parts")
public class Parts {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int nombre;

    private String status_paiement;

    private Date date_paiement;



    private String methode_paiement;

    private String description;

    private String couts_supp;


    @ManyToOne
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        nombre = nombre;
    }

    public String getStatus_paiement() {
        return status_paiement;
    }

    public void setStatus_paiement(String status_paiement) {
        this.status_paiement = status_paiement;
    }

    public Date getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(Date date_paiement) {
        this.date_paiement = date_paiement;
    }

    public String getMethode_paiement() {
        return methode_paiement;
    }

    public void setMethode_paiement(String methode_paiement) {
        this.methode_paiement = methode_paiement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouts_supp() {
        return couts_supp;
    }

    public void setCouts_supp(String couts_supp) {
        this.couts_supp = couts_supp;
    }

}
