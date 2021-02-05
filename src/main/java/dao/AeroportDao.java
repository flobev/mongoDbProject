/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.compagnie.compagnieaeriennemongodb.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author flo
 */
public class AeroportDao {
     
    Connection con;
    PreparedStatement pst;
    
    JfAeroports a = new JfAeroports();
    
    JTextField inputNom = a.getInputNameAeroport();
    JTextField inputVille = a.getInputVilleAeroport();
    JTextField inputPays = a.getInputPaysAeroport();
    JTable tableAeroports = a.getTableAeroports();
    
    public void ajouterAeroport(){
        
        if(inputNom.getText().equals("") || inputVille.getText().equals("") || inputPays.getText().equals("")){
            JOptionPane.showMessageDialog(this.a, "Vous devez remplir les champs !", "ATTENTION", JOptionPane.INFORMATION_MESSAGE);;
            inputNom.requestFocus();
        } else {
            String nomAeroport = inputNom.getText();
            String villeAeroport = inputVille.getText();
            String paysAeroport = inputPays.getText();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
                pst=con.prepareStatement("insert into aeroport (nom, ville, pays) values (?,?,?)");
                pst.setString(1,nomAeroport);
                pst.setString(2,villeAeroport);
                pst.setString(3,paysAeroport);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this.a, "Données enregistrées !");

                inputNom.setText("");
                inputVille.setText("");
                inputPays.setText("");

                tableUpdate();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void modifierAeroport(){
        DefaultTableModel dtm = (DefaultTableModel)tableAeroports.getModel();
        int selectedIndex = tableAeroports.getSelectedRow();
        
        try {
            
            int idAerop = Integer.parseInt(dtm.getValueAt(selectedIndex, 0).toString());
            String nomAeroport = inputNom.getText();
            String villeAeroport = inputVille.getText(); 
            String paysAeroport = inputVille.getText(); 
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
            pst=con.prepareStatement("update aeroport set nom = ?, ville = ?, pays = ? where idaeroport = ?");
            
            pst.setString(1, nomAeroport);
            pst.setString(2, villeAeroport);
            pst.setString(3, paysAeroport);
            pst.setInt(4, idAerop);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this.a, "Données mises à jour !");
            
            inputNom.setText("");
            inputVille.setText("");
            inputPays.setText("");
            
            tableUpdate();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerAeroport(){
        DefaultTableModel dtm = (DefaultTableModel)tableAeroports.getModel();
        int selectedIndex = tableAeroports.getSelectedRow();
            
        int idAerop = Integer.parseInt(dtm.getValueAt(selectedIndex, 0).toString());
        int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette donnée ?", "Attention !", JOptionPane.YES_NO_OPTION);

        if(dialogResult==JOptionPane.YES_OPTION)
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
                pst=con.prepareStatement("delete from aeroport where idaeroport=?");

                pst.setInt(1, idAerop);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this.a, "Donnée supprimée!");

                inputNom.setText("");
                inputVille.setText("");
                inputPays.setText("");

                tableUpdate();
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void tableUpdate(){
        
        int cpt;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3307/airbabouche", "root","");
            pst=con.prepareStatement("select * from aeroport");
            ResultSet rs = pst.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData(); //Recupération des données sql
            
            cpt = rsmd.getColumnCount();
            
            DefaultTableModel dtm = (DefaultTableModel)tableAeroports.getModel();
            
            dtm.setRowCount(0);
            
            while(rs.next()) {
                
                Vector vect = new Vector();
                
                for (int i = 0; i < cpt; i++) {
                    vect.add(rs.getString("idaeroport"));
                    vect.add(rs.getString("nom"));
                    vect.add(rs.getString("ville"));
                    vect.add(rs.getString("pays"));
                }
                dtm.addRow(vect);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JfAeroports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectionTableAeroports(){
        DefaultTableModel dtm = (DefaultTableModel)a.getTableAeroports().getModel();
        int selectedIndex = a.getTableAeroports().getSelectedRow();
        
        inputNom.setText(dtm.getValueAt(selectedIndex, 1).toString());
        inputVille.setText(dtm.getValueAt(selectedIndex, 2).toString());
        inputPays.setText(dtm.getValueAt(selectedIndex, 3).toString());
    }
}
