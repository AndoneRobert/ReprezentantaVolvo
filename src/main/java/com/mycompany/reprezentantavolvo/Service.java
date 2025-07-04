/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

/**
 *
 * @author robii
 */
public class Service {
    private int CODS;
    private String diagnostic;
    private int cost;
    private int CODVEH;
    private int CODMEC;
    
    public Service() {
        CODS = 0;
        diagnostic = "NULL";
        cost = 0;
        CODVEH = 0;
        CODMEC = 0;
    }

    public Service(int CODS, String diagnostic, int cost, int CODVEH, int CODMEC) {
        this.CODS = CODS;
        this.diagnostic = diagnostic;
        this.cost = cost;
        this.CODVEH = CODVEH;
        this.CODMEC = CODMEC;
    }

    public Service(Service service) {
        this.CODS = service.CODS;
        this.diagnostic = service.diagnostic;
        this.cost = service.cost;
        this.CODVEH = service.CODVEH;
        this.CODMEC = service.CODMEC;
    }

    public int getCODS() {
        return CODS;
    }

    public void setCODS(int CODS) {
        this.CODS = CODS;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCODVEH() {
        return CODVEH;
    }

    public void setCODVEH(int CODVEH) {
        this.CODVEH = CODVEH;
    }

    public int getCODMEC() {
        return CODMEC;
    }

    public void setCODMEC(int CODMEC) {
        this.CODMEC = CODMEC;
    }
}
