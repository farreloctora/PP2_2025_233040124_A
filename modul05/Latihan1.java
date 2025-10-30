/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul05;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Latihan1 { 
    public static void main(String[] args) {
        // Menjalankan kode GUI di Event Dispatch Thread (EDT)
        // Praktik terbaik untuk menghindari masalah thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Buat objek JFrame
                JFrame frame = new JFrame("Jendela Pertamaku");
                
                // 2. Atur ukuran jendela (lebar 400, tinggi 300)
                frame.setSize(400, 300);
                
                // 3. Atur aksi saat tombol close (X) ditekan
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // 4. Buat jendela terlihat
                frame.setVisible(true);
            }
        });
    }
}
