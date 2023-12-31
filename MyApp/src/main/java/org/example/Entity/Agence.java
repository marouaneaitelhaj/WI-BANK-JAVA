package org.example.Entity;

import java.util.List;

public class Agence {
    private String code;
    private String nom;
    private String adresse;
    private String numeroTelephone;
    private List<AgenceOfEmploye> employeAgenceLogs;

    public Agence(String code, String nom, String adresse, String numeroTelephone) {
        this.code = code;
        this.nom = nom;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
    }

    public Agence(String code) {
        this.code = code;
    }



    public List<AgenceOfEmploye> getEmployeAgenceLogs() {
        return employeAgenceLogs;
    }

    public void setEmployeAgenceLogs(List<AgenceOfEmploye> employeAgenceLogs) {
        this.employeAgenceLogs = employeAgenceLogs;
    }

    public Agence() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
}
