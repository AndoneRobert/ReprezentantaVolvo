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
    private Date data_vanzarii;
    private String localitate;
    
    public Vanzari(){
        CODV=0;
        data_vanzarii=Date.valueOf("2025-01-01");
        localitate="NULL";
    }
    
    public Vanzari(int CODV, Date data_vanzarii, String localitate){
        this.CODV=CODV;
        this.data_vanzarii=data_vanzarii;
        this.localitate=localitate;
    }
    
    public Vanzari(Vanzari vanzari){
        this.CODV=vanzari.CODV;
        this.data_vanzarii=vanzari.data_vanzarii;
        this.localitate=vanzari.localitate;
    }
    
    public int getCODV(){
        return CODV;
    }
    public void setCODV(int CODV){
        this.CODV=CODV;
    }
    
    public Date getDataVanzarii(){
        return data_vanzarii;
    }
    public void setDataVanzarii(Date data_vanzarii){
        this.data_vanzarii=data_vanzarii;
    }
    
    public String getLocalitate(){
        return localitate;
    }
    public void setLocalitate(String localitate){
        this.localitate=localitate;
    }
}
