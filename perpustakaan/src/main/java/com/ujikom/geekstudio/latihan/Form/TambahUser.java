/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujikom.geekstudio.latihan.Form;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author purwa
 */
public class TambahUser extends javax.swing.JDialog {
    Connection koneksi;
    ResultSet rss;
    Statement stm;
    Boolean ada = false;
    Boolean edit = false;
    Boolean tabel = true;
    int n, row = 0;
    ///
    //koneksiDatabase
    public void buka_database(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/perpustakaan", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("Error #1 : " + e.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("Error #2 : " + e.getMessage());
            System.exit(0);
        }
    }
    //
    
    public void buatNoBuku(){
        try {
            stm = koneksi.createStatement();
            String sql = "select userId from tm_user order by userId desc";
            rss = stm.executeQuery(sql);
            if (rss.next()){
                String ambilNo = rss.getString("userId");
                String sub = ambilNo.substring(5, 10);
                int counter = Integer.parseInt(sub) + 1;
                txtIdUser.setText("USER00000" + Integer.toString(counter));
            }else{
                txtIdUser.setText("USER000001");
            }
            rss.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Buat Nomor RM !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Buat Nomor RM !", "Informasi", 1);
        }
    }
    //
    //edit mati
    public void SetEditMati(){
       txtIdUser.setEnabled(false); 
       txtUser.setEnabled(false);
       txtPass.setEnabled(false); 
    }
    //
    //edit hidup
    public void SetEditHidup(){
       txtIdUser.setEnabled(false); 
       txtUser.setEnabled(true); 
       txtPass.setEnabled(true); 
        
    }
    //
    //bersih
    public void Bersih(){
        txtIdUser.setText("");
        txtUser.setText("");
        txtPass.setText("");
    }
    //
    //button hidup mati
    public void SetTombolHidup(){
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
    }
    
    public void SetTombolMati(){
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
    }
    //
    ///
    
    /**
     * Creates new form TambahUser
     */
    public TambahUser(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        SetEditMati();
        buka_database();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdUser = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnTambah = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tambah User");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User Id");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdUser, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txtUser)
                            .addComponent(txtPass))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTambah)
                            .addComponent(btnSimpan)
                            .addComponent(btnKeluar))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKeluar))
                .addContainerGap(153, Short.MAX_VALUE))
        );

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

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        //tabelDaftarBuku.setEnabled(false);
        Bersih();
        buatNoBuku();
        SetTombolHidup();
        SetEditHidup();
        //tabelDaftarBuku.isEnabled();
        edit = false;
        //cbUser.setEnabled(false);
        //tampil_kode_user();
        tabel = false;
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
        String SQL;
        String password = new String(txtPass.getPassword());
        Statement stm = koneksi.createStatement();
        if (edit == false){
            SQL = "insert into tm_user values '" + txtIdUser.getText() + "','"
                    + txtUser.getText() + "','"
                    + txtPass.getText() + "','";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Data dengan User Id " + txtIdUser.getText() + " Berhasil di Simpan !");
            
        }else{
            SQL = "update tm_user set "
                    + "userId='" + txtIdUser.getText() + "',"
                    + "userNama='" + txtUser.getText() + "',"
                    + "userPass='" + txtPass.getText() + "',"
                    + "where userId='" + txtIdUser.getText() + ",";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Data dengan Kode Buku " + txtIdUser.getText() + " berhasil diperbaharui !");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Simpan/Update !", "Informasi", 1);
    } catch (Exception e){
        JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Simpan/Update !", "Informasi", 1);
    }
    
    //tampildata();
    Bersih();
    SetTombolMati();
    SetEditMati();
    //tabelDaftarBuku.setEnabled(true);
    tabel = true;
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Anda yakin Akan Keluar ?\nTekan OK untuk keluar !\nTekan Cancel untuk membatalkan !",
            "Konfirmasi", JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE)
            == JOptionPane.OK_OPTION){
        this.dispose();}
        
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

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
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TambahUser dialog = new TambahUser(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtIdUser;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
