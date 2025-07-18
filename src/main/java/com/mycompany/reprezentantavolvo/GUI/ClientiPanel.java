/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.reprezentantavolvo.GUI;

import com.mycompany.reprezentantavolvo.Clienti;
import com.mycompany.reprezentantavolvo.ClientiDAO;

import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 *
 * @author robii
 */
public class ClientiPanel extends javax.swing.JPanel {
    MainWindow parent;
    private DefaultTableModel defaultTable;
    private Connection conn;
    /**
     * Creates new form Clienti
     */
    public ClientiPanel(MainWindow parent, Connection conn) {
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
        
        defaultTable = new DefaultTableModel(new Object[]{"CODC","Nume","Localitate"},0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        clientiTable.setModel(defaultTable);
        clientiTable.setRowSelectionAllowed(false);

        loadAllClienti();

        
        Home1.setBorder(BorderFactory.createEmptyBorder());
        Home1.setContentAreaFilled(false);
        Home1.setFocusPainted(false);
    }
    
    private void loadAllClienti(){
        try{
            ClientiDAO dao= new ClientiDAO(conn);
            List<Clienti> clienti = dao.getAllClienti();
            populateTable(clienti);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void searchClienti(){
        try{
            String nume = numeField.getText().trim();
            String localitate = localitateField.getText().trim();
            
            ClientiDAO dao = new ClientiDAO(conn);
            List<Clienti> clienti = dao.cautaClienti(nume, localitate);
            populateTable(clienti);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void populateTable(List<Clienti> clienti){
        defaultTable.setRowCount(0);
        for (Clienti c : clienti){
            defaultTable.addRow(new Object[]{c.getCODC(), c.getNume(),c.getLocalitate()});
        }
    }
    
private void insertClientPopup() {
    JTextField numeInput = new JTextField();
    JTextField localitateInput = new JTextField();
    Object[] fields = {
        "Nume:", numeInput,
        "Localitate:", localitateInput
    };

    int result = JOptionPane.showConfirmDialog(
        this, fields, "Adauga", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        try {
            String nume = numeInput.getText().trim();
            String localitate = localitateInput.getText().trim();
            if (nume.isEmpty() || localitate.isEmpty()) return;

            ClientiDAO dao = new ClientiDAO(conn);
            dao.insertClient(nume, localitate);
            loadAllClienti();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

private void updateClientPopup(){
    int selectedRow = clientiTable.getSelectedRow();
    
    int codc = (int) clientiTable.getValueAt(selectedRow, 0);
    String numeVechi = (String) clientiTable.getValueAt(selectedRow, 1);
    String localitateVeche = (String) clientiTable.getValueAt(selectedRow, 2);
    
    JTextField numeUpdate = new JTextField(numeVechi);
    JTextField localitateUpdate = new JTextField(localitateVeche);
    
    Object[] fields = {
        "Nume nou: ", numeUpdate,
        "Localitate noua: ", localitateUpdate
    };
    
    int result = JOptionPane.showConfirmDialog(
    this, fields, "Modifica client", JOptionPane.OK_CANCEL_OPTION);
    
    if (result == JOptionPane.OK_OPTION){
        String numeNou = numeUpdate.getText().trim();
        String localitateNoua = localitateUpdate.getText().trim();
        
        if (numeNou.isEmpty() || localitateNoua.isEmpty()){
            JOptionPane.showMessageDialog(this, "Toate campurile trebuie completate!");
            return;
        }
        try{
            ClientiDAO dao = new ClientiDAO(conn);
            dao.updateClient(codc, numeNou, localitateNoua);
            loadAllClienti();
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la actualizarea clientului");
        }
    }
}

private void deleteClientPopup(){
    JTextField CODCDelete = new JTextField();
    Object[] fields = {
        "CODC: ", CODCDelete
    };
    
    int result = JOptionPane.showConfirmDialog(
    this, fields, "Sterge", JOptionPane.OK_CANCEL_OPTION);
    
    if (result == JOptionPane.OK_OPTION){
        try{
            int CODC = Integer.parseInt(CODCDelete.getText().trim());
            
            ClientiDAO dao = new ClientiDAO(conn);
            dao.deleteClient(CODC);
            loadAllClienti();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        clientiTable = new javax.swing.JTable();
        numeField = new java.awt.TextField();
        localitateField = new java.awt.TextField();
        Cauta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Insert = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();

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

        clientiTable.setModel(new javax.swing.table.DefaultTableModel(
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
        clientiTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(clientiTable);

        numeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeFieldActionPerformed(evt);
            }
        });

        localitateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localitateFieldActionPerformed(evt);
            }
        });

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("NUME CLIENT");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("LOCALITATE");

        Insert.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        Insert.setText("INSERT");
        Insert.setPreferredSize(new java.awt.Dimension(75, 22));
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
        Update.setPreferredSize(new java.awt.Dimension(75, 22));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(488, 488, 488)
                .addComponent(Home1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(localitateField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cauta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                        .addComponent(Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Delete))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(localitateField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cauta, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void localitateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localitateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_localitateFieldActionPerformed

    private void numeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeFieldActionPerformed

    private void CautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CautaActionPerformed
        // TODO add your handling code here:
        searchClienti();
    }//GEN-LAST:event_CautaActionPerformed

    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        // TODO add your handling code here:
        insertClientPopup();
    }//GEN-LAST:event_InsertActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        updateClientPopup();
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        deleteClientPopup();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cauta;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Home1;
    private javax.swing.JButton Insert;
    private javax.swing.JButton Update;
    private javax.swing.JTable clientiTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label1;
    private java.awt.TextField localitateField;
    private java.awt.TextField numeField;
    // End of variables declaration//GEN-END:variables
}
