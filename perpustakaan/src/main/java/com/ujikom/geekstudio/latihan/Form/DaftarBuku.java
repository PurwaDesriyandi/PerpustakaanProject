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
 * @author Yasin Dwi Ramandhita
 */
public class DaftarBuku extends javax.swing.JDialog {
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
            String sql = "select kdBuku from tr_buku order by kdBuku desc";
            rss = stm.executeQuery(sql);
            if (rss.next()){
                String ambilNo = rss.getString("kdBuku");
                String sub = ambilNo.substring(5, 10);
                int counter = Integer.parseInt(sub) + 1;
                txtKdBuku.setText("BUKU00000" + Integer.toString(counter));
            }else{
                txtKdBuku.setText("BUKU000001");
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
       txtKdBuku.setEnabled(false); 
       cbKategori.setEnabled(false);
       txtKategori.setEnabled(false);
       cbPenerbit.setEnabled(false);
       txtPenerbit.setEnabled(false);
       cbPengarang.setEnabled(false);
       txtPengarang.setEnabled(false);
       txtJudulBuku.setEnabled(false);  
       txtSinopsis.setEnabled(false); 
       txtJumlahBuku.setEnabled(false); 
       txtGambarBuku.setEnabled(false); 
    }
    //
    //edit hidup
    public void SetEditHidup(){
       txtKdBuku.setEnabled(false); 
       cbKategori.setEnabled(true); 
       cbPenerbit.setEnabled(true); 
       cbPengarang.setEnabled(true);  
       txtJudulBuku.setEnabled(true);  
       txtSinopsis.setEnabled(true); 
       txtJumlahBuku.setEnabled(true); 
       txtGambarBuku.setEnabled(true); 
       txtKategori.setEnabled(false);
       txtPenerbit.setEnabled(false);
       txtPengarang.setEnabled(false);
    }
    //
    //bersih
    public void Bersih(){
        txtKdBuku.setText("");
        cbKategori.setSelectedIndex(0);
        cbPenerbit.setSelectedIndex(0);
        cbPengarang.setSelectedIndex(0);
        txtKategori.setText("");
        txtPenerbit.setText("");
        txtPengarang.setText("");
        txtJudulBuku.setText("");
        txtSinopsis.setText("");
        txtJumlahBuku.setText("");
        txtGambarBuku.setText("");
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
    //tampil kode kategori
    public void tampilKodeKategori(){
        try {
            stm = koneksi.createStatement();
            String sql = "select kdKategori from tr_kategori";
            Statement stat = koneksi.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()){
                cbKategori.addItem(res.getString("kdKategori"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Tampil Kode Tindakan !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Kode Tindakan !", "Informasi", 1);
        }
    }
    //
    //tampil kode penerbit
    public void tampilKodePenerbit(){
        try {
            stm = koneksi.createStatement();
            String sql = "select kdPenerbit from tr_penerbit";
            Statement stat = koneksi.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()){
                cbPenerbit.addItem(res.getString("kdPenerbit"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Tampil Kode Tindakan !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Kode Tindakan !", "Informasi", 1);
        }
    }
    //
    //tampil kode pengarang
    public void tampilKodePengarang(){
        try {
            stm = koneksi.createStatement();
            String sql = "select kdPengarang from tr_pengarang";
            Statement stat = koneksi.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()){
                cbPengarang.addItem(res.getString("kdPengarang"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Tampil Kode Tindakan !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Kode Tindakan !", "Informasi", 1);
        }
    }
    //
    //tampilData
    public void tampildata(){
        DefaultTableModel yasin = new DefaultTableModel();
        yasin.addColumn("Kode Buku");
        yasin.addColumn("Judul Buku");
        yasin.addColumn("Jumlah Buku");
        try {
            stm = koneksi.createStatement();
            String sql ="SELECT kdBuku, judulBuku, jmlBuku FROM tr_buku";
            rss = stm.executeQuery(sql);
            while (rss.next()){
                yasin.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    });
            }
            tabelDaftarBuku.setModel(yasin);
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(this,e);
           
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Data !", "Informasi", 1);
        }       
    }
    //
    
    public DaftarBuku(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        SetEditMati();
        buka_database();
        tampilKodeKategori();
        tampilKodePenerbit();
        tampilKodePengarang();
        tampildata();
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtKdBuku = new javax.swing.JTextField();
        cbKategori = new javax.swing.JComboBox();
        txtKategori = new javax.swing.JTextField();
        cbPenerbit = new javax.swing.JComboBox();
        txtPenerbit = new javax.swing.JTextField();
        cbPengarang = new javax.swing.JComboBox();
        txtPengarang = new javax.swing.JTextField();
        txtJudulBuku = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSinopsis = new javax.swing.JTextArea();
        txtJumlahBuku = new javax.swing.JTextField();
        txtGambarBuku = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtCariBuk = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelDaftarBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Daftar Buku Ceria Library");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "| Input Data Buku |", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Buku");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kode Penerbit");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Pengarang");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode Kategori");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Judul Buku");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Sinopsis");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jumlah Buku");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gambar Buku");

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...PILIH..." }));
        cbKategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKategoriItemStateChanged(evt);
            }
        });

        cbPenerbit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...PILIH..." }));
        cbPenerbit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPenerbitItemStateChanged(evt);
            }
        });

        cbPengarang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...PILIH..." }));
        cbPengarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPengarangItemStateChanged(evt);
            }
        });

        txtSinopsis.setColumns(20);
        txtSinopsis.setRows(5);
        jScrollPane1.setViewportView(txtSinopsis);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbKategori, 0, 140, Short.MAX_VALUE)
                            .addComponent(cbPenerbit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPengarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKategori)
                            .addComponent(txtPenerbit)
                            .addComponent(txtPengarang, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addComponent(txtKdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtGambarBuku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addComponent(txtJumlahBuku, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtJumlahBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGambarBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKeluar)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTambah)
                .addGap(18, 18, 18)
                .addComponent(btnBatal)
                .addGap(18, 18, 18)
                .addComponent(btnSimpan)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnHapus)
                .addGap(18, 18, 18)
                .addComponent(btnKeluar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "| Pencarian |", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Kode/Judul Buku");

        btnCari.setText("CARI");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Kode Buku", "Judul Buku" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCariBuk)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCari))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCariBuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnCari)
                .addContainerGap())
        );

        tabelDaftarBuku.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelDaftarBuku);

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        tabelDaftarBuku.setEnabled(false);
        Bersih();
        buatNoBuku();
        SetTombolHidup();
        SetEditHidup();
        tabelDaftarBuku.isEnabled();
        edit = false;
        //cbUser.setEnabled(false);
        //tampil_kode_user();
        tabel = false;
    }//GEN-LAST:event_btnTambahActionPerformed

    private void cbKategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbKategoriItemStateChanged
        // TODO add your handling code here:
        String key = cbKategori.getSelectedItem().toString();
        if (key.equalsIgnoreCase("...PILIH...")){
            txtKategori.setText("");

        } else {
            try{
                stm = koneksi.createStatement();
                String KdKategori = cbKategori.getSelectedItem().toString();
                String sql = "select*from tr_kategori where kdKategori='" + KdKategori + "'";
                rss = stm.executeQuery(sql);
                if (rss.next()){
                    txtKategori.setText(rss.getString("nmKategori"));

                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam SQL statement !");
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam kode program !");
            }
        }
    }//GEN-LAST:event_cbKategoriItemStateChanged

    private void cbPenerbitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPenerbitItemStateChanged
        // TODO add your handling code here:
        String key = cbPenerbit.getSelectedItem().toString();
        if (key.equalsIgnoreCase("...PILIH...")){
            txtPenerbit.setText("");

        } else {
            try{
                stm = koneksi.createStatement();
                String KdKategori = cbPenerbit.getSelectedItem().toString();
                String sql = "select*from tr_penerbit where kdPenerbit='" + KdKategori + "'";
                rss = stm.executeQuery(sql);
                if (rss.next()){
                    txtPenerbit.setText(rss.getString("nmPenerbit"));

                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam SQL statement !");
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam kode program !");
            }
        }
    }//GEN-LAST:event_cbPenerbitItemStateChanged

    private void cbPengarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPengarangItemStateChanged
        // TODO add your handling code here:
        String key = cbPengarang.getSelectedItem().toString();
        if (key.equalsIgnoreCase("...PILIH...")){
            txtPengarang.setText("");

        } else {
            try{
                stm = koneksi.createStatement();
                String KdKategori = cbPengarang.getSelectedItem().toString();
                String sql = "select*from tr_pengarang where kdPengarang='" + KdKategori + "'";
                rss = stm.executeQuery(sql);
                if (rss.next()){
                    txtPengarang.setText(rss.getString("nmPengarang"));

                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam SQL statement !");
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam kode program !");
            }
        }
    }//GEN-LAST:event_cbPengarangItemStateChanged

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        tabelDaftarBuku.setEnabled(false);
        Bersih();
        buatNoBuku();
        SetTombolHidup();
        SetEditHidup();
        tabelDaftarBuku.isEnabled();
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
            SQL = "insert into tr_buku values ('" + txtKdBuku.getText() + "','"
                    + cbKategori.getSelectedItem() + "','"
                    + cbPenerbit.getSelectedItem() + "','"
                    + cbPengarang.getSelectedItem() + "','"
                    + txtJudulBuku.getText() + "','"
                    + txtSinopsis.getText() + "','"
                    + txtJumlahBuku.getText() + "','"
                    + txtGambarBuku.getText() + "')";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Data dengan Kode Buku " + txtKdBuku.getText() + " Berhasil di Simpan !");
            
        }else{
            SQL = "update tr_buku set "
                    + "kdBuku='" + txtKdBuku.getText() + "',"
                    + "kdKategori='" + cbKategori.getSelectedItem() + "',"
                    + "kdPenerbit='" + cbPenerbit.getSelectedItem() + "',"
                    + "kdPengarang='" + cbPengarang.getSelectedItem() + "',"
                    + "judulBuku='" + txtJudulBuku.getText()+ "',"
                    + "sinopsis='" + txtSinopsis.getText() + "',"
                    + "where kdBuku='" + txtKdBuku.getText() + "'";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Data dengan Kode Buku " + txtKdBuku.getText() + " berhasil diperbaharui !");
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
    tabelDaftarBuku.setEnabled(true);
    tabel = true;
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (txtKdBuku.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Pilih Data dari tabel terlebih dahulu !"
                + "\nKlik pada tabel atau gunakan navigasi !", "Informasi", 1);
    }else{
        SetTombolHidup();
        edit = true;
        SetEditHidup();
        tabelDaftarBuku.setEnabled(true);
        tabel = false;
    }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if (txtKdBuku.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Pilih Data dari tabel terlebih dahulu !"
                + "\nKlik pada tabel atau gunakan navigasi !", "Informasi", 1);
    }else{
        if (JOptionPane.showConfirmDialog(null,
                "Yakin Data dengan No Buku " + txtKdBuku.getText() + " akan dihapus ?", "Informasi", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION){
            try {
                stm = koneksi.createStatement();
                String sql = "delete from tr_buku where kdBuku='" + txtKdBuku.getText() + "'";
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
    tabelDaftarBuku.setEnabled(true);
    tabel = true;
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Anda yakin Akan Keluar ?\nTekan OK untuk keluar !\nTekan Cancel untuk membatalkan !",
            "Konfirmasi", JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE)
            == JOptionPane.OK_OPTION){
        this.dispose();}
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        if (txtCariBuk.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Silakan input kata kunci pencarian,"
                + "\nTentukan kriteria pencariannya !");
        txtCariBuk.requestFocus();
    }else{
        try {
            DefaultTableModel yasin = new DefaultTableModel();
            yasin.addColumn("Kode Buku");
            yasin.addColumn("Judul Buku");
            yasin.addColumn("Jumlah Buku");
            
            String sql;
            stm = koneksi.createStatement();
            String key = jComboBox1.getSelectedItem().toString();
            if (key.equalsIgnoreCase("Kode Buku")){
                sql = "select kdBuku, judulBuku, jmlBuku from tr_buku where kdBuku like '%" + txtCariBuk.getText() + "%'";
            }else if (key.equalsIgnoreCase("Judul Buku")){
                sql = "select kdBuku, judulBuku, jmlBuku from tr_buku where judulBuku like '%" + txtCariBuk.getText() + "%'";
            }else{
                sql = "select kdBuku, judulBuku, jmlBuku from tr_buku order by kdBuku asc";
                tampildata();
            }
            rss = stm.executeQuery(sql);
            
            while (rss.next()){
                yasin.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    });
            }
            tabelDaftarBuku.setModel(yasin);
            txtCariBuk.setText("");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Cari !", "Informasi", 1);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Cari !", "Informasi", 1);
        }
    }
    }//GEN-LAST:event_btnCariActionPerformed

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
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DaftarBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DaftarBuku dialog = new DaftarBuku(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox cbKategori;
    private javax.swing.JComboBox cbPenerbit;
    private javax.swing.JComboBox cbPengarang;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTable tabelDaftarBuku;
    private javax.swing.JTextField txtCariBuk;
    private javax.swing.JTextField txtGambarBuku;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtJumlahBuku;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtKdBuku;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextArea txtSinopsis;
    // End of variables declaration//GEN-END:variables
}
