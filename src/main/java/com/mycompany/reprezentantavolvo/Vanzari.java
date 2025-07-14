/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;
import java.sql.Date;
/**
 *
 * @author robii
 */
public class Vanzari {
    private int CODV;
    private Date datav;
    private int CODM;
    private int CODC;
    
    public Vanzari(){
        CODV=0;
        datav=Date.valueOf("2025-01-01");
        CODM=0;
        CODC=0;
    }
    
    public Vanzari(int CODV, Date datav, int CODM, int CODC){
        this.CODV=CODV;
        this.datav=datav;
        this.CODM=CODM;
        this.CODC=CODC;
    }
    
    public Vanzari(Vanzari vanzari){
        this.CODV=vanzari.CODV;
        this.datav=vanzari.datav;
        this.CODM=vanzari.CODM;
        this.CODC=vanzari.CODC;
    }
    
    public int getCODV(){
        return CODV;
    }
    public void setCODV(int CODV){
        this.CODV=CODV;
    }
    
    public Date getDatav(){
        return datav;
    }
    public void setDatav(Date datav){
        this.datav=datav;
    }
    
    public int getCODM(){
        return CODM;
    }
    public void setCODM(int CODM){
        this.CODM=CODM;
    }
    
    public int getCODC(){
        return CODC;
    }
    public void setCODC(int CODC){
        this.CODC=CODC;
    }
}
