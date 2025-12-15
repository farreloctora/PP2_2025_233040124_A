/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10;

/**
 *
 * @author Hype AMD
 */
import id.ac.unpas.modul10.model.KoneksiDB;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class MahasiswaApp extends JFrame {
    JTextField txtNama, txtNIM, txtJurusan, txtCari ;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JTable tableMahasiswa;
    DefaultTableModel model;

    public MahasiswaApp() {
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Form
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);
        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        // Panel Pencarian
        JPanel panelCari = new JPanel(new FlowLayout());
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari Nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        
        JPanel panelUtara = new JPanel();
        panelUtara.setLayout(new BoxLayout(panelUtara, BoxLayout.Y_AXIS));
        panelUtara.add(panelAtas);  // panel form + tombol CRUD
        panelUtara.add(panelCari);  // panel pencarian
        add(panelUtara, BorderLayout.NORTH);

        // Tabel
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");
        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });
        
        // Event Cari
        btnCari.addActionListener(e -> {
            if (txtCari.getText().trim().isEmpty()) {
                loadData(); // tampilkan semua jika kosong
            } else {
                cariData(txtCari.getText());
            }
        });

        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());

        loadData();
    }

        private void loadData() {
            model.setRowCount(0);
            try {
                Connection conn = KoneksiDB.configDB();
                Statement stm = conn.createStatement();
                ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
                int no = 1;
                while (res.next()) {
                    model.addRow(new Object[]{
                        no++,
                        res.getString("nama"),
                        res.getString("nim"),
                        res.getString("jurusan")
                    });
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
            }
        }

        private void tambahData() {
            if (txtNama.getText().trim().isEmpty() || txtNIM.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data Nama dan NIM tidak boleh kosong!");
                return;
            }

            // Cek Duplikat NIM
            if (cekDuplikatNIM(txtNIM.getText())) {
                JOptionPane.showMessageDialog(this, "NIM sudah ada di database!");
                return;
            }

            try {
                String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, txtNama.getText());
                pst.setString(2, txtNIM.getText());
                pst.setString(3, txtJurusan.getText());
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
                loadData();
                kosongkanForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
            }
        }

        private void ubahData() {
            try {
                String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, txtNama.getText());
                pst.setString(2, txtJurusan.getText());
                pst.setString(3, txtNIM.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
                loadData();
                kosongkanForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
            }
        }

        private void hapusData() {
            try {
                String sql = "DELETE FROM mahasiswa WHERE nim = ?";
                Connection conn = KoneksiDB.configDB();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, txtNIM.getText());
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                loadData();
                kosongkanForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
            }
        }

        private void cariData(String keyword) {
        model.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet res = pst.executeQuery();
            int no = 1;
            while (res.next()) {
                model.addRow(new Object[] {
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mencari: " + e.getMessage());
        }
    }
        
        private boolean cekDuplikatNIM(String nim) {
            try {
                Connection conn = KoneksiDB.configDB();
                String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, nim);
                ResultSet res = pst.executeQuery();
                if (res.next() && res.getInt(1) > 0) {
                    return true; // NIM sudah ada
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false; // NIM belum ada
        }

        private void kosongkanForm() {
            txtNama.setText("");
            txtNIM.setText("");
            txtJurusan.setText("");
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
        }
}