/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujikom.geekstudio.latihan.Form;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import java.text.DateFormat;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Yasin Dwi Ramandhita
 */
public class JumlahAnggota extends javax.swing.JDialog {
    private JFileChooser chooser = new JFileChooser();
    Connection koneksi;
    ResultSet rss;
    Statement stm;
    Boolean ada = false;
    Boolean edit = false;
    Boolean tabel = true;
    int n, row = 0;
    
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
    //buatNoBuku
    public void buatNoBuku(){
        try {
            stm = koneksi.createStatement();
            String sql = "select kdAnggota from tm_anggota order by kdAnggota desc";
            rss = stm.executeQuery(sql);
            if (rss.next()){
                String ambilNo = rss.getString("kdAnggota");
                String sub = ambilNo.substring(5, 10);
                int counter = Integer.parseInt(sub) + 1;
                txtKdAnggota.setText("ANGT00000" + Integer.toString(counter));
            }else{
                txtKdAnggota.setText("ANGT000001");
            }
            rss.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Buat Nomor RM !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Buat Nomor RM !", "Informasi", 1);
        }
    }
    //
    //Jumlah Anggota
    public void jumlahAnggota(){
        try {
            stm = koneksi.createStatement();
            String sql = "SELECT COUNT(*)FROM tm_anggota";
            rss = stm.executeQuery(sql);
            
            //lblAnggota.setText(a);
            if (rss.next()){
                String a = rss.getString(1);
                lblAnggota.setText(a);
            }
            rss.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Buat Nomor RM !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Buat Nomor RM !", "Informasi", 1);
        }
    }
    //
    //tampilData
    public void tampildata(){
        DefaultTableModel yasin = new DefaultTableModel();
        yasin.addColumn("Kode Anggota");
        yasin.addColumn("Nama Anggota");
        yasin.addColumn("Jenis Kelamin");
        yasin.addColumn("Alamat Anggota");
        yasin.addColumn("No Tlp/Hp");
        yasin.addColumn("Foto Anggota");
        
        try {
            stm = koneksi.createStatement();
            String sql ="SELECT * FROM tm_anggota";
            rss = stm.executeQuery(sql);
            while (rss.next()){
                yasin.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getString(4),
                    rss.getString(5),});
            }
            tabelAnggota.setModel(yasin);
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(this,e);
           
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Data !", "Informasi", 1);
        }       
    }
    //
    //edit hidup mati bersih
    public void SetEditMati(){
       txtKdAnggota.setEnabled(false);
       txtNamaAnggota.setEnabled(false);
       cbAnggota.setEnabled(false);   
       txtAlamat.setEnabled(false); 
       txtNoHp.setEnabled(false);
       txtFoto.setEnabled(false);
    }
    
    public void SetEditHidup(){
       txtKdAnggota.setEnabled(true);
       txtNamaAnggota.setEnabled(true);
       cbAnggota.setEnabled(true);   
       txtAlamat.setEnabled(true); 
       txtNoHp.setEnabled(true);
       txtFoto.setEnabled(true);
    }
    
    public void Bersih(){
        txtKdAnggota.setText("");
        txtNamaAnggota.setText("");
        cbAnggota.setSelectedIndex(0);
        txtAlamat.setText("");
        txtNoHp.setText("");
        txtFoto.setText("");
    }
    //
    //button hidup mati
    public void SetTombolHidup(){
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnEdit.setEnabled(false);
        btnBatal.setEnabled(true);
        btnHapus.setEnabled(false);
    }
    
    public void SetTombolMati(){
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnEdit.setEnabled(true);
        btnBatal.setEnabled(false);
        btnHapus.setEnabled(true);
    }
    //
    //data detail
    public void dataDetail(int table){
        try {
            if (tabel == true){
            String a = tabelAnggota.getValueAt(table, 0).toString();
            String b = tabelAnggota.getValueAt(table, 1).toString();
            String c = tabelAnggota.getValueAt(table, 2).toString();
            String d = tabelAnggota.getValueAt(table, 3).toString();
            String e = tabelAnggota.getValueAt(table, 4).toString();
            String f = tabelAnggota.getValueAt(table, 5).toString();
            
            txtKdAnggota.setText(a);
            txtNamaAnggota.setText(b);
            cbAnggota.setSelectedItem(c);
            txtAlamat.setText(d);
            txtNoHp.setText(e);
            txtFoto.setText(f);
            } else {
                JOptionPane.showMessageDialog(this, "Tekan Batal terlebih dahulu !");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tekan Batal terlebih dahulu !");
        }
    }
    //
    public JumlahAnggota(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        SetEditMati();
        buka_database();
        tampildata();
        jumlahAnggota();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtKdAnggota = new javax.swing.JTextField();
        txtNamaAnggota = new javax.swing.JTextField();
        cbAnggota = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFoto = new javax.swing.JTextField();
        btnFile = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCariAnggota = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelAnggota = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lblAnggota = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Daftar Anggota Ceria Library");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "| Input Data Anggota |", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Anggota");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Anggota");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis Kelamin");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Alamat");

        cbAnggota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ".......", "L", "P" }));

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("No Tlp/Hp");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Foto Anggota");

        btnFile.setText("FILE");
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(txtKdAnggota)
                    .addComponent(txtNamaAnggota)
                    .addComponent(cbAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoHp)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFile)))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFile))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "| Kontroller |", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnKeluar.setText("KELUAR");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKeluar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "| Pencarian |", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kode/Nama Anggota");

        btnCari.setText("CARI");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Kode Anggota", "Nama Anggota" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCariAnggota)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCari))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCariAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCari))
        );

        tabelAnggota.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelAnggotaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelAnggota);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Jumlah Anggota :");

        lblAnggota.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAnggota.setForeground(new java.awt.Color(255, 255, 255));
        lblAnggota.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(lblAnggota)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblAnggota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        tabelAnggota.setEnabled(false);
        Bersih();
        buatNoBuku();
        SetTombolHidup();
        SetEditHidup();
        tabelAnggota.isEnabled();
        edit = false;
        //cbUser.setEnabled(false);
        //tampil_kode_user();
        tabel = false;
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        tabelAnggota.setEnabled(false);
        Bersih();
        buatNoBuku();
        SetTombolHidup();
        SetEditHidup();
        tabelAnggota.isEnabled();
        edit = false;
        //cbUser.setEnabled(false);
        //tampil_kode_user();
        tabel = false;
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
        String SQL;
        stm = koneksi.createStatement();
        if (edit == false){
            SQL = "insert into tm_anggota values ('" + txtKdAnggota.getText() + "','"
                    + txtNamaAnggota.getText()+ "','"
                    + cbAnggota.getSelectedItem() + "','"
                    + txtAlamat.getText() + "','"
                    + txtNoHp.getText() + "','"
                    + txtFoto.getText() + "')";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Data dengan Kode Buku " + txtKdAnggota.getText() + " Berhasil di Simpan !");
            
        }else{
            SQL = "update tm_anggota set "
                    + "kdAnggota='" + txtKdAnggota.getText() + "',"
                    + "nmAnggota='" + txtNamaAnggota.getText() + "',"
                    + "jnsKelamin='" + cbAnggota.getSelectedItem() + "',"
                    + "almtAnggota='" + txtAlamat.getText() + "',"
                    + "nmrTelp='" + txtNoHp.getText()+ "',"
                    + "potoAnggota='" + txtFoto.getText() + "',"
                    + "where kdAnggota='" + txtKdAnggota.getText() + "'";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Data dengan Kode Anggota " + txtKdAnggota.getText() + " berhasil diperbaharui !");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Simpan/Update !", "Informasi", 1);
    } catch (Exception e){
        JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Simpan/Update !", "Informasi", 1);
    }
    
    tampildata();
    Bersih();
    SetTombolMati();
    SetEditMati();
    tabelAnggota.setEnabled(true);
    tabel = true;
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (txtKdAnggota.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Pilih Data dari tabel terlebih dahulu !"
                + "\nKlik pada tabel atau gunakan navigasi !", "Informasi", 1);
    }else{
        SetTombolHidup();
        edit = true;
        SetEditHidup();
        tabelAnggota.setEnabled(true);
        tabel = false;
    }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if (txtKdAnggota.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Pilih Data dari tabel terlebih dahulu !"
                + "\nKlik pada tabel atau gunakan navigasi !", "Informasi", 1);
    }else{
        if (JOptionPane.showConfirmDialog(null,
                "Yakin Data dengan No Buku " + txtKdAnggota.getText() + " akan dihapus ?", "Informasi", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION){
            try {
                stm = koneksi.createStatement();
                String sql = "delete from tm_anggota where kdAnggota='" + txtKdAnggota.getText() + "'";
                stm.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus !");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Hapus !", "Informasi", 1);
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Hapus !", "Informasi", 1);
            }
        }
    }
    Bersih();
    SetTombolMati();
    SetEditMati();
    tampildata();
    tabelAnggota.setEnabled(true);
    tabel = true;
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Anda yakin Akan Keluar ?\nTekan OK untuk keluar !\nTekan Cancel untuk membatalkan !",
            "Konfirmasi", JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE)
            == JOptionPane.OK_OPTION){
        this.dispose();
    }
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        if (txtCariAnggota.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Silakan input kata kunci pencarian,"
                + "\nTentukan kriteria pencariannya !");
        txtCariAnggota.requestFocus();
    }else{
        try {
            DefaultTableModel yasin = new DefaultTableModel();
            yasin.addColumn("Kode Anggota");
            yasin.addColumn("Nama Anggota");
            yasin.addColumn("Jenis Kelamin");
            yasin.addColumn("Alamat Anggota");
            yasin.addColumn("No Tlp/Hp");
            yasin.addColumn("Foto Anggota");
            
            String sql;
            stm = koneksi.createStatement();
            String key = jComboBox1.getSelectedItem().toString();
            if (key.equalsIgnoreCase("Kode Anggota")){
                sql = "select*from tm_anggota where kdAnggota like '%" + txtCariAnggota.getText() + "%'";
            }else if (key.equalsIgnoreCase("Nama Anggota")){
                sql = "select*from tm_anggota where nmAnggota like '%" + txtCariAnggota.getText() + "%'";
            }else{
                sql = "select*from tm_anggota order by kdAnggota asc";
                tampildata();
            }
            rss = stm.executeQuery(sql);
            
            while (rss.next()){
                yasin.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getString(4),
                    rss.getString(5),});
            }
            tabelAnggota.setModel(yasin);
            txtCariAnggota.setText("");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Cari !", "Informasi", 1);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Cari !", "Informasi", 1);
        }
    }
    }//GEN-LAST:event_btnCariActionPerformed

    private void tabelAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelAnggotaMouseClicked
        // TODO add your handling code here:
        try {
        if (tabel == true){
            int table = tabelAnggota.getSelectedRow();
            String a = tabelAnggota.getValueAt(table, 0).toString();
            String b = tabelAnggota.getValueAt(table, 1).toString();
            String c = tabelAnggota.getValueAt(table, 2).toString();
            String d = tabelAnggota.getValueAt(table, 3).toString();
            String e = tabelAnggota.getValueAt(table, 4).toString();
            String f = tabelAnggota.getValueAt(table, 5).toString();
            
            txtKdAnggota.setText(a);
            txtNamaAnggota.setText(b);
            cbAnggota.setSelectedItem(c);
            txtAlamat.setText(d);
            txtNoHp.setText(e);
            txtFoto.setText(f);
        }else {
            JOptionPane.showMessageDialog(this, "Tekan BATAL terlebih dahulu !", "Informasi", 1);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Tekan BATAL terlebih dahulu !", "Informasi", 1);
    }
    }//GEN-LAST:event_tabelAnggotaMouseClicked

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        // TODO add your handling code here:
        int jes=chooser.showOpenDialog(this);

        if(jes==chooser.APPROVE_OPTION){
           File f=chooser.getSelectedFile();
           txtFoto.setText(f.getPath());
        }
    }//GEN-LAST:event_btnFileActionPerformed

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
            java.util.logging.Logger.getLogger(JumlahAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JumlahAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JumlahAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JumlahAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JumlahAnggota dialog = new JumlahAnggota(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox cbAnggota;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnggota;
    private javax.swing.JTable tabelAnggota;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCariAnggota;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JTextField txtKdAnggota;
    private javax.swing.JTextField txtNamaAnggota;
    private javax.swing.JTextField txtNoHp;
    // End of variables declaration//GEN-END:variables
}
