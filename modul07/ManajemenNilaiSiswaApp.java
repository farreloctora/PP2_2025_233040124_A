/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul07;

/**
 *
 * @author LazyBear
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManajemenNilaiSiswaApp extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public ManajemenNilaiSiswaApp() {
        setTitle("Manajemen Nilai Siswa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi JTabbedPane
        tabbedPane = new JTabbedPane();

        // Tambahkan panel input dan panel tabel ke tab
        tabbedPane.addTab("Input Data", createInputPanel());
        tabbedPane.addTab("Daftar Nilai", createTablePanel());

        // Tambahkan tabbedPane ke frame utama
        add(tabbedPane);
    }

    // Method untuk membuat desain Tab Input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Kuliah (ComboBox)
        panel.add(new JLabel("Mata Kuliah:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia", "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(new JLabel("")); // Placeholder kosong agar tombol di kanan
        panel.add(btnSimpan);

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        return panel;
    }

    // Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        // Inisialisasi model tabel
        String[] columnNames = {"No", "Nama Siswa", "Mata Kuliah", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat tabel tidak bisa diedit
            }
        };

        tableData = new JTable(tableModel);

        // Scroll pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(tableData);

        // Buat panel tanpa border dan layout yang mengisi semua ruang
        JPanel panel = new JPanel(new BorderLayout());
        // panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Hilangkan baris ini
        panel.add(scrollPane, BorderLayout.CENTER); // Tambahkan scrollPane ke center agar mengisi semua ruang

        return panel;
    }

    // Method untuk memproses penyimpanan data
    private void prosesSimpan() {
        String nama = txtNama.getText().trim();
        String strNilai = txtNilai.getText().trim();
        String matkul = (String) cmbMatkul.getSelectedItem();

        // Validasi nama tidak boleh kosong
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama siswa tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validasi nilai harus berupa angka
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validasi nilai dalam rentang 0-100
        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this, "Nilai harus berada dalam rentang 0-100!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hitung grade
        String grade;
        if (nilai >= 80) {
            grade = "A";
        } else if (nilai >= 70) {
            grade = "AB";
        } else if (nilai >= 60) {
            grade = "B";
        } else if (nilai >= 50) {
            grade = "BC";
        } else if (nilai >= 40) {
            grade = "C";
        } else if (nilai >= 30) {
            grade = "D";
        } else {
            grade = "E";
        }

        // Ambil nomor urut baris baru
        int no = tableModel.getRowCount() + 1;

        // Tambahkan data ke model tabel
        Object[] rowData = {no, nama, matkul, nilai, grade};
        tableModel.addRow(rowData);

        // Beri pesan sukses
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Info", JOptionPane.INFORMATION_MESSAGE);

        // Kosongkan input setelah disimpan
        txtNama.setText("");
        txtNilai.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}