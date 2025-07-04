/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

/**
 *
 * @author robii
 */
public class Vehicule {
    private int CODVEH;
    private String model;
    private String motorizare;
    private int km;
    private int an;
    private int CODC;
    
        public Vehicule() {
        CODVEH = 0;
        model = "NULL";
        motorizare = "NULL";
        km = 0;
        an = 2000;
        CODC = 0;
    }

        public Vehicule(int CODVEH, String model, String motorizare, int km, int an, int CODC) {
        this.CODVEH = CODVEH;
        this.model = model;
        this.motorizare = motorizare;
        this.km = km;
        this.an = an;
        this.CODC = CODC;
    }

        public Vehicule(Vehicule vehicul) {
        this.CODVEH = vehicul.CODVEH;
        this.model = vehicul.model;
        this.motorizare = vehicul.motorizare;
        this.km = vehicul.km;
        this.an = vehicul.an;
        this.CODC = vehicul.CODC;
    }
        
    public int getCODVEH() {
        return CODVEH;
    }

    public void setCODVEH(int CODVEH) {
        this.CODVEH = CODVEH;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMotorizare() {
        return motorizare;
    }

    public void setMotorizare(String motorizare) {
        this.motorizare = motorizare;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public int getCODC() {
        return CODC;
    }

    public void setCODC(int CODC) {
        this.CODC = CODC;
    }
}
