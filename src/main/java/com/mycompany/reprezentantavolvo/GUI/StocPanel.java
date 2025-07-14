/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.reprezentantavolvo.GUI;

import com.mycompany.reprezentantavolvo.Stoc;
import com.mycompany.reprezentantavolvo.StocDAO;
import com.mycompany.reprezentantavolvo.VanzariDAO;
import com.mycompany.reprezentantavolvo.Vanzari;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


/**
 *
 * @author robii
 */
public class StocPanel extends javax.swing.JPanel {
    private MainWindow parent;
    private Connection conn;
    private DefaultTableModel defaultTable;
    private MainMenu menu;
    /**
     * Creates new form Stoc
     */
    public StocPanel(MainWindow parent, Connection conn) {
        this.parent=parent;
        this.conn=conn;
        initComponents();
        
        Home.setBorder(BorderFactory.createEmptyBorder());
        Home.setContentAreaFilled(false);
        Home.setFocusPainted(false);
        
        Cauta.setBorder(BorderFactory.createEmptyBorder());
        Cauta.setContentAreaFilled(false);
        Cauta.setFocusPainted(false);
        
        Insert.setBorder(BorderFactory.createEmptyBorder());
        Insert.setContentAreaFilled(false);
        Insert.setFocusPainted(false);
        
        Update.setBorder(BorderFactory.createEmptyBorder());
        Update.setContentAreaFilled(false);
        Update.setFocusPainted(false);
        
        Delete.setBorder(BorderFactory.createEmptyBorder());
        Delete.setContentAreaFilled(false);
        Delete.setFocusPainted(false);
        
        Vanzare.setBorder(BorderFactory.createEmptyBorder());
        Vanzare.setContentAreaFilled(false);
        Vanzare.setFocusPainted(false);
        
        defaultTable = new DefaultTableModel(new Object[]{"CODM", "Model", "An", "Motorizare","Culoare","KM","Starea","Pret","Tip"},0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        stocTable.setModel(defaultTable);
        stocTable.setRowSelectionAllowed(false);
        
        loadAllStoc();
    }

    
    private void loadAllStoc(){
        try{
            StocDAO dao = new StocDAO(conn);
            List<Stoc> stoc = dao.getAllStoc();
            populateTable(stoc);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void populateTable(List<Stoc> stoc){
        defaultTable.setRowCount(0);
        for(Stoc s : stoc){
            defaultTable.addRow(new Object[]{s.getCODM(),s.getModel(),s.getAn(),s.getMotorizare(),s.getCuloare(),s.getKm(),s.getStare(),s.getPret(),s.getTip()});
        }
    }
    
    private void cautaStocPopup(){
        JTextField model = new JTextField();
        JTextField motorizare = new JTextField();
        Object[] fields = {
            "Model: ", model,
            "Motorizare: ", motorizare
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Cauta", JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
        try {
            String modelText = model.getText().trim();
            String motorizareText = motorizare.getText().trim();


            StocDAO dao = new StocDAO(conn);
            List<Stoc> stoc = dao.cautaStoc(modelText, motorizareText);
            populateTable(stoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    
    private void insertStocPopup(){
        JTextField modelField = new JTextField();
        JTextField anField = new JTextField();
        JTextField motorizareField = new JTextField();
        JTextField culoareField = new JTextField();
        JTextField kmField = new JTextField();
        JTextField stareField = new JTextField();
        JTextField pretField = new JTextField();
        JTextField tipField = new JTextField();
        
        Object[] fields = {
            "Model: ", modelField,
            "Anul: ", anField,
            "Motorizare: ", motorizareField,
            "Culoare: ", culoareField,
            "KM: ", kmField,
            "Starea: ",stareField,
            "Pret: ",pretField,
            "Tip: ",tipField
        };
        
        int result = JOptionPane.showConfirmDialog(
        this,fields,"Adauga",JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            try{
                String model = modelField.getText().trim();
                int an = Integer.parseInt(anField.getText().trim());
                String motorizare = motorizareField.getText().trim();
                String culoare = culoareField.getText().trim();
                int km = Integer.parseInt(kmField.getText().trim());
                String stare = stareField.getText().trim();
                int pret = Integer.parseInt(pretField.getText().trim());
                String tip = tipField.getText().trim();
                
                StocDAO dao = new StocDAO(conn);
                dao.insertStoc(model, an, motorizare,culoare,km,stare,pret,tip);
                loadAllStoc();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void updateStocPopup(){
        int selectedRow = stocTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this,"eroare");
            return;
        }
        int codm = (int) stocTable.getValueAt(selectedRow, 0);
        String modelVechi = (String) stocTable.getValueAt(selectedRow, 1);
        int anVechi = (int) stocTable.getValueAt(selectedRow, 2);
        String motorizareVechi = (String) stocTable.getValueAt(selectedRow, 3);
        String culoareVechi = (String) stocTable.getValueAt(selectedRow, 4);
        int kmVechi = (int) stocTable.getValueAt(selectedRow, 5);
        String stareVechi = (String) stocTable.getValueAt(selectedRow, 6);
        int pretVechi = (int) stocTable.getValueAt(selectedRow, 7);
        String tipVechi = (String) stocTable.getValueAt(selectedRow, 8);
        
        JTextField modelUpdate = new JTextField(modelVechi);
        JTextField anUpdate = new JTextField(String.valueOf(anVechi));
        JTextField motorizareUpdate = new JTextField(motorizareVechi);
        JTextField culoareUpdate = new JTextField(culoareVechi);
        JTextField kmUpdate = new JTextField(String.valueOf(kmVechi));
        JTextField stareUpdate = new JTextField(stareVechi);
        JTextField pretUpdate = new JTextField(String.valueOf(pretVechi));
        JTextField tipUpdate = new JTextField(tipVechi);
        
        Object[] fields = {
            "Model nou: ", modelUpdate,
            "An nou: ", anUpdate,
            "Motorizare noua: ", motorizareUpdate,
            "Culoare noua: ", culoareUpdate,
            "KM noi: ", kmUpdate,
            "Stare noua: ", stareUpdate,
            "Pret nou: ", pretUpdate,
            "Tip nou: ", tipUpdate
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Update", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION){
            String modelNou = modelUpdate.getText().trim();
            int anNou= Integer.parseInt(anUpdate.getText().trim());
            String motorizareNou = motorizareUpdate.getText().trim();
            String culoareNou = culoareUpdate.getText().trim();
            int kmNou = Integer.parseInt(kmUpdate.getText().trim());
            String stareNou = stareUpdate.getText().trim();
            int pretNou = Integer.parseInt(pretUpdate.getText().trim());
            String tipNou = tipUpdate.getText().trim();
            
            if(modelNou.isEmpty() || motorizareNou.isEmpty() || culoareNou.isEmpty() || stareNou.isEmpty() || tipNou.isEmpty()){
                JOptionPane.showMessageDialog(this, "Toate campurile trebuie completate!");
                return;
            }
            try{
                StocDAO dao = new StocDAO(conn);
                dao.updateStoc(codm, modelNou, anNou, motorizareNou, culoareNou, kmNou, stareNou, pretNou, tipNou);
                loadAllStoc();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void deleteStocPopup(){
            JTextField codmDelete = new JTextField();
            Object[] fields = {
                "COD", codmDelete
            };
                int result = JOptionPane.showConfirmDialog(
                        this, fields, "Sterge", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION){
                    try{
                        int CODM = Integer.parseInt(codmDelete.getText().trim());
                        
                        StocDAO dao = new StocDAO(conn);
                        dao.deleteStoc(CODM);
                        loadAllStoc();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
    }
    
    private void insertVanzarePopup(){
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home = new javax.swing.JButton();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        Cauta = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        stocTable = new javax.swing.JTable();
        Vanzare = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/VolvoLogo (1).png"))); // NOI18N
        Home.setAlignmentY(0.0F);
        Home.setBorder(null);
        Home.setMaximumSize(null);
        Home.setMinimumSize(null);
        Home.setPreferredSize(null);
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HomeMouseExited(evt);
            }
        });
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        label1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("STOC");

        Cauta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cauta.setText("CAUTA");
        Cauta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CautaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CautaMouseExited(evt);
            }
        });
        Cauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CautaActionPerformed(evt);
            }
        });

        Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Insert.setText("INSERT");
        Insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InsertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InsertMouseExited(evt);
            }
        });
        Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertActionPerformed(evt);
            }
        });

        Update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Update.setText("UPDATE");
        Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UpdateMouseExited(evt);
            }
        });
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Delete.setText("DELETE");
        Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DeleteMouseExited(evt);
            }
        });
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        stocTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(stocTable);

        Vanzare.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Vanzare.setText("VANZARE");
        Vanzare.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VanzareMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                VanzareMouseExited(evt);
            }
        });
        Vanzare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VanzareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(482, 482, 482))
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Cauta)
                .addGap(18, 18, 18)
                .addComponent(Insert)
                .addGap(18, 18, 18)
                .addComponent(Update)
                .addGap(18, 18, 18)
                .addComponent(Delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Vanzare)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Cauta)
                    .addComponent(Insert)
                    .addComponent(Update)
                    .addComponent(Delete)
                    .addComponent(Vanzare))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        parent.showPanel("menu");
    }//GEN-LAST:event_HomeActionPerformed

    private void HomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseEntered
        // TODO add your handling code here:
        Home.setOpaque(true);
        Home.setBackground(new java.awt.Color(211, 211, 211));
    }//GEN-LAST:event_HomeMouseEntered

    private void HomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseExited
        // TODO add your handling code here:
        Home.setOpaque(false);
    }//GEN-LAST:event_HomeMouseExited

    private void CautaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CautaMouseEntered
        // TODO add your handling code here:
        Cauta.setOpaque(true);
        Cauta.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_CautaMouseEntered

    private void CautaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CautaMouseExited
        // TODO add your handling code here:
        Cauta.setOpaque(false);
    }//GEN-LAST:event_CautaMouseExited

    private void CautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CautaActionPerformed
        // TODO add your handling code here:
        cautaStocPopup();
    }//GEN-LAST:event_CautaActionPerformed

    private void InsertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InsertMouseEntered
        // TODO add your handling code here:
        Insert.setOpaque(true);
        Insert.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_InsertMouseEntered

    private void InsertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InsertMouseExited
        // TODO add your handling code here:
        Insert.setOpaque(false);
    }//GEN-LAST:event_InsertMouseExited

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        // TODO add your handling code here:
        insertStocPopup();
    }//GEN-LAST:event_InsertActionPerformed

    private void UpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateMouseEntered
        // TODO add your handling code here:
        Update.setOpaque(true);
        Update.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_UpdateMouseEntered

    private void UpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateMouseExited
        // TODO add your handling code here:
        Update.setOpaque(false);
    }//GEN-LAST:event_UpdateMouseExited

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        updateStocPopup();
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseEntered
        // TODO add your handling code here:
        Delete.setOpaque(true);
        Delete.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_DeleteMouseEntered

    private void DeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseExited
        // TODO add your handling code here:
        Delete.setOpaque(false);
    }//GEN-LAST:event_DeleteMouseExited

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        deleteStocPopup();
    }//GEN-LAST:event_DeleteActionPerformed

    private void VanzareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VanzareActionPerformed
        // TODO add your handling code here:
        insertVanzarePopup();
    }//GEN-LAST:event_VanzareActionPerformed

    private void VanzareMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VanzareMouseEntered
        // TODO add your handling code here:
        Vanzare.setOpaque(true);
        Vanzare.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_VanzareMouseEntered

    private void VanzareMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VanzareMouseExited
        // TODO add your handling code here:
        Vanzare.setOpaque(false);
    }//GEN-LAST:event_VanzareMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cauta;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Home;
    private javax.swing.JButton Insert;
    private javax.swing.JButton Update;
    private javax.swing.JButton Vanzare;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable stocTable;
    // End of variables declaration//GEN-END:variables
}
