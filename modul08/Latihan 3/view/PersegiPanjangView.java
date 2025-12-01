/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasil = new JLabel("-");
    private JButton btnHitung = new JButton("Hitung Luas");
    private JLabel lblKeliling = new JLabel("-");
    private JButton btnKeliling = new JButton("Hitung Keliling");
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(7, 2, 10, 10)); // Grid 7 baris x 2 kolom
        this.setTitle("MVC Kalkulator");

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);

        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);

        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasil);

        this.add(new JLabel("")); // Spacer kosong untuk tombol Hitung Luas
        this.add(btnHitung);

        this.add(new JLabel("Keliling:"));
        this.add(lblKeliling);

        this.add(new JLabel("")); // Spacer kosong untuk tombol Hitung Keliling
        this.add(btnKeliling);  // tombol keliling

        this.add(new JLabel("")); // Spacer kosong untuk tombol Reset
        this.add(btnReset); // tombol reset

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    // Mengambil nilai panjang dari TextField
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai lebar dari TextField
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Menampilkan hasil ke Label
    public void setHasil(double hasil) {
        lblHasil.setText(String.valueOf(hasil));
    }
    
    public void setKeliling(double keliling) {
        lblKeliling.setText(String.valueOf(keliling));
    }

    // Reset form values
    public void resetForm() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasil.setText("-");
        lblKeliling.setText("-");
    }

    // Menampilkan pesan error (jika input bukan angka)
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Mendaftarkan Listener untuk tombol (Controller memberikan aksi)
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    
    public void addKelilingListener(ActionListener listener) {
        btnKeliling.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}