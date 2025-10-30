/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * @author wdgus
 */
public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Membuat objek JFrame
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Atur Layout Manager ke BorderLayout
                // Secara default, JFrame sudah menggunakan BorderLayout,
                // tapi diatur ulang untuk kejelasan
                frame.setLayout(new BorderLayout());

                // 2. Buat komponen
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton button = new JButton("Tombol ada di Bawah (SOUTH)");

                // 3. Tambahkan Aksi (ActionListener) ke tombol
                button.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                // 4. Tambahkan komponen ke frame dengan posisi masing-masing
                frame.add(label, BorderLayout.NORTH);
                frame.add(button, BorderLayout.SOUTH);

                // 5. (Opsional) Tambahkan contoh komponen lain di posisi lain
                frame.add(new JButton("WEST"), BorderLayout.WEST);
                frame.add(new JButton("EAST"), BorderLayout.EAST);
                frame.add(new JButton("CENTER"), BorderLayout.CENTER);

                // 6. Tampilkan jendela
                frame.setVisible(true);
            }
        });
    }
}
