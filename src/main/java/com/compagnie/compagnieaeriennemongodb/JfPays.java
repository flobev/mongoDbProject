/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnie.compagnieaeriennemongodb;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import dao.PaysDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author flo
 */
public class JfPays extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    
    /**
     * Creates new form JfPays
     */
    public JfPays() {
        initComponents();
        tableUpdate();
    }

    public JButton getBtnAjouterPays() {
        return btnAjouterPays;
    }

    public JButton getBtnModifierPays() {
        return btnModifierPays;
    }

    public JButton getBtnSupprimerPays() {
        return btnSupprimerPays;
    }

    public JTextField getInputNamePays() {
        return inputNamePays;
    }

    public JTable getTablePays() {
        return tablePays;
    }
    
    
    public void tableUpdate(){
        
        int cpt;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
            pst=con.prepareStatement("select * from pays");
            ResultSet rs = pst.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData(); //Recupération des données sql
            
            cpt = rsmd.getColumnCount();
            
            DefaultTableModel dtm = (DefaultTableModel)tablePays.getModel();
            
            dtm.setRowCount(0);
            
            while(rs.next()) {
                
                Vector vect = new Vector();
                
                for (int i = 0; i < cpt; i++) {
                    vect.add(rs.getString("idPays"));
                    vect.add(rs.getString("nom"));
                }
                dtm.addRow(vect);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputNamePays = new javax.swing.JTextField();
        btnAjouterPays = new javax.swing.JButton();
        btnModifierPays = new javax.swing.JButton();
        btnSupprimerPays = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePays = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.gray);

        jLabel1.setText("Nom");

        btnAjouterPays.setText("Ajouter");
        btnAjouterPays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterPaysActionPerformed(evt);
            }
        });

        btnModifierPays.setText("Modifier");
        btnModifierPays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierPaysActionPerformed(evt);
            }
        });

        btnSupprimerPays.setText("Supprimer");
        btnSupprimerPays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerPaysActionPerformed(evt);
            }
        });

        jLabel2.setText("Formulaire Pays");

        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnAjouterPays)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModifierPays)
                .addGap(58, 58, 58)
                .addComponent(btnSupprimerPays)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(inputNamePays, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnMenu))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputNamePays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAjouterPays)
                    .addComponent(btnModifierPays)
                    .addComponent(btnSupprimerPays))
                .addGap(14, 14, 14))
        );

        tablePays.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nom"
            }
        ));
        tablePays.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePaysMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePays);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjouterPaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterPaysActionPerformed
        // TODO add your handling code here:
        if(inputNamePays.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Vous devez remplir les champs !", "ATTENTION", JOptionPane.INFORMATION_MESSAGE);
            inputNamePays.requestFocus();
        } else {
            String nomPays = inputNamePays.getText();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
                pst=con.prepareStatement("insert into pays (nom) values (?)");
                pst.setString(1,nomPays);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Données enregistrées !");

                inputNamePays.setText("");

                tableUpdate();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAjouterPaysActionPerformed

    private void btnModifierPaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierPaysActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel)tablePays.getModel();
        int selectedIndex = tablePays.getSelectedRow();
        
        try {
            
            int idPays = Integer.parseInt(dtm.getValueAt(selectedIndex, 0).toString());
            String nomPays = inputNamePays.getText();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
            pst=con.prepareStatement("update pays set nom = ? where idPays = ?");
            
            pst.setString(1, nomPays);
            pst.setInt(2, idPays);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Données mises à jour !");
            
            inputNamePays.setText("");
            
            tableUpdate();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnModifierPaysActionPerformed

    private void btnSupprimerPaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerPaysActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel)tablePays.getModel();
        int selectedIndex = tablePays.getSelectedRow();
            
        int idPays = Integer.parseInt(dtm.getValueAt(selectedIndex, 0).toString());
        int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette donnée ?", "Attention !", JOptionPane.YES_NO_OPTION);

        if(dialogResult==JOptionPane.YES_OPTION)
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
                pst=con.prepareStatement("delete from pays where idPays=?");

                pst.setInt(1, idPays);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Donnée supprimée!");

                inputNamePays.setText("");

                tableUpdate();
                
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSupprimerPaysActionPerformed

    private void tablePaysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePaysMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel)tablePays.getModel();
        int selectedIndex = tablePays.getSelectedRow();
        
        inputNamePays.setText(dtm.getValueAt(selectedIndex, 1).toString());
    }//GEN-LAST:event_tablePaysMouseClicked

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        dispose();
        PagePrincipale r = new PagePrincipale();
        r.setVisible(true);
    }//GEN-LAST:event_btnMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JfPays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfPays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfPays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfPays.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfPays().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterPays;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModifierPays;
    private javax.swing.JButton btnSupprimerPays;
    private javax.swing.JTextField inputNamePays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePays;
    // End of variables declaration//GEN-END:variables
}