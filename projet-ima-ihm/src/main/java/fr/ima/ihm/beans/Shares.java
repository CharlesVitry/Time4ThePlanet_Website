package fr.ima.ihm.beans;


import java.util.Date;

public class Shares {

    private int id;



    private int number;



    private String paiement_situation;

    private Date paiement_date;

    private String paiement_method;

    private String desc;

    private String Additional_fee;



    private Adherents adherents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Adherents getAdherents() {
        return adherents;
    }

    public void setAdherents(Adherents adherents) {
        this.adherents = adherents;
    }

    public String getPaiement_situation() {
        return paiement_situation;
    }

    public void setPaiement_situation(String paiement_situation) {
        this.paiement_situation = paiement_situation;
    }

    public Date getPaiement_date() {
        return paiement_date;
    }

    public void setPaiement_date(Date paiement_date) {
        this.paiement_date = paiement_date;
    }

    public String getPaiement_method() {
        return paiement_method;
    }

    public void setPaiement_method(String paiement_method) {
        this.paiement_method = paiement_method;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAdditional_fee() {
        return Additional_fee;
    }

    public void setAdditional_fee(String additional_fee) {
        Additional_fee = additional_fee;
    }
}
