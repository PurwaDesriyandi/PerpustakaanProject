/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujikom.geekstudio.latihan.Form;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * 
 */
public class AplikasiPerpustakaan {
    private String PROP_FILE="config.ini";
    private Properties p2 = new Properties();
    public Home Start = new Home();
    public String vsNIK,Jabatan;

    
    public static void main(String[] args) {
        new Login().setVisible(true);
        String userRole = "Admin"; 
        new AplikasiPerpustakaan().HakAkses(userRole);
    }
    
    public void koneksiDatabase(){
        String server = "localhost";
        String user = "root";
        String password ="";
        String database ="perpustakaan";
    }
    public static Properties loadProperties(String sFile){
        Properties p = new Properties();
        try {
	FileInputStream in = new FileInputStream(sFile);
	p.load(in);
	in.close();
      } catch (IOException iOException) {
 	System.out.println(iOException);
      }
        return p;
    }
    public static void saveProperties(Properties p, String sFile)
       throws IOException{

   FileOutputStream out = null;
   try {
	out = new FileOutputStream(sFile);
	p.store(out, "Ini baris komentar\nFile konfigurasi");

   } catch (FileNotFoundException ex) {
	JOptionPane.showMessageDialog(null, ex.getMessage());
   } finally {
        try {
	   out.close();
         } catch (IOException ex) {
	    JOptionPane.showMessageDialog(null, ex.getMessage());
         }
    }
  }
    public void loadNIK(JLabel Label1){
        p2 = loadProperties(PROP_FILE);
	Label1.setText(p2.getProperty("NIK"));	
  }
    public void saveNIK(String NIK) {
      try {
        Properties p = new Properties();
        
	p.setProperty("NIK", NIK);
	
	saveProperties(p, PROP_FILE);
	} catch (IOException ex) {
	    JOptionPane.showMessageDialog(null, ex.getMessage());
	}
  }
    public void HakAkses(String userRole){
      try{ 
            if (Jabatan.equals("Admin")) {
                grantAdminAccess();
            } else if (Jabatan.equals("Operator")) {
                grantOperatorAccess();
            } else if (Jabatan.equals("User")) {
                grantUserAccess();
            } else {
                // Hak akses default jika peran tidak dikenali
                grantDefaultAccess();
         }
        } catch (Exception ex) {
            // Handle exception
            ex.printStackTrace();
        }
    }
        private void grantAdminAccess() {
        // Implementasi hak akses Admin
        // Start.jMnItmCTGRY.setVisible(false);
        // Start.jMnItmCabang.setVisible(false);
        // Start.jMnItmDept.setVisible(false);
        // Start.jMnItmKaryawan.setVisible(false);
        // Start.jMnItmSBS.setVisible(false);
    }

    // Metode untuk memberikan hak akses Operator
    private void grantOperatorAccess() {
        // Implementasi hak akses Operator
        // Start.jMnMaster.setVisible(false);
        // Start.jMnTRN.setVisible(false);
        // Start.jMnPopulasi.setVisible(false);
    }

    // Metode untuk memberikan hak akses User
    private void grantUserAccess() {
        // Implementasi hak akses User
        // Start.jMnMaster.setVisible(false);
        // Start.jMnPopulasi.setVisible(false);
    }

    // Metode untuk memberikan hak akses default
    private void grantDefaultAccess() {
       
    }
  }
    
