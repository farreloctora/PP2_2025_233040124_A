/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul06;

import javax.swing.*;
import java.awt.*;

public class Tugas1 extends JFrame {

    public Tugas1() {
        setTitle("Kalkulator Sederhana");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Mengatur layout utama menjadi BorderLayout
        setLayout(new BorderLayout());

        // Bagian atas: layar kalkulator (JTextField)
        JTextField layar = new JTextField();
        layar.setHorizontalAlignment(JTextField.RIGHT);
        layar.setEditable(false);
        add(layar, BorderLayout.NORTH);

        // Bagian tengah: panel tombol (menggunakan GridLayout)
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(4, 4, 5, 5));

        // Daftar tombol kalkulator
        String[] tombol = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // Menambahkan tombol ke panel
        for (String teks : tombol) {
            panelTombol.add(new JButton(teks));
        }

        // Tambahkan panel tombol ke bagian tengah
        add(panelTombol, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tugas1());
    }
}

