/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

/**
 *
 * @author robii
 */
public class Mecanici {
    private int CODMEC;
    private String nume;
    private String specializare;
    
    public Mecanici(){
        CODMEC=0;
        nume = "NULL";
        specializare="NULL";
    }
    
    public Mecanici(int CODMEC, String nume, String specializare){
        this.CODMEC=CODMEC;
        this.nume=nume;
        this.specializare=specializare;
    }
    
    public Mecanici(Mecanici mecanici){
        this.CODMEC=mecanici.CODMEC;
        this.nume=mecanici.nume;
        this.specializare=mecanici.specializare;
    }
    
    public int getCODMEC(){
        return CODMEC;
    }
    public void setCODMEC(int CODMEC){
        this.CODMEC=CODMEC;
    }
    
    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    
    public String getSpecializare(){
        return specializare;
    }
    public void setSpecializare(String specializare){
        this.specializare=specializare;
    }
    
    public String toString(){
        return "Mecanicul cu ID-ul "+CODMEC+", "+nume+"este la "+specializare+".";
    }
}
