/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10;

/**
 *
 * @author Hype AMD
 */
import id.ac.unpas.modul10.controller.MahasiswaController;
import id.ac.unpas.modul10.view.MahasiswaView;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView view = new MahasiswaView();
            new MahasiswaController(view);
            view.setVisible(true);
        });
    }
}