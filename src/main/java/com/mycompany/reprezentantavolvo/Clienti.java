/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

/**
 *
 * @author robii
 */
public class Clienti {
    private int CODC;
    private String nume;
    private String localitate;
    
    public Clienti() {
        CODC = 0;
        nume = "NULL";
        localitate = "NULL";
    }

    public Clienti(int CODC, String nume, String localitate) {
        this.CODC = CODC;
        this.nume = nume;
        this.localitate = localitate;
    }

    public Clienti(Clienti client) {
        this.CODC = client.CODC;
        this.nume = client.nume;
        this.localitate = client.localitate;
    }

    public int getCODC() {
        return CODC;
    }

    public void setCODC(int CODC) {
        this.CODC = CODC;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }
}
