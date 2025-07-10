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
    private int codm;
    private String model;
    private int an;
    private String motorizare;
    private String culoare;
    private int km;
    private String starea;
    private int pret;
    private String tip;
    
    
    public Stoc(){
        codm = 0;
        model = "NULL";
        an = 0;
        motorizare = "NULL";
        culoare = "NULL";
        km = 0;
        starea = "NULL";
        pret = 0;
        tip = "NULL";
    }
    public Stoc(int codm, String model, int an, String motorizare, String culoare, int km, String starea, int pret, String tip){
        this.codm = codm;
        this.starea=starea;
        this.an=an;
        this.model=model;
        this.motorizare=motorizare;
        this.pret=pret;
        this.km=km;
        this.culoare=culoare;
        this.tip=tip;
    }
    
    public Stoc(Stoc stoc){
        this.codm=stoc.codm;
        this.starea=stoc.starea;
        this.an=stoc.an;
        this.model=stoc.model;
        this.motorizare=stoc.motorizare;
        this.pret=stoc.pret;
        this.km=stoc.km;
        this.culoare=stoc.culoare;
        this.tip=stoc.tip;
    }
    
    public int getCODM(){
        return codm;
    }
    public void setCODM(int CODM){
        this.codm=codm;
    }
    
    public String getStare(){
        return starea;
    }
    public void setStare(String stare){
        this.starea=starea;
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
        return "Vehiculul cu codul "+codm+" este in stare "+starea+" din anul "+an+". Modelul este "+model+" "+motorizare+" la pretul de "+pret+" euro, are "+km+" culoarea este "+culoare+".";
    }
}
