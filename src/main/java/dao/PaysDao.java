/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.compagnie.compagnieaeriennemongodb.*;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author flo
 */
public class PaysDao {
    
    Connection con;
    PreparedStatement pst;
    DefaultTableModel dtm;
    
    JfPays a = new JfPays();
    
    JTextField inputNom = a.getInputNamePays();
    JTable tablePays = a.getTablePays();
   
    /**
     *
     */
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
                    vect.add(rs.getString("idaeroport"));
                    vect.add(rs.getString("nom"));
                }
                dtm.addRow(vect);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaysDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PaysDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectionTablePays(){
        DefaultTableModel dtm = (DefaultTableModel)tablePays.getModel();
        int selectedIndex = tablePays.getSelectedRow();
        
        inputNom.setText(dtm.getValueAt(selectedIndex, 1).toString());
    }
    
    public void ajouterPays(){
        
        if(inputNom.getText().equals("")){
            JOptionPane.showMessageDialog(this.a, "Vous devez remplir les champs !", "ATTENTION", JOptionPane.INFORMATION_MESSAGE);
            inputNom.requestFocus();
        } else {
            String nomPays = inputNom.getText();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
                pst=con.prepareStatement("insert into pays (nom) values (?)");
                pst.setString(1,nomPays);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this.a, "Données enregistrées !");

                inputNom.setText("");

                tableUpdate();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void modifierPays(){
        DefaultTableModel dtm = (DefaultTableModel)tablePays.getModel();
        int selectedIndex = tablePays.getSelectedRow();
        
        try {
            
            int idPays = Integer.parseInt(dtm.getValueAt(selectedIndex, 0).toString());
            String nomPays = inputNom.getText();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
            pst=con.prepareStatement("update pays set nom = ? where idPays = ?");
            
            pst.setString(1, nomPays);
            pst.setInt(2, idPays);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this.a, "Données mises à jour !");
            
            inputNom.setText("");
            
            tableUpdate();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerAeroport(){
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

                JOptionPane.showMessageDialog(this.a, "Donnée supprimée!");

                inputNom.setText("");

                tableUpdate();
                
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfPays.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
