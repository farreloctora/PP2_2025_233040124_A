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

public class Tugas extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public Tugas() {
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
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10)); // Ubah ke 5 baris untuk tombol reset
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

        // Tombol Reset
        JButton btnReset = new JButton("Reset");
        panel.add(new JLabel("")); // Placeholder kosong agar tombol di kanan
        panel.add(btnReset);

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        // Event Handling Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNama.setText("");
                txtNilai.setText("");
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

        // Panel utama untuk tabel
        JPanel tablePanel = new JPanel(new BorderLayout());
        // tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Hilangkan border
        tablePanel.add(scrollPane, BorderLayout.CENTER); // Tambahkan scrollPane ke center

        // Tombol Hapus
        JButton btnHapus = new JButton("Hapus Baris Terpilih");
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableData.getSelectedRow();
                if (selectedRow >= 0) { // Jika ada baris yang dipilih
                    tableModel.removeRow(selectedRow);
                    // Perbarui nomor urut setelah penghapusan
                    perbaruiNomorUrut();
                } else {
                    JOptionPane.showMessageDialog(tablePanel, "Pilih baris yang ingin dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Panel untuk tombol, ditempatkan di SOUTH dari panel utama
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnHapus);

        // Gabungkan panel tabel dan panel tombol
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        return tablePanel;
    }

    // Method untuk memperbarui nomor urut setelah penghapusan atau penambahan
    private void perbaruiNomorUrut() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(i + 1, i, 0); // Kolom 0 adalah kolom No
        }
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

        // Validasi nama minimal 3 karakter
        if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama siswa minimal terdiri dari 3 karakter!", "Error", JOptionPane.ERROR_MESSAGE);
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

        // Hitung grade menggunakan switch-case
        String grade = hitungGrade(nilai);

        // Ambil nomor urut baris baru (sebelum ditambahkan)
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

    // Method untuk menghitung grade menggunakan switch-case
    private String hitungGrade(int nilai) {
        int indeksNilai = nilai / 10; // Misal: 85/10 = 8, 45/10 = 4
        switch (indeksNilai) {
            case 10:
            case 9:
            case 8:
                return "A";
            case 7:
                return "AB";
            case 6:
                return "B";
            case 5:
                return "BC";
            case 4:
                return "C";
            case 3:
                return "D";
            default: // Untuk nilai 0, 1, 2
                return "E";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Tugas().setVisible(true);
        });
    }
}