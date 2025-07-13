/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.reprezentantavolvo.GUI;

import com.mycompany.reprezentantavolvo.Service;
import com.mycompany.reprezentantavolvo.ServiceDAO;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;

/**
 *
 * @author robii
 */
public class ServicePanel extends javax.swing.JPanel {
    private MainWindow parent;
    private DefaultTableModel defaultTable;
    private Connection conn;
    /**
     * Creates new form Service
     */
    public ServicePanel(MainWindow parent, Connection conn) {
        this.parent=parent;
        this.conn=conn;
        initComponents();
        
        defaultTable = new DefaultTableModel(new Object[]{"CODS","Diagnostic","Cost","CODVEH","CODMEC"},0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        serviceTable.setModel(defaultTable);
        serviceTable.setRowSelectionAllowed(false);

        loadAllService();
        
        Home1.setBorder(BorderFactory.createEmptyBorder());
        Home1.setContentAreaFilled(false);
        Home1.setFocusPainted(false);
    }

    private void loadAllService(){
        try{
            ServiceDAO dao = new ServiceDAO(conn);
            List<Service> service = dao.getAllService();
            populateTable(service);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void populateTable(List<Service> service){
        defaultTable.setRowCount(0);
        for (Service s : service){
            defaultTable.addRow(new Object[]{s.getCODS(), s.getDiagnostic(), s.getCost(), s.getCODVEH(), s.getCODMEC()});
        }
    }
    
    private void cautaServicePopup(){
        JTextField vehInput = new JTextField();
        JTextField mecInput = new JTextField();
        Object[] fields ={
            "Cod vehicul: ", vehInput,
            "Cod mecanic: ", mecInput
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Cauta", JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
        try{
            int veh = Integer.parseInt(vehInput.getText().trim());
            
            ServiceDAO dao = new ServiceDAO(conn);
            List<Service> service = dao.cautaService(veh);
            populateTable(service);
        } catch(Exception e){
            e.printStackTrace();
        }
        }
    }
    
    private void insertServicePopup(){
        JTextField diagnosticField = new JTextField();
        JTextField costField = new JTextField();
        JTextField codvehField = new JTextField();
        JTextField codmecField = new JTextField();
        
        Object[] fields = {
            "Diagnostic: ", diagnosticField,
            "Cost: ", costField,
            "Cod vehicul: ", codvehField,
            "Cod mecanic: ", codmecField
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Adauga", JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            try{
                String diagnostic = diagnosticField.getText().trim();
                int cost = Integer.parseInt(costField.getText().trim());
                int codveh = Integer.parseInt(codvehField.getText().trim());
                int codmec = Integer.parseInt(codmecField.getText().trim());
                
                ServiceDAO dao = new ServiceDAO(conn);
                dao.insertService(diagnostic, cost, codveh, codmec);
                loadAllService();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void updateServicePopup(){
        int selectedRow = serviceTable.getSelectedRow();
        
        int cods = (int) serviceTable.getValueAt(selectedRow, 0);
        String diagnosticVechi = (String) serviceTable.getValueAt(selectedRow, 1);
        int costVechi = (int) serviceTable.getValueAt(selectedRow, 2);
        int codvVechi = (int) serviceTable.getValueAt(selectedRow, 3);
        int codmVechi = (int) serviceTable.getValueAt(selectedRow, 4);
        
        JTextField diagnosticUpdate = new JTextField();
        JTextField costUpdate = new JTextField();
        JTextField codvUpdate = new JTextField();
        JTextField codmUpdate = new JTextField();
        
        Object[] fields = {
            "Diagnostic nou: ", diagnosticUpdate,
            "Cost nou: ", costUpdate,
            "Cod vehicul nou: ", codvUpdate,
            "Cod mecanic nou: ", codmUpdate
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Update", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION){
            String diagnosticNou = diagnosticUpdate.getText().trim();
            Integer costNou = Integer.parseInt(costUpdate.getText().trim());
            Integer codvNou = Integer.parseInt(codvUpdate.getText().trim());
            Integer codmNou = Integer.parseInt(codmUpdate.getText().trim());
            
            if(diagnosticNou.isEmpty() || costNou == null || codvNou == null || codmNou == null){
                JOptionPane.showMessageDialog(this, "Toate campurile trebuie completate!");
                return;
            }
            try{
                ServiceDAO dao = new ServiceDAO(conn);
                dao.updateService(cods, diagnosticNou, costNou, codvNou, codmNou);
                loadAllService();
            } catch (Exception e){
                e.printStackTrace();
            }
    }
    }
    private void deleteServicePopup(){
            JTextField codsDelete = new JTextField();
            Object[] fields = {
                "CODS", codsDelete
            };
                int result = JOptionPane.showConfirmDialog(
                        this, fields, "Sterge", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION){
                    try{
                        int CODS = Integer.parseInt(codsDelete.getText().trim());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        serviceTable = new javax.swing.JTable();
        Cauta = new java.awt.Button();
        Insert = new java.awt.Button();
        Update = new java.awt.Button();
        Sterge = new java.awt.Button();

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

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
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
        serviceTable.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(serviceTable);

        Cauta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cauta.setLabel("CAUTA");
        Cauta.setMinimumSize(new java.awt.Dimension(75, 25));
        Cauta.setPreferredSize(new java.awt.Dimension(75, 25));
        Cauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CautaActionPerformed(evt);
            }
        });

        Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Insert.setLabel("INSERT");
        Insert.setPreferredSize(new java.awt.Dimension(75, 25));
        Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertActionPerformed(evt);
            }
        });

        Update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Update.setLabel("UPDATE");
        Update.setName(""); // NOI18N
        Update.setPreferredSize(new java.awt.Dimension(75, 25));
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Sterge.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sterge.setLabel("STERGE");
        Sterge.setPreferredSize(new java.awt.Dimension(75, 25));
        Sterge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StergeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(488, 488, 488)
                        .addComponent(Home1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Cauta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sterge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(482, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Home1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cauta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sterge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
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

    private void CautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CautaActionPerformed
        // TODO add your handling code here:
        cautaServicePopup();
    }//GEN-LAST:event_CautaActionPerformed

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        // TODO add your handling code here:
        insertServicePopup();
    }//GEN-LAST:event_InsertActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        updateServicePopup();
    }//GEN-LAST:event_UpdateActionPerformed

    private void StergeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StergeActionPerformed
        // TODO add your handling code here:
        deleteServicePopup();
    }//GEN-LAST:event_StergeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Cauta;
    private javax.swing.JButton Home1;
    private java.awt.Button Insert;
    private java.awt.Button Sterge;
    private java.awt.Button Update;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable serviceTable;
    // End of variables declaration//GEN-END:variables


}
