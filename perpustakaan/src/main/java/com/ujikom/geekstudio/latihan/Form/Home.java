/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujikom.geekstudio.latihan.Form;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * 
 */
public class Home extends javax.swing.JFrame {
    Connection koneksi;
    ResultSet rss;
    Statement stm;
    Boolean ada = false;
    Boolean edit = false;
    Boolean tabel = true;
    int n, row = 0;
    
    public Home(){
        initComponents();
        buka_database();
        validasi();
    }
    //koneksi
    public void buka_database(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/perpustakaan", "root", "");
            stm = koneksi.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //validasi
    public void validasi(){
        try {
            String query = "SELECT userId";
            ResultSet resultSet = stm.executeQuery(query);

            if (resultSet.next()) {
                String username = resultSet.getString("userId");
                String level = resultSet.getString("userHak");

                if ("admin".equalsIgnoreCase(level)) {
                    // Code for admin
                } else if ("petugas".equalsIgnoreCase(level)) {
                    // Code for petugas
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error validating user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //
    /**
     * Creates new form Home
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnKeluar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        infoBuku = new javax.swing.JMenuItem();
        bukuFavorit = new javax.swing.JMenuItem();
        jumlahAnggota = new javax.swing.JMenuItem();
        peraturanPeminjaman = new javax.swing.JMenuItem();
        transaksi = new javax.swing.JMenu();
        daftarPeminjaman = new javax.swing.JMenuItem();
        daftarPengembalian = new javax.swing.JMenuItem();
        laporan = new javax.swing.JMenu();
        laporanPeminjaman = new javax.swing.JMenuItem();
        laporanPengembalian = new javax.swing.JMenuItem();
        laporanDenda = new javax.swing.JMenuItem();
        referensi = new javax.swing.JMenu();
        daftarBuku = new javax.swing.JMenuItem();
        daftarPengguna = new javax.swing.JMenuItem();
        konfigurasi = new javax.swing.JMenu();
        logout = new javax.swing.JMenuItem();
        backUpData = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        btnKeluar.setText("KELUAR");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .addComponent(btnKeluar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKeluar)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        file.setText("File");

        infoBuku.setText("Info Buku");
        infoBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoBukuActionPerformed(evt);
            }
        });
        file.add(infoBuku);

        bukuFavorit.setText("Buku Favorit");
        bukuFavorit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bukuFavoritActionPerformed(evt);
            }
        });
        file.add(bukuFavorit);

        jumlahAnggota.setText("Jumlah Anggota");
        jumlahAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahAnggotaActionPerformed(evt);
            }
        });
        file.add(jumlahAnggota);

        peraturanPeminjaman.setText("Peraturan Peminjaman");
        file.add(peraturanPeminjaman);

        jMenuBar1.add(file);

        transaksi.setText("Transaksi");

        daftarPeminjaman.setText("Daftar Peminjaman");
        daftarPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarPeminjamanActionPerformed(evt);
            }
        });
        transaksi.add(daftarPeminjaman);

        daftarPengembalian.setText("Daftar Pengembalian");
        daftarPengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarPengembalianActionPerformed(evt);
            }
        });
        transaksi.add(daftarPengembalian);

        jMenuBar1.add(transaksi);

        laporan.setText("Laporan");

        laporanPeminjaman.setText("Laporan Peminjaman");
        laporanPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanPeminjamanActionPerformed(evt);
            }
        });
        laporan.add(laporanPeminjaman);

        laporanPengembalian.setText("Laporan Pengembalian");
        laporanPengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanPengembalianActionPerformed(evt);
            }
        });
        laporan.add(laporanPengembalian);

        laporanDenda.setText("Laporan Denda");
        laporanDenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanDendaActionPerformed(evt);
            }
        });
        laporan.add(laporanDenda);

        jMenuBar1.add(laporan);

        referensi.setText("Referensi");

        daftarBuku.setText("Daftar Buku");
        daftarBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarBukuActionPerformed(evt);
            }
        });
        referensi.add(daftarBuku);

        daftarPengguna.setText("Daftar Pengguna");
        daftarPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarPenggunaActionPerformed(evt);
            }
        });
        referensi.add(daftarPengguna);

        jMenuBar1.add(referensi);

        konfigurasi.setText("Konfigurasi");

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        konfigurasi.add(logout);

        backUpData.setText("Back Up Data");
        backUpData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backUpDataActionPerformed(evt);
            }
        });
        konfigurasi.add(backUpData);

        jMenuBar1.add(konfigurasi);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jumlahAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahAnggotaActionPerformed
        // TODO add your handling code here:
        JumlahAnggota d = new JumlahAnggota(this, rootPaneCheckingEnabled);
        d.setVisible(true);
    }//GEN-LAST:event_jumlahAnggotaActionPerformed

    private void bukuFavoritActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bukuFavoritActionPerformed
        // TODO add your handling code here:
        BukuFavorit c = new BukuFavorit(this, rootPaneCheckingEnabled);
        c.setVisible(true);
    }//GEN-LAST:event_bukuFavoritActionPerformed

    private void daftarBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarBukuActionPerformed
        // TODO add your handling code here:
        DaftarBuku a = new DaftarBuku(this, rootPaneCheckingEnabled);
        a.setVisible(true);
    }//GEN-LAST:event_daftarBukuActionPerformed

    private void infoBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoBukuActionPerformed
        // TODO add your handling code here:
        InfoBuku b = new InfoBuku(this, rootPaneCheckingEnabled);
        b.setVisible(true);
    }//GEN-LAST:event_infoBukuActionPerformed

    private void daftarPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarPenggunaActionPerformed
        // TODO add your handling code here:
        DaftarPengguna e = new DaftarPengguna(this, rootPaneCheckingEnabled);
        e.setVisible(true);
    }//GEN-LAST:event_daftarPenggunaActionPerformed

    private void daftarPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarPeminjamanActionPerformed
        // TODO add your handling code here:
        Peminjaman f = new Peminjaman(this, rootPaneCheckingEnabled);
        f.setVisible(true);
    }//GEN-LAST:event_daftarPeminjamanActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Anda yakin Akan Keluar ?\nTekan OK untuk keluar !\nTekan Cancel untuk membatalkan !",
            "Konfirmasi", JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE)
            == JOptionPane.OK_OPTION){
        this.dispose();
    }
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void daftarPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarPengembalianActionPerformed
        // TODO add your handling code here:
        Pengembalian g = new Pengembalian(this, rootPaneCheckingEnabled);
        g.setVisible(true);
    }//GEN-LAST:event_daftarPengembalianActionPerformed

    private void backUpDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backUpDataActionPerformed
        // TODO add your handling code here:
        BackUpData h = new BackUpData(this, rootPaneCheckingEnabled);
        h.setVisible(true);
    }//GEN-LAST:event_backUpDataActionPerformed

    private void laporanPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanPeminjamanActionPerformed
        // TODO add your handling code here:
        try {
        buka_database();
        stm = koneksi.createStatement();
        String reportContent = generatePeminjamanreport();
        printReport(reportContent);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_laporanPeminjamanActionPerformed

    private void laporanPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanPengembalianActionPerformed
        // TODO add your handling code here:
        try {
        buka_database();
        stm = koneksi.createStatement();
        Map <String, Object> map = new HashMap<String, Object>();
        String reportContent = generatePengembalianReport();
        printReport(reportContent);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_laporanPengembalianActionPerformed

    private void laporanDendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanDendaActionPerformed
        // TODO add your handling code here:
        try {
        buka_database();
        stm = koneksi.createStatement();
        Map <String, Object> map = new HashMap<String, Object>();
        String reportContent = generateDendaReport();
        printReport(reportContent);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_laporanDendaActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InstantiationException {
        try{
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() ->{
            new Home().setVisible(true);
        });
    }


        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem backUpData;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JMenuItem bukuFavorit;
    private javax.swing.JMenuItem daftarBuku;
    private javax.swing.JMenuItem daftarPeminjaman;
    private javax.swing.JMenuItem daftarPengembalian;
    private javax.swing.JMenuItem daftarPengguna;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem infoBuku;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem jumlahAnggota;
    private javax.swing.JMenu konfigurasi;
    private javax.swing.JMenu laporan;
    private javax.swing.JMenuItem laporanDenda;
    private javax.swing.JMenuItem laporanPeminjaman;
    private javax.swing.JMenuItem laporanPengembalian;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem peraturanPeminjaman;
    private javax.swing.JMenu referensi;
    private javax.swing.JMenu transaksi;
    // End of variables declaration//GEN-END:variables

    private String generatePeminjamanreport() {
        return "";
    }

    private void printReport(String reportContent) {
    }

    private String generatePengembalianReport() {
        return "";
    }

    private String generateDendaReport() {
        return "";
    }
}
