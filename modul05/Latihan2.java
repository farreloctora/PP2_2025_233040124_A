/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul05;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Membuat jendela utama
                JFrame frame = new JFrame("Jendela dengan Label");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Membuat komponen JLabel
                JLabel label = new JLabel("Ini adalah JLabel.", JLabel.CENTER);

                // 2. Menambahkan JLabel ke JFrame
                // Secara default, JFrame menggunakan BorderLayout,
                // dan add() akan menempatkannya di bagian tengah (CENTER)
                frame.add(label);

                // 3. Menampilkan jendela
                frame.setVisible(true);
            }
        });
    }
}
