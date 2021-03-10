/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siswa;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
/**
 *
 * @author Ndaru Pratiwi
 */

public class Siswa extends JFrame implements ActionListener {

    private JPanel jpSiswa = new JPanel();
    private JLabel lblNis = new JLabel("Nis:"), lblNama = new JLabel("Nama:"),
            lblPassword = new JLabel("Password:"), lblAlamat = new JLabel("Alamat:"),
            lblEmail = new JLabel("Email:"), lblJenisKelamin = new JLabel("JenisKelamin:");
    private JTextField txtNis = new JTextField(), txtNama = new JTextField(), txtPassword
            = new JTextField(), txtAlamat = new JTextField(), txtEmail = new JTextField();
    private JComboBox cboJenisKelamin = new JComboBox();
    private JButton btnTambah = new JButton("Tambah"), btnUbah = new JButton("Ubah"),
            btnHapus = new JButton("Hapus"), btnBersih = new JButton("Bersih");

    String[] strJdl = {"No", "Nis", "Nama", "Password", "Alamat", "Email", "Jenis Kelamin"};

//Deklarasi untuk Tabel
    JTable tabel = new JTable();
    JScrollPane skrTabel = new JScrollPane();

    Siswa() {
        super("Entri Data MHS");
        setSize(500, 300);
        jpSiswa.setLayout(null);

        //pengaturan Tabel
        skrTabel.getViewport().add(tabel);
        tabel.setEnabled(true);
        skrTabel.setBounds(15, 250, 470, 115);

        //menambahkan tabel pada panel
        jpSiswa.add(skrTabel);

        //menambahkan objek JPanel pada container frame
        getContentPane().add(jpSiswa);

        //menampilkan tabel siswa ke komponen tabel
        TampilTabel();

        //mengatur letak objek pada container
        lblNis.setBounds(15, 20, 100, 25);
        lblNama.setBounds(15, 55, 100, 25);
        lblPassword.setBounds(15, 90, 100, 25);
        lblAlamat.setBounds(15, 125, 100, 25);
        lblEmail.setBounds(15, 160, 100, 25);
        lblJenisKelamin.setBounds(15, 195, 100, 25);

        txtNis.setBounds(115, 20, 100, 25);
        txtNama.setBounds(115, 55, 100, 25);
        txtPassword.setBounds(115, 90, 100, 25);
        txtAlamat.setBounds(115, 125, 150, 25);
        txtEmail.setBounds(115, 160, 100, 25);
        cboJenisKelamin.setBounds(115, 195, 50, 25);

        btnTambah.setBounds(340, 20, 85, 25);
        btnUbah.setBounds(340, 55, 85, 25);
        btnHapus.setBounds(340, 90, 85, 25);
        btnBersih.setBounds(340, 125, 85, 25);

        //mengatur/meletakkan objek pada objek panel
        jpSiswa.add(lblNis);
        jpSiswa.add(lblNama);
        jpSiswa.add(lblPassword);
        jpSiswa.add(lblAlamat);
        jpSiswa.add(lblEmail);
        jpSiswa.add(lblJenisKelamin);

        jpSiswa.add(txtNis);
        jpSiswa.add(txtNama);
        jpSiswa.add(txtPassword);
        jpSiswa.add(txtAlamat);
        jpSiswa.add(txtEmail);
        jpSiswa.add(cboJenisKelamin);

        jpSiswa.add(btnTambah);
        jpSiswa.add(btnUbah);
        jpSiswa.add(btnHapus);
        jpSiswa.add(btnBersih);

        //mengisi combo Jenis kelamin
        cboJenisKelamin.addItem("L");
        cboJenisKelamin.addItem("P");

        //mengatur objek agar dapat berinteraksi dengan user
        btnTambah.addActionListener(this);
        btnUbah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnBersih.addActionListener(this);

        //menambahkan objek JPanel pada container frame
        getContentPane().add(jpSiswa);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        if (obj == btnTambah) {
            Tambah();
        }
        if (obj == btnUbah) {
            Ubah();
        }
        if (obj == btnHapus) {
            Hapus();
        }
        Bersih();
    }

    void Bersih() {
        txtNis.setText("");
        txtNama.setText("");
        txtPassword.setText("");
        txtAlamat.setText("");
        txtEmail.setText("");
        cboJenisKelamin.setSelectedIndex(0);

    }

    void Tambah() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siswa", "root", "");
            Statement st = con.createStatement();
            String sql = "INSERT INTO siswa('nis','nama','password','alamat','email','jeniskelamin') VALUES ('" + txtNis.getText() 
                    + "','" + txtNama.getText()
                    + "','" + txtPassword.getText() + "','" + txtAlamat.getText() + "',"
                    + "'" + txtEmail.getText() + "','" + cboJenisKelamin.getSelectedItem() + "')";
            int row = st.executeUpdate(sql);

            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah ditambahkan ke"
                        + " database", "infomasi", JOptionPane.INFORMATION_MESSAGE);

                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak ditambahkan ke"
                    + " database", "infomasi", JOptionPane.INFORMATION_MESSAGE);
        }
        TampilTabel();
    }

    void Ubah() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siswa", "root", "");
            Statement st = con.createStatement();

            String sql = "UPDATE siswa SET nama ='" + txtNama.getText() + "',"
                    + "password ='" + txtPassword.getText() + "',alamat = '" + txtAlamat.getText()
                    + "',email='" + txtEmail.getText() + "',jeniskelamin = '" + cboJenisKelamin.getSelectedItem()
                    + "' WHERE nis = '" + txtNis.getText() + "' ";

            int row = st.executeUpdate(sql);
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah di update ", "infomasi", JOptionPane.INFORMATION_MESSAGE);
                con.close();

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data sudah di update",
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
        TampilTabel();
    }

    void Hapus() {
        try {
            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siswa", "root", "");
            Statement st = con.createStatement();
            String sql = "DELETE FROM siswa WHERE nis = '" + txtNis.getText() + "' ";
            int row = st.executeUpdate(sql);
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Data sudah dihapus dari "
                        + " database", "infomasi", JOptionPane.INFORMATION_MESSAGE);

                con.close();

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak dihapus", "infomasi", JOptionPane.INFORMATION_MESSAGE);
        }
        TampilTabel();
    }

    void TampilTabel() {
        try {

            Koneksi ObjKoneksi = new Koneksi();
            Connection con = ObjKoneksi.bukaKoneksi();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siswa", "root", "");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM siswa";
            ResultSet set = st.executeQuery(sql);

            //menampilkan data ke Tabel
            ResultSetTableModel model = new ResultSetTableModel(set);
            tabel.setModel(model);

            while (set.next()) {
                txtNis.setText(Integer.toString(set.getInt("nis")));
                txtNama.setText(set.getString("nama"));
                txtPassword.setText(set.getString("password"));
                txtAlamat.setText(set.getString("alamat"));
                txtEmail.setText(set.getString("email"));
                cboJenisKelamin.addItem(set.getString("jeniskelamin"));
            }
        } catch (SQLException e) {
            System.out.println("gagal query");
        }
    }

    public static void main(String[] args) {
        new Siswa();
    }

    private static class Koneksi {

        public Koneksi() {
        }

        public Connection bukaKoneksi() throws SQLException {
            Connection con = null;

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/siswa", "root", "");
            return con;
        }

    }
}
