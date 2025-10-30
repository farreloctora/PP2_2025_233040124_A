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

public class Tugas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Membuat objek JFrame
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Atur Layout Manager ke BorderLayout
                frame.setLayout(new BorderLayout());

                // Membuat komponen utama
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton buttonSouth = new JButton("Tombol ada di Bawah (SOUTH)");
                JButton buttonWest = new JButton("WEST");
                JButton buttonEast = new JButton("EAST");
                JButton buttonCenter = new JButton("CENTER");

                // Tambahkan aksi ke tombol SOUTH
                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                // Tambahkan aksi ke tombol WEST
                buttonWest.addActionListener(e -> {
                    label.setText("Tombol di WEST diklik!");
                });

                // Tambahkan aksi ke tombol EAST
                buttonEast.addActionListener(e -> {
                    label.setText("Tombol di EAST diklik!");
                });

                // Tambahkan aksi ke tombol CENTER
                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol di CENTER diklik!");
                });

                // Tambahkan komponen ke frame sesuai posisinya
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);

                // Tampilkan jendela
                frame.setVisible(true);
            }
        });
    }
}

