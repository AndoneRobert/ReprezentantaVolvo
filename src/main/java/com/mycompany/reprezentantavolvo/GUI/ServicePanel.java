/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.reprezentantavolvo.GUI;

import com.mycompany.reprezentantavolvo.Mecanici;
import com.mycompany.reprezentantavolvo.MecaniciDAO;
import com.mycompany.reprezentantavolvo.Service;
import com.mycompany.reprezentantavolvo.ServiceDAO;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;
import java.awt.*;

/**
 *
 * @author robii
 */
public class ServicePanel extends javax.swing.JPanel {
    private MainWindow parent;
    private DefaultTableModel defaultTable;
    private DefaultTableModel defaultTableMec;
    private Connection conn;
    /**
     * Creates new form Service
     */
    public ServicePanel(MainWindow parent, Connection conn) {
        this.parent=parent;
        this.conn=conn;
        initComponents();
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
        
        mCauta.setBorder(BorderFactory.createEmptyBorder());
        mCauta.setContentAreaFilled(false);
        mCauta.setFocusPainted(false);
        
        mInsert.setBorder(BorderFactory.createEmptyBorder());
        mInsert.setContentAreaFilled(false);
        mInsert.setFocusPainted(false);
        
        mUpdate.setBorder(BorderFactory.createEmptyBorder());
        mUpdate.setContentAreaFilled(false);
        mUpdate.setFocusPainted(false);
        
        mDelete.setBorder(BorderFactory.createEmptyBorder());
        mDelete.setContentAreaFilled(false);
        mDelete.setFocusPainted(false);
        
        
        
        defaultTable = new DefaultTableModel(new Object[]{"CODS","Diagnostic","Cost","CODVEH","CODMEC"},0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        serviceTable.setModel(defaultTable);
        serviceTable.setRowSelectionAllowed(false);

        loadAllService();
        
        defaultTableMec = new DefaultTableModel(new Object[]{"CODMEC", "Nume", "Specializare"},0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        mecaniciTable.setModel(defaultTableMec);
        mecaniciTable.setRowSelectionAllowed(false);
        
        loadAllMecanici();
        
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
    
    private void loadAllMecanici(){
        try{
            MecaniciDAO dao = new MecaniciDAO(conn);
            List<Mecanici> mecanici = dao.getAllMecanici();
            populateTableMec(mecanici);
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
    
    private void populateTableMec(List<Mecanici> mecanici){
        defaultTableMec.setRowCount(0);
        for (Mecanici m : mecanici){
            defaultTableMec.addRow(new Object[]{m.getCODMEC(), m.getNume(), m.getSpecializare()});
        }
    }
    
    private void cautaServicePopup() {
    JTextField vehInput = new JTextField();
    Object[] fields = {
        "Cod vehicul", vehInput,
    };

    int result = JOptionPane.showConfirmDialog(
        this, fields, "Cauta", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        try {
            String vehText = vehInput.getText().trim();
            Integer veh = null;

            if (!vehText.isEmpty()) {
                veh = Integer.parseInt(vehText);
            }

            ServiceDAO dao = new ServiceDAO(conn);
            List<Service> service = dao.cautaService(veh);
            populateTable(service);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Codul vehiculului trebuie sa fie un numar sau lasat gol.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
    private void cautaMecaniciPopup(){
        JTextField nume = new JTextField();
        Object[] fields = {
            "Nume: ", nume
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Cauta", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try{
                String numeText = nume.getText().trim();
                MecaniciDAO dao = new MecaniciDAO(conn);
                List<Mecanici> mecanici = dao.cautaMecanici(numeText);
                populateTableMec(mecanici);
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
    
    private void insertMecaniciPopup(){
        JTextField numeField = new JTextField();
        JTextField specializareField = new JTextField();
        
        Object[] fields = {
            "Nume: ", numeField,
            "Specializare: ", specializareField
        };
        
        int result = JOptionPane.showConfirmDialog(
        this,fields,"Adauga",JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            try{
                String nume = numeField.getText().trim();
                String specializare = specializareField.getText().trim();
                
                MecaniciDAO dao = new MecaniciDAO(conn);
                dao.insertMecanici(nume, specializare);
                loadAllMecanici();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void updateServicePopup(){
        int selectedRow = serviceTable.getSelectedRow();
        
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this,"eroare");
            return;
        }
        int cods = (int) serviceTable.getValueAt(selectedRow, 0);
        String diagnosticVechi = (String) serviceTable.getValueAt(selectedRow, 1);
        int costVechi = (int) serviceTable.getValueAt(selectedRow, 2);
        int codvVechi = (int) serviceTable.getValueAt(selectedRow, 3);
        int codmVechi = (int) serviceTable.getValueAt(selectedRow, 4);
        
        JTextField diagnosticUpdate = new JTextField(diagnosticVechi);
        JTextField costUpdate = new JTextField(String.valueOf(costVechi));
        JTextField codvUpdate = new JTextField(String.valueOf(codvVechi));
        JTextField codmUpdate = new JTextField(String.valueOf(codmVechi));
        
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
    
    private void updateMecaniciPopup(){
        int selectedRow = mecaniciTable.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(this,"eroare");
            return;
        }
        int codmec = (int) mecaniciTable.getValueAt(selectedRow, 0);
        String numeVechi = (String) mecaniciTable.getValueAt(selectedRow, 1);
        String specializareVechi = (String) mecaniciTable.getValueAt(selectedRow, 2);
        
        JTextField numeUpdate = new JTextField(numeVechi);
        JTextField specializareUpdate = new JTextField(specializareVechi);
        
        Object[] fields = {
            "Nume nou: ", numeUpdate,
            "Specializare noua: ", specializareUpdate,
        };
        
        int result = JOptionPane.showConfirmDialog(
        this, fields, "Update", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION){
            String numeNou = numeUpdate.getText().trim();
            String specializareNoua = specializareUpdate.getText().trim();
            
            if(numeNou.isEmpty() || specializareNoua.isEmpty()){
                JOptionPane.showMessageDialog(this, "Toate campurile trebuie completate!");
                return;
            }
            try{
                MecaniciDAO dao = new MecaniciDAO(conn);
                dao.updateMecanici(codmec, numeNou, specializareNoua);
                loadAllMecanici();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void deleteServicePopup(){
            JTextField codsDelete = new JTextField();
            Object[] fields = {
                "COD", codsDelete
            };
                int result = JOptionPane.showConfirmDialog(
                        this, fields, "Sterge", JOptionPane.OK_CANCEL_OPTION);
                
                if(result == JOptionPane.OK_OPTION){
                    try{
                        int CODS = Integer.parseInt(codsDelete.getText().trim());
                        
                        ServiceDAO dao = new ServiceDAO(conn);
                        dao.deleteService(CODS);
                        loadAllService();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
        }
    
    private void deleteMecaniciPopup(){
        JTextField codmDelete = new JTextField();
        Object[] fields = {
            "Cod mecanic: ", codmDelete
        };
        int result = JOptionPane.showConfirmDialog(
        this,fields, "Sterge",JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            try{
                int CODMEC = Integer.parseInt(codmDelete.getText().trim());
                MecaniciDAO dao = new MecaniciDAO(conn);
                dao.deleteMecanici(CODMEC);
                loadAllMecanici();
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
        Cauta = new javax.swing.JButton();
        Insert = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        mecaniciTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        mCauta = new javax.swing.JButton();
        mInsert = new javax.swing.JButton();
        mUpdate = new javax.swing.JButton();
        mDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

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

        mecaniciTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(mecaniciTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("MECANICI");

        mCauta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mCauta.setText("CAUTA");
        mCauta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mCautaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mCautaMouseExited(evt);
            }
        });
        mCauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCautaActionPerformed(evt);
            }
        });

        mInsert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mInsert.setText("INSERT");
        mInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mInsertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mInsertMouseExited(evt);
            }
        });
        mInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mInsertActionPerformed(evt);
            }
        });

        mUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mUpdate.setText("UPDATE");
        mUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mUpdateMouseExited(evt);
            }
        });
        mUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mUpdateActionPerformed(evt);
            }
        });

        mDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mDelete.setText("DELETE");
        mDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mDeleteMouseExited(evt);
            }
        });
        mDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mDeleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("REPARATII");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(488, 488, 488)
                                .addComponent(Home1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(Cauta)
                                .addGap(18, 18, 18)
                                .addComponent(Insert)
                                .addGap(18, 18, 18)
                                .addComponent(Update)
                                .addGap(18, 18, 18)
                                .addComponent(Delete)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mCauta)
                        .addGap(18, 18, 18)
                        .addComponent(mInsert)
                        .addGap(18, 18, 18)
                        .addComponent(mUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(mDelete))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                    .addComponent(Cauta)
                    .addComponent(Insert)
                    .addComponent(Update)
                    .addComponent(Delete)
                    .addComponent(jLabel1)
                    .addComponent(mCauta)
                    .addComponent(mInsert)
                    .addComponent(mUpdate)
                    .addComponent(mDelete)
                    .addComponent(jLabel2))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Home1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home1MouseEntered
        // TODO add your handling code here:
        Home1.setOpaque(true);
        Home1.setBackground(new Color(211, 211, 211));
    }//GEN-LAST:event_Home1MouseEntered

    private void Home1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home1MouseExited
        // TODO add your handling code here:
        Home1.setOpaque(false);
    }//GEN-LAST:event_Home1MouseExited

    private void Home1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Home1ActionPerformed
        // TODO add your handling code here:
        parent.showPanel("menu");
    }//GEN-LAST:event_Home1ActionPerformed

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        // TODO add your handling code here:
        insertServicePopup();
    }//GEN-LAST:event_InsertActionPerformed

    private void CautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CautaActionPerformed
        // TODO add your handling code here:
        cautaServicePopup();
    }//GEN-LAST:event_CautaActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        updateServicePopup();
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        deleteServicePopup();
    }//GEN-LAST:event_DeleteActionPerformed

    private void CautaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CautaMouseEntered
        // TODO add your handling code here:
        Cauta.setOpaque(true);
        Cauta.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_CautaMouseEntered

    private void CautaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CautaMouseExited
        // TODO add your handling code here:
        Cauta.setOpaque(false);
    }//GEN-LAST:event_CautaMouseExited

    private void InsertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InsertMouseEntered
        // TODO add your handling code here:
        Insert.setOpaque(true);
        Insert.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_InsertMouseEntered

    private void InsertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InsertMouseExited
        // TODO add your handling code here:
        Insert.setOpaque(false);
    }//GEN-LAST:event_InsertMouseExited

    private void UpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateMouseEntered
        // TODO add your handling code here:
        Update.setOpaque(true);
        Update.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_UpdateMouseEntered

    private void UpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateMouseExited
        // TODO add your handling code here:
        Update.setOpaque(false);
    }//GEN-LAST:event_UpdateMouseExited

    private void DeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseEntered
        // TODO add your handling code here:
        Delete.setOpaque(true);
        Delete.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_DeleteMouseEntered

    private void DeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseExited
        // TODO add your handling code here:
        Delete.setOpaque(false);
    }//GEN-LAST:event_DeleteMouseExited

    private void mInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mInsertActionPerformed
        // TODO add your handling code here:
        insertMecaniciPopup();
    }//GEN-LAST:event_mInsertActionPerformed

    private void mCautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCautaActionPerformed
        // TODO add your handling code here:
        cautaMecaniciPopup();
    }//GEN-LAST:event_mCautaActionPerformed

    private void mUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mUpdateActionPerformed
        // TODO add your handling code here:
        updateMecaniciPopup();
    }//GEN-LAST:event_mUpdateActionPerformed

    private void mDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mDeleteActionPerformed
        // TODO add your handling code here:
        deleteMecaniciPopup();
    }//GEN-LAST:event_mDeleteActionPerformed

    private void mCautaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mCautaMouseEntered
        // TODO add your handling code here:
        mCauta.setOpaque(true);
        mCauta.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_mCautaMouseEntered

    private void mCautaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mCautaMouseExited
        // TODO add your handling code here:
        mCauta.setOpaque(false);
    }//GEN-LAST:event_mCautaMouseExited

    private void mInsertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mInsertMouseEntered
        // TODO add your handling code here:
        mInsert.setOpaque(true);
        mInsert.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_mInsertMouseEntered

    private void mInsertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mInsertMouseExited
        // TODO add your handling code here:
        mInsert.setOpaque(false);
    }//GEN-LAST:event_mInsertMouseExited

    private void mUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mUpdateMouseEntered
        // TODO add your handling code here:
        mUpdate.setOpaque(true);
        mUpdate.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_mUpdateMouseEntered

    private void mUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mUpdateMouseExited
        // TODO add your handling code here:
        mUpdate.setOpaque(false);
    }//GEN-LAST:event_mUpdateMouseExited

    private void mDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mDeleteMouseEntered
        // TODO add your handling code here:
        mDelete.setOpaque(true);
        mDelete.setBackground(new Color(211,211,211));
    }//GEN-LAST:event_mDeleteMouseEntered

    private void mDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mDeleteMouseExited
        // TODO add your handling code here:
        mDelete.setOpaque(false);
    }//GEN-LAST:event_mDeleteMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cauta;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Home1;
    private javax.swing.JButton Insert;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private javax.swing.JButton mCauta;
    private javax.swing.JButton mDelete;
    private javax.swing.JButton mInsert;
    private javax.swing.JButton mUpdate;
    private javax.swing.JTable mecaniciTable;
    private javax.swing.JTable serviceTable;
    // End of variables declaration//GEN-END:variables


}
