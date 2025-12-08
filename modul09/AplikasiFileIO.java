/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *
 * @author Hype AMD
 */

public class AplikasiFileIO extends JFrame {
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText, btnAppendText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;

    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();

        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnAppendText = new JButton("Tambah Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        // Tambahkan komponen ke frame
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Handling
        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());
        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());
        // 3. MENAMBAH FILE TEKS (Text Stream - Append Mode)
        btnAppendText.addActionListener(e -> tambahFileTeks());
        // 4. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        // 5. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Otomatis membaca last_notes.txt saat aplikasi dibuka
        bacaLastNotes();
    }

    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            BufferedReader reader = null; // deklarasi di luar try agar bisa dipakai di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // kosongkan area

                String line;

                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                        "File tidak ditemukan: " + ex.getMessage());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Gagal membaca file: " + ex.getMessage());

            } finally {
                // Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // penting!
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Metode: Membaca last_notes.txt otomatis saat aplikasi dibuka
    private void bacaLastNotes() {
        File file = new File("last_notes.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.setText(""); // kosongkan area

            String line;

            // Baca baris demi baris
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }

        } catch (FileNotFoundException ex) {
            // File tidak ditemukan - tidak perlu menampilkan pesan error
            // aplikasi tetap berjalan normal

        } catch (IOException ex) {
            // Jika ada error lain saat membaca, abaikan saja
            ex.printStackTrace();
        }
    }

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Try-with-resources: stream akan ditutup otomatis
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

    // Contoh: Menambah File Teks dengan Append Mode (tidak menghapus konten lama)
    private void tambahFileTeks() {

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // FileWriter(File file, boolean append)
            // Jika append = true, text akan ditambahkan di akhir file (tidak menimpa)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

                writer.write(textArea.getText());
                writer.newLine(); // Tambahkan baris baru
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan ke file!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Gagal menambah file: " + ex.getMessage());
            }
        }
    }

    // Contoh: Menyimpan OBJEK UserConfig ke file .bin
    private void simpanConfigBinary() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("config.bin"))) {

            UserConfig config = new UserConfig();
            config.setUsername("admin"); // contoh, bisa kamu ganti sesuai input
            config.setFontsize(textArea.getFont().getSize());

            // Tulis objek ke file
            oos.writeObject(config);

            JOptionPane.showMessageDialog(
                    this,
                    "UserConfig disimpan!\nUsername: " + config.getUsername() +
                    "\nFont size: " + config.getFontsize()
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal menyimpan binary: " + ex.getMessage()
            );
        }
    }

    // Contoh: Membaca OBJEK UserConfig dari file .bin
    private void muatConfigBinary() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("config.bin"))) {

            // Casting kembali menjadi objek UserConfig
            UserConfig config = (UserConfig) ois.readObject();

            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));

            JOptionPane.showMessageDialog(
                    this,
                    "UserConfig dimuat!\nUsername: " + config.getUsername() +
                    "\nFont size: " + config.getFontsize()
            );

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "File config.bin belum dibuat!"
            );

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal membaca binary: " + ex.getMessage()
            );
        }
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
