/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujikom.geekstudio.latihan.Form;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Yasin Dwi Ramandhita
 */
public class Peminjaman extends javax.swing.JDialog {
    Connection koneksi;
    ResultSet rss;
    Statement stm;
    Boolean ada = false;
    Boolean edit = false;
    Boolean tabel = true;
    int n, row = 0;
    int y = 1;
    //
    //validasi
    public void validasi(){
        try {
            stm = koneksi.createStatement();
            String SQL = "SELECT userId,userHak FROM hak_akses";
            rss = stm.executeQuery(SQL);
            if (rss.next()){
                String username = rss.getString("userId");
                String level = rss.getString("userHak");
                if ("admin".equalsIgnoreCase(level)){
                    lblUser.setText(username);
                    
                } else if("petugas".equalsIgnoreCase(level)){
                    btnHapus.setEnabled(false);
                    lblUser.setText(username);
                } }
        } catch (Exception e) {
        }
    }
    //
    //koneksi database
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
    //jika jumlah buku 0 maka tidak bisa di pinjam
    public void jmlBuku(){
        try {
            stm = koneksi.createStatement();
            String SQL = "SELECT jmlBuku FROM tr_buku";
            rss = stm.executeQuery(SQL);
            if (rss.next()){
                String jumlahBuku = rss.getString("jmlBuku");
                String nol = "0";
                if (jumlahBuku == nol) {
                    JOptionPane.showMessageDialog(null, "Maaf Buku Habis");
                }
                } 
        } catch (Exception e) {
        }
    }
    //
    //hanya angka (yasin belom tau fungsinya untuk apa -___-
    public void HanyaAngka(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))
                )
                )
            evt.consume();
    }
    //
    //buat no peminjaman
    public void buatNoObat(){
        try {
            stm = koneksi.createStatement();
            String sql = "select kdPinjam from tm_peminjaman order by kdPinjam desc";
            rss = stm.executeQuery(sql);
            if (rss.next()){
                String ambilNo = rss.getString("kdPinjam");
                String sub = ambilNo.substring(5, 10);
                int counter = Integer.parseInt(sub) + 1;
                txtKdPinjam.setText("PNJM00000" + Integer.toString(counter));
            }else{
                txtKdPinjam.setText("PNJM000001");
            }
            rss.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Buat Kode Obat !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Buat Kode Obat !", "Informasi", 1);
        }
    }
    //
    //data detail
    public void dataDetail(int table){
        try {
            if (tabel == true){
            String a = tabelPeminjaman.getValueAt(table, 0).toString();
            String b = tabelPeminjaman.getValueAt(table, 1).toString();
            String c = tabelPeminjaman.getValueAt(table, 2).toString();
            String d = tabelPeminjaman.getValueAt(table, 3).toString();
            String e = tabelPeminjaman.getValueAt(table, 4).toString();
            String f = tabelPeminjaman.getValueAt(table, 5).toString();
            String g = tabelPeminjaman.getValueAt(table, 6).toString();
            String h = tabelPeminjaman.getValueAt(table, 7).toString();
            
            txtKdPinjam.setText(a);
            txtTanggalPinjam.setText(b);
            cdAnggota.setSelectedItem(c);
            cdBuku.setSelectedItem(e);
            txtTanggalKembali.setText(f);
            txtStatus.setText(g);
            txtJumlahBuku.setText(h);
            } else {
                JOptionPane.showMessageDialog(this, "Tekan Batal terlebih dahulu !");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tekan Batal terlebih dahulu !");
        }
    }
    //
    //tampil data
    public void tampildata(){
        DefaultTableModel yasin = new DefaultTableModel();
        yasin.addColumn("Kode Pinjam");
        yasin.addColumn("Tanggal Pinjam");
        yasin.addColumn("Kode Anggota");
        yasin.addColumn("Kode Buku");
        yasin.addColumn("Tanggal Kembali");
        yasin.addColumn("Status");
        yasin.addColumn("User ID");
        yasin.addColumn("Jumlah Buku");
        
        try {
            stm = koneksi.createStatement();
            String sql = "select * from tm_peminjaman";
            rss = stm.executeQuery(sql);
            while (rss.next()){
                yasin.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getString(4),
                    rss.getString(5),
                    rss.getString(6),
                    rss.getString(7),
                    rss.getString(8),
                    });
            }
            tabelPeminjaman.setModel(yasin);
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(this,e);
           
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Data !", "Informasi", 1);
        }       
    }
    //
    //set edit hidup mati
public void SetEditMati(){
       txtKdPinjam.setEnabled(false); 
       cdAnggota.setEnabled(false); 
       cdBuku.setEnabled(false);  
       txtAnggota.setEnabled(false); 
       txtBuku.setEnabled(false); 
       txtTanggalKembali.setEnabled(false); 
       txtTanggalPinjam.setEnabled(false);
       txtStatus.setEnabled(false);
       txtJumlahBuku.setEnabled(false);
    }
    
    public void SetEditHidup(){
       txtKdPinjam.setEnabled(false); 
       cdAnggota.setEnabled(true); 
       cdBuku.setEnabled(true);  
       txtAnggota.setEnabled(true); 
       txtBuku.setEnabled(true); 
       txtTanggalKembali.setEnabled(true); 
       txtTanggalPinjam.setEnabled(true);
       txtStatus.setEnabled(true);
       txtJumlahBuku.setEnabled(true);
    }
    //
    //bersih
    public void Bersih(){
        txtKdPinjam.setText("");
        cdAnggota.setSelectedIndex(0);
        cdBuku.setSelectedIndex(0);
        txtAnggota.setText("");
        txtBuku.setText("");
        txtTanggalKembali.setText("");
        txtTanggalPinjam.setText("");
        txtStatus.setText("");
        txtJumlahBuku.setText("");
    }
    //
    //button enable
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
    //tampil kode anggota
    public void tampil_kode_anggota(){
        try {
            stm = koneksi.createStatement();
            String sql = "select kdAnggota from tm_anggota";
            Statement stat = koneksi.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()){
                cdAnggota.addItem(res.getString("kdAnggota"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Tampil Kode Tindakan !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Kode Tindakan !", "Informasi", 1);
        }
    }
    //
    //tampil kode buku
    public void tampil_kode_buku(){
        try {
            String a = "0"; 
            stm = koneksi.createStatement();
            String sql = "select kdBuku from tr_buku where jmlBuku";
            Statement stat = koneksi.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()){
                cdBuku.addItem(res.getString("kdBuku"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Tampil Kode Obat !", "Informasi", 1);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Tampil Kode Obat !", "Informasi", 1);
        }
    }
    //
    //tanggal pinjam
    public void waktu(){
        java.util.Date yasin = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        txtTanggalPinjam.setText(kal.format(yasin));
    }
    //
    //tanggal kembali
    public void waktuKembali(){
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nm_bulan = "";
                String nm_hari = "";
                String nol_hari = "";
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                Date dt = new Date();
                int nilai_tahun = dt.getYear()+1900;
                int nilai_bulan = dt.getMonth()+1;
                int nilai_hari = dt.getDate()+3;
                int nilai_hari1 = dt.getDay();
                int nilai_jam = dt.getHours();
                int nilai_menit = dt.getMinutes();
                int nilai_detik = dt.getSeconds();
                
                if(nilai_hari1 == 1){nm_hari = "Senin";}
                else if(nilai_hari1 == 2){nm_hari = "Selasa";}
                else if(nilai_hari1 == 3){nm_hari = "Rabu";}
                else if(nilai_hari1 == 4){nm_hari = "Kamis";}
                else if(nilai_hari1 == 5){nm_hari = "Jum'at";}
                else if(nilai_hari1 == 6){nm_hari = "Sabtu";}
                else if(nilai_hari1 == 7){nm_hari = "Minggu";}
                
                if(nilai_bulan == 1){nm_bulan = "01";}
                else if(nilai_bulan == 2){nm_bulan = "02";}
                else if(nilai_bulan == 3){nm_bulan = "03";}
                else if(nilai_bulan == 4){nm_bulan = "04";}
                else if(nilai_bulan == 5){nm_bulan = "05";}
                else if(nilai_bulan == 6){nm_bulan = "06";}
                else if(nilai_bulan == 7){nm_bulan = "07";}
                else if(nilai_bulan == 8){nm_bulan = "08";}
                else if(nilai_bulan == 9){nm_bulan = "09";}
                else if(nilai_bulan == 10){nm_bulan = "10";}
                else if(nilai_bulan == 11){nm_bulan = "11";}
                else if(nilai_bulan == 2){nm_bulan = "12";}
                if(nilai_hari <= 9){nol_hari = "0";}
                if(nilai_jam <= 9){nol_jam = "0";}
                if(nilai_menit <= 9){nol_menit = "0";}
                if(nilai_detik <= 9){nol_detik = "0";}
                
                String bulan = nm_bulan;
                String hari1 = nm_hari;
                String hari = nol_hari +Integer.toString(nilai_hari);
                String jam = nol_jam +Integer.toString(nilai_jam);
                String menit = nol_menit +Integer.toString(nilai_menit);
                String detik = nol_detik +Integer.toString(nilai_detik);
                //txtTanggalPinjam.setText(hari1+" , " +hari+ " " +bulan+ " " +nilai_tahun);
                txtTanggalKembali.setText(nilai_tahun+"-"+bulan+"-"+hari);
            }
        };
        new Timer(1000, taskPerformer).start();
    }
    //
    
    //
    public Peminjaman(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        SetEditMati();
        buka_database();
        tampil_kode_buku();
        tampil_kode_anggota();
        tampildata();
        waktu();
        waktuKembali();
        validasi();
        jmlBuku();
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
        jLabel10 = new javax.swing.JLabel();
        txtKdPinjam = new javax.swing.JTextField();
        txtTanggalPinjam = new javax.swing.JTextField();
        cdAnggota = new javax.swing.JComboBox();
        txtAnggota = new javax.swing.JTextField();
        cdBuku = new javax.swing.JComboBox();
        txtBuku = new javax.swing.JTextField();
        txtTanggalKembali = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        txtJumlahBuku = new javax.swing.JTextField();
        lblUser = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbCari = new javax.swing.JComboBox();
        txtCariPinjam = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPeminjaman = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Daftar Peminjaman Ceria Library");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "| Input Peminjaman Buku |", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Pinjam");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal Pinjam");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Anggota");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama Anggota");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kode Buku");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Judul Buku");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal Kembali");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Status");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jumlah Buku");

        cdAnggota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...PILIH..." }));
        cdAnggota.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cdAnggotaItemStateChanged(evt);
            }
        });

        cdBuku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...PILIH..." }));
        cdBuku.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cdBukuItemStateChanged(evt);
            }
        });

        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText("jLabel12");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKdPinjam)
                            .addComponent(txtTanggalPinjam)
                            .addComponent(cdAnggota, 0, 210, Short.MAX_VALUE)
                            .addComponent(txtAnggota)
                            .addComponent(cdBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBuku)
                            .addComponent(txtTanggalKembali)
                            .addComponent(txtStatus)
                            .addComponent(txtJumlahBuku)))
                    .addComponent(lblUser))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtKdPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addComponent(cdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtJumlahBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUser)
                .addContainerGap())
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
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
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

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Kata Kunci");

        cbCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Kode Pinjam", "Judul Buku" }));

        btnCari.setText("CARI");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbCari, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCariPinjam, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCari)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCariPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCari)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPeminjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPeminjaman);

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
                    .addComponent(jScrollPane1))
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        tabelPeminjaman.setEnabled(false);
    Bersih();
    buatNoObat();
    SetTombolHidup();
    SetEditHidup();
    tabelPeminjaman.isEnabled();
    edit = false;
    //cbUser.setEnabled(false);
    //tampil_kode_user();
    tabel = false;
    //statusSet();
    waktu();
    validasi();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void cdAnggotaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cdAnggotaItemStateChanged
        // TODO add your handling code here:
        String key = cdAnggota.getSelectedItem().toString();
        if (key.equalsIgnoreCase("...PILIH...")){
            txtAnggota.setText("");

        } else {
            try{
                stm = koneksi.createStatement();
                String KdAnggota = cdAnggota.getSelectedItem().toString();
                String sql = "select*from tm_anggota where kdAnggota='" + KdAnggota + "'";
                rss = stm.executeQuery(sql);
                if (rss.next()){
                    txtAnggota.setText(rss.getString("nmAnggota"));

                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam SQL statement !");
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam kode program !");
            }
        }
    }//GEN-LAST:event_cdAnggotaItemStateChanged

    private void cdBukuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cdBukuItemStateChanged
        // TODO add your handling code here:
        String key = cdBuku.getSelectedItem().toString();
        if (key.equalsIgnoreCase("-pilih-")){
            txtBuku.setText("");
        } else {
            try{
                stm = koneksi.createStatement();
                String KdAnggota = cdBuku.getSelectedItem().toString();
                String sql = "select*from tr_buku where kdBuku='" + KdAnggota + "'";
                rss = stm.executeQuery(sql);
                if (rss.next()){
                    txtBuku.setText(rss.getString("judulBuku"));
                    jmlBuku();
                    txtStatus.setText("pinjam");
                    txtJumlahBuku.setText("1");

                }
                //jmlBuku();
            } catch (SQLException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam SQL statement !");
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam kode program !");
            }
        }
    }//GEN-LAST:event_cdBukuItemStateChanged

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        SetEditMati();
    SetTombolMati();
    Bersih();
    tabelPeminjaman.setEnabled(true);
    tabel = true;
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
        String SQL;
        stm = koneksi.createStatement();
        if (edit == false){
            SQL = "insert into tm_peminjaman values ('" + txtKdPinjam.getText() + "','"
                    + txtTanggalPinjam.getText() + "','"
                    + cdAnggota.getSelectedItem() + "','"
                    + cdBuku.getSelectedItem() + "','"
                    + txtTanggalKembali.getText() + "','"
                    + txtStatus.getText() + "','"
                    + lblUser.getText() + "','"
                    + txtJumlahBuku.getText() + "')";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Data dengan Kode Pinjam " + txtKdPinjam.getText() + " Berhasil di Simpan !");
            
        }else{
            SQL = "update tm_peminjaman set "
                    + "kdPinjam='" + txtKdPinjam.getText() + "',"
                    + "tglPinjam='" + txtTanggalPinjam.getText() + "',"
                    + "kdAnggota='" + cdAnggota.getSelectedItem() + "',"
                    + "kdBuku='" + cdBuku.getSelectedItem() + "',"
                    + "tglKembali='" + txtTanggalKembali.getText() + "',"
                    + "status='" + txtStatus.getText() + "',"
                    + "userId='" + lblUser.getText() + "',"
                    + "jmlBuku='" + txtJumlahBuku.getText() + "',"
                    + "where kdPinjam='" + txtKdPinjam.getText() + "'";
            stm.executeUpdate(SQL);
            JOptionPane.showMessageDialog(this, "Data dengan Kode RM " + txtKdPinjam.getText() + " berhasil diperbaharui !");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Simpan/Update !", "Informasi", 1);
    } catch (Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Simpan/Update !", "Informasi", 1);
    }
    
    tampildata();
    Bersih();
    SetTombolMati();
    SetEditMati();
    tabelPeminjaman.setEnabled(true);
    tabel = true;
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (txtKdPinjam.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Pilih Data dari tabel terlebih dahulu !"
                + "\nKlik pada tabel atau gunakan navigasi !", "Informasi", 1);
    }else{
        SetTombolHidup();
        edit = true;
        SetEditHidup();
        tabelPeminjaman.setEnabled(true);
        tabel = false;
    }                   
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if (txtKdPinjam.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Pilih Data dari tabel terlebih dahulu !"
                + "\nKlik pada tabel atau gunakan navigasi !", "Informasi", 1);
    }else{
        if (JOptionPane.showConfirmDialog(null,
                "Yakin Data dengan No RM " + txtKdPinjam.getText() + " akan dihapus ?", "Informasi", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION){
            try {
                stm = koneksi.createStatement();
                String sql = "delete from tm_peminjaman where kdPinjam='" + txtKdPinjam.getText() + "'";
                stm.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus !");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam penulisan SQL statement Hapus !", "Informasi", 1);
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, "Ada kesalahan dalam Kode Program Hapus !", "Informasi", 1);
            }
        }
    }                    
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Anda yakin Akan Keluar ?\nTekan OK untuk keluar !\nTekan Cancel untuk membatalkan !",
            "Konfirmasi", JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE)
            == JOptionPane.OK_OPTION){
        this.dispose();
    }
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void tabelPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPeminjamanMouseClicked
        // TODO add your handling code here:
        try {
        if (tabel == true){
            int table = tabelPeminjaman.getSelectedRow();
            String a = tabelPeminjaman.getValueAt(table, 0).toString();
            String b = tabelPeminjaman.getValueAt(table, 1).toString();
            String c = tabelPeminjaman.getValueAt(table, 2).toString();
            String d = tabelPeminjaman.getValueAt(table, 3).toString();
            String e = tabelPeminjaman.getValueAt(table, 4).toString();
            String f = tabelPeminjaman.getValueAt(table, 5).toString();
            String g = tabelPeminjaman.getValueAt(table, 6).toString();
            String h = tabelPeminjaman.getValueAt(table, 7).toString();
            
            txtKdPinjam.setText(a);
            txtTanggalPinjam.setText(b);
            cdAnggota.setSelectedItem(c);
            cdBuku.setSelectedItem(d);
            txtTanggalKembali.setText(e);
            txtStatus.setText(f);
            txtJumlahBuku.setText(h);
        }else {
            JOptionPane.showMessageDialog(this, "Tekan BATAL terlebih dahulu !", "Informasi", 1);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Tekan BATAL terlebih dahulu !", "Informasi", 1);
    }
    }//GEN-LAST:event_tabelPeminjamanMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        if (txtCariPinjam.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Silakan input kata kunci pencarian,"
                + "\nTentukan kriteria pencariannya !");
        txtCariPinjam.requestFocus();
    }else{
        try {
            DefaultTableModel yasin = new DefaultTableModel();
            yasin.addColumn("Kode Pinjam");
            yasin.addColumn("Tanggal Pinjam");
            yasin.addColumn("Kode Anggota");
            yasin.addColumn("Kode Buku");
            yasin.addColumn("Tanggal Kembali");
            yasin.addColumn("Status");
            yasin.addColumn("User ID");
            yasin.addColumn("Jumlah Buku");
            
            String sql;
            stm = koneksi.createStatement();
            String key = cbCari.getSelectedItem().toString();
            if (key.equalsIgnoreCase("Kode Pinjam")){
                sql = "select * from tm_peminjaman where kdPinjam like '%" + txtCariPinjam.getText() + "%'";
            }else if (key.equalsIgnoreCase("Kode Buku")){
                sql = "select * from tm_peminjaman where kdBuku like '%" + txtCariPinjam.getText() + "%'";
            }else{
                sql = "select * from tm_peminjaman order by kdPinjam asc";
                tampildata();
            }
            rss = stm.executeQuery(sql);
            
            while (rss.next()){
                yasin.addRow(new Object[]{
                    rss.getString(1),
                    rss.getString(2),
                    rss.getString(3),
                    rss.getString(4),
                    rss.getString(5),
                    rss.getString(6),
                    rss.getString(7),
                    rss.getString(8),
                    });
            }
            tabelPeminjaman.setModel(yasin);
            txtCariPinjam.setText("");
            
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
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Peminjaman dialog = new Peminjaman(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cbCari;
    private javax.swing.JComboBox cdAnggota;
    private javax.swing.JComboBox cdBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel lblUser;
    private javax.swing.JTable tabelPeminjaman;
    private javax.swing.JTextField txtAnggota;
    private javax.swing.JTextField txtBuku;
    private javax.swing.JTextField txtCariPinjam;
    private javax.swing.JTextField txtJumlahBuku;
    private javax.swing.JTextField txtKdPinjam;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    // End of variables declaration//GEN-END:variables
}
