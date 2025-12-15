/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10.view;

/**
 *
 * @author Hype AMD
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaView extends JFrame {
    // Komponen GUI
    public JTextField txtNama, txtNIM, txtJurusan, txtCari;
    public JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    public JTable tableMahasiswa;
    public DefaultTableModel model;
    
    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa JDBC - MVC");
        setSize(700, 500);
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
        panelUtara.add(panelAtas);
        panelUtara.add(panelCari);
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
    }
    
    // Methods untuk mendapatkan input
    public String getNama() { return txtNama.getText(); }
    public String getNim() { return txtNIM.getText(); }
    public String getJurusan() { return txtJurusan.getText(); }
    public String getKeyword() { return txtCari.getText(); }
    
    // Methods untuk mengatur nilai
    public void setNama(String nama) { txtNama.setText(nama); }
    public void setNim(String nim) { txtNIM.setText(nim); }
    public void setJurusan(String jurusan) { txtJurusan.setText(jurusan); }
    
    // Method untuk mengosongkan form
    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        txtCari.setText("");
    }
    
    // Method untuk menampilkan data di tabel
    public void showDataInTable(java.util.List<id.ac.unpas.modul10.model.Mahasiswa> list) {
        model.setRowCount(0);
        int no = 1;
        for (id.ac.unpas.modul10.model.Mahasiswa mhs : list) {
            model.addRow(new Object[]{
                no++,
                mhs.getNama(),
                mhs.getNim(),
                mhs.getJurusan()
            });
        }
    }
    
    // Method untuk mendapatkan baris yang dipilih
    public int getSelectedRow() {
        return tableMahasiswa.getSelectedRow();
    }
}
