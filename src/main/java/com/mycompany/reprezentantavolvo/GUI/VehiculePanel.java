/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.reprezentantavolvo.GUI;

import com.mycompany.reprezentantavolvo.Vehicule;
import com.mycompany.reprezentantavolvo.VehiculeDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author robii
 */
public class VehiculePanel extends javax.swing.JPanel {
    MainWindow parent;
    Connection conn;
    private DefaultTableModel defaultTable;

    /**
     * Creates new form Vehicule
     */
    public VehiculePanel(MainWindow parent, Connection conn) {
        this.parent=parent;
        this.conn=conn;
        initComponents();
        
        Home1.setBorder(BorderFactory.createEmptyBorder());
        Home1.setContentAreaFilled(false);
        Home1.setFocusPainted(false);
        
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
        
        defaultTable = new DefaultTableModel(new Object[]{"CODVEH", "Model", "Motorizare", "KM", "an", "CODC"},0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        vehiculeTable.setModel(defaultTable);
        vehiculeTable.setRowSelectionAllowed(false);
        
        loadAllVehicule();
    }
    
    private void loadAllVehicule(){
        try{
            VehiculeDAO dao = new VehiculeDAO(conn);
            List<Vehicule> vehicule = dao.getAllVehicule();
            populateTable(vehicule);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void populateTable(List<Vehicule> vehicule){
        defaultTable.setRowCount(0);
        for(Vehicule v : vehicule){
            defaultTable.addRow(new Object[]{v.getCODVEH(),v.getModel(),v.getMotorizare(),v.getKm(), v.getAn(), v.getCODC()});
        }
    }

private void cautaVehiculePopup(){
    JTextField model = new JTextField();
    JTextField motorizare = new JTextField();
    JTextField codc = new JTextField();
    Object[] fields = {
        "Model: ", model,
        "Motorizare: ", motorizare,
        "Cod client: ", codc
    };

    int result = JOptionPane.showConfirmDialog(
    this, fields, "Cauta", JOptionPane.OK_CANCEL_OPTION);
    
    if(result == JOptionPane.OK_OPTION){
        try {
            String modelText = model.getText().trim();
            String motorizareText = motorizare.getText().trim();

            Integer codcText = null;
            if (!codc.getText().trim().isEmpty()) {
                codcText = Integer.parseInt(codc.getText().trim());
            }

            VehiculeDAO dao = new VehiculeDAO(conn);
            List<Vehicule> vehicule = dao.cautaVehicule(modelText, motorizareText, codcText);
            populateTable(vehicule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
    private void insertVehiculePopup(){
        JTextField modelField = new JTextField();
        JTextField motorizareField = new JTextField();
        JTextField kmField = new JTextField();
        JTextField anField = new JTextField();
        JTextField codcField = new JTextField();
        
        Object[] fields = {
            "Model: ", modelField,
            "Motorizare: ", motorizareField,
            "KM: ", kmField,
            "An: ", anField,
            "Cod client: ", codcField
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Adauga", JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            try{
                String model = modelField.getText().trim();
                String motorizare = motorizareField.getText().trim();
                int km = Integer.parseInt(kmField.getText().trim());
                int an = Integer.parseInt(anField.getText().trim());
                int codc = Integer.parseInt(codcField.getText().trim());
                
                VehiculeDAO dao = new VehiculeDAO(conn);
                dao.insertVehicule(model, motorizare, km, an, codc);
                loadAllVehicule();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void updateVehiculePopup(){
        int selectedRow = vehiculeTable.getSelectedRow();
        
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this,"eroare");
            return;
        }
        int codveh = (int) vehiculeTable.getValueAt(selectedRow, 0);
        String modelVechi = (String) vehiculeTable.getValueAt(selectedRow, 1);
        String motorizareVechi = (String) vehiculeTable.getValueAt(selectedRow, 2);
        int kmVechi = (int) vehiculeTable.getValueAt(selectedRow, 3);
        int anVechi = (int) vehiculeTable.getValueAt(selectedRow, 4);
        int codcVechi = (int) vehiculeTable.getValueAt(selectedRow, 5);
        
        JTextField modelUpdate = new JTextField(modelVechi);
        JTextField motorizareUpdate = new JTextField(String.valueOf(motorizareVechi));
        JTextField kmUpdate = new JTextField(String.valueOf(kmVechi));
        JTextField anUpdate = new JTextField(String.valueOf(anVechi));
        JTextField codcUpdate = new JTextField(String.valueOf(codcVechi));
        
        Object[] fields = {
            "Model nou: ", modelUpdate,
            "Motorizare noua: ", motorizareUpdate,
            "KM noi: ", kmUpdate,
            "An nou: ", anUpdate,
            "Cod client nou: ", codcUpdate
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Update", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION){
            String modelNou = modelUpdate.getText().trim();
            String motorizareNou = motorizareUpdate.getText().trim();
            Integer kmNou = Integer.parseInt(kmUpdate.getText().trim());
            Integer anNou = Integer.parseInt(anUpdate.getText().trim());
            Integer codcNou = Integer.parseInt(codcUpdate.getText().trim());
            
            if(modelNou.isEmpty() || motorizareNou.isEmpty() || kmNou == null || anNou == null || codcNou == null){
                JOptionPane.showMessageDialog(this, "Toate campurile trebuie completate!");
                return;
            }
            try{
                VehiculeDAO dao = new VehiculeDAO(conn);
                dao.updateVehicule(codveh, modelNou, motorizareNou, kmNou, anNou, codcNou);
                loadAllVehicule();
            } catch (Exception e){
                e.printStackTrace();
            }
    }
    }
    
    private void deleteVehiculePopup(){
        JTextField codvehDelete = new JTextField();
            Object[] fields = {
                "COD", codvehDelete
            };
                int result = JOptionPane.showConfirmDialog(
                        this, fields, "Sterge", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION){
                    try{
                        int CODVEH = Integer.parseInt(codvehDelete.getText().trim());
                        
                        VehiculeDAO dao = new VehiculeDAO(conn);
                        dao.deleteVehicule(CODVEH);
                        loadAllVehicule();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home1 = new javax.swing.JButton();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        Cauta = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        vehiculeTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        Home1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/VolvoLogo (1).png"))); // NOI18N
        Home1.setAlignmentY(0.0F);
        Home1.setBorder(null);
        Home1.setMaximumSize(null);
        Home1.setMinimumSize(null);
        Home1.setPreferredSize(null);
        Home1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Home1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Home1MouseExited(evt);
            }
        });
        Home1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Home1ActionPerformed(evt);
            }
        });

        label1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("VEHICULE");

        Cauta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        Insert.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        Update.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        Delete.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        vehiculeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(vehiculeTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(488, 488, 488)
                                .addComponent(Home1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(Cauta)
                                .addGap(18, 18, 18)
                                .addComponent(Insert)
                                .addGap(18, 18, 18)
                                .addComponent(Update)
                                .addGap(18, 18, 18)
                                .addComponent(Delete)))
                        .addGap(0, 462, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Home1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Cauta)
                    .addComponent(Insert)
                    .addComponent(Update)
                    .addComponent(Delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Home1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home1MouseEntered
        // TODO add your handling code here:
        Home1.setOpaque(true);
        Home1.setBackground(new java.awt.Color(211, 211, 211));
    }//GEN-LAST:event_Home1MouseEntered

    private void Home1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home1MouseExited
        // TODO add your handling code here:
        Home1.setOpaque(false);
    }//GEN-LAST:event_Home1MouseExited

    private void Home1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home1ActionPerformed
        // TODO add your handling code here:
        parent.showPanel("menu");
    }//GEN-LAST:event_Home1ActionPerformed

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
        cautaVehiculePopup();
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
        insertVehiculePopup();
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
        updateVehiculePopup();
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
        deleteVehiculePopup();
    }//GEN-LAST:event_DeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cauta;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Home1;
    private javax.swing.JButton Insert;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable vehiculeTable;
    // End of variables declaration//GEN-END:variables
}
