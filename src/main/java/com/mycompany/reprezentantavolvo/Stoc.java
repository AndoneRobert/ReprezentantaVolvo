/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

/**
 *
 * @author robii
 */
public class Stoc {
    private int CODM;
    private String stare;
    private int an;
    private String model;
    private String motorizare;
    private int pret;
    private int km;
    private String culoare;
    private String tip;
    
    
    public Stoc(){
        CODM = 0;
        stare = "noua";
        model = "NULL";
        motorizare = "NULL";
        pret = 0;
        km = 0;
        culoare = "NULL";
        tip = "NULL";
    }
    public Stoc(int CODM, String stare, int an, String model, String motorizare, int pret, int km, String culoare, String tip){
        this.CODM=CODM;
        this.stare=stare;
        this.an=an;
        this.model=model;
        this.motorizare=motorizare;
        this.pret=pret;
        this.km=km;
        this.culoare=culoare;
        this.tip=tip;
    }
    
    public Stoc(Stoc stoc){
        this.CODM=stoc.CODM;
        this.stare=stoc.stare;
        this.an=stoc.an;
        this.model=stoc.model;
        this.motorizare=stoc.motorizare;
        this.pret=stoc.pret;
        this.km=stoc.km;
        this.culoare=stoc.culoare;
        this.tip=stoc.tip;
    }
    
    public int getCODM(){
        return CODM;
    }
    public void setCODM(int CODM){
        this.CODM=CODM;
    }
    
    public String getStare(){
        return stare;
    }
    public void setStare(String stare){
        this.stare=stare;
    }
    
    public int getAn(){
        return an;
    }
    public void setAn(int an){
        this.an=an;
    }
    
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model=model;
    }
    
    public String getMotorizare(){
        return motorizare;
    }
    public void setMotorizare(String motorizare){
        this.motorizare=motorizare;
    }
    
    public int getPret(){
        return pret;
    }
    public void setPret(int pret){
        this.pret=pret;
    }
    
    public int getKm(){
        return km;
    }
    public void setKm(int km){
        this.km=km;
    }
    
    public String getCuloare(){
        return culoare;
    }
    public void setCuloare(String culoare){
        this.culoare=culoare;
    }
    
    public String getTip(){
        return tip;
    }
    public void setTip(String tip){
        this.tip=tip;
    }
            
    public String toString(){
        return "Vehiculul cu codul "+CODM+" este in stare "+stare+" din anul "+an+". Modelul este "+model+" "+motorizare+" la pretul de "+pret+" euro, are "+km+" culoarea este "+culoare+".";
    }
}
