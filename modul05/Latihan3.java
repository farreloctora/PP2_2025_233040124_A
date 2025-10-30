/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul05;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Membuat jendela utama
                JFrame frame = new JFrame("Label dan Tombol");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Mengatur layout manager
                // FlowLayout akan menyusun komponen dari kiri ke kanan
                frame.setLayout(new FlowLayout());

                // 2. Membuat komponen GUI
                JLabel label = new JLabel("Teks Awal");
                JButton button = new JButton("Klik Saya!");

                // 3. Menambahkan aksi (event listener) ke tombol
                // Menggunakan lambda expression agar lebih ringkas
                button.addActionListener(e -> {
                    // Aksi ini akan mengubah teks pada label
                    label.setText("Tombol berhasil diklik!");
                });

                // 4. Menambahkan komponen ke frame
                // Dengan FlowLayout, komponen akan muncul berdampingan
                frame.add(label);
                frame.add(button);

                // 5. Menampilkan jendela
                frame.setVisible(true);
            }
        });
    }
}
