/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tugas2 extends JFrame implements ActionListener {

    private JTextField inputCelcius;
    private JLabel hasilLabel;

    public Tugas2() {
        setTitle("Konverter Suhu");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));

        // Komponen
        JLabel labelCelcius = new JLabel("Celcius:");
        inputCelcius = new JTextField();
        JButton tombolKonversi = new JButton("Konversi");
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        hasilLabel = new JLabel("");

        // Tambahkan ActionListener ke tombol
        tombolKonversi.addActionListener(this);

        // Menambahkan komponen ke jendela
        add(labelCelcius);
        add(inputCelcius);
        add(tombolKonversi);
        add(new JLabel()); // kosong, untuk perataan grid
        add(labelFahrenheit);
        add(hasilLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double celcius = Double.parseDouble(inputCelcius.getText());
            double fahrenheit = (celcius * 9 / 5) + 32;
            hasilLabel.setText(String.format("%.2f °F", fahrenheit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Input harus berupa angka!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tugas2());
    }
}
