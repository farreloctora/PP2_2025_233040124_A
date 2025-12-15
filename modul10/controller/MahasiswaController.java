/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10.controller;

/**
 *
 * @author Hype AMD
 */
import id.ac.unpas.modul10.model.Mahasiswa;
import id.ac.unpas.modul10.model.MahasiswaDAO;
import id.ac.unpas.modul10.view.MahasiswaView;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MahasiswaController {
    private MahasiswaView view;
    private MahasiswaDAO dao;
    
    public MahasiswaController(MahasiswaView view) {
        this.view = view;
        this.dao = new MahasiswaDAO();
        initController();
        loadData();
    }
    
    private void initController() {
        // Event untuk klik tabel
        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getSelectedRow();
                if (row >= 0) {
                    view.setNama(view.model.getValueAt(row, 1).toString());
                    view.setNim(view.model.getValueAt(row, 2).toString());
                    view.setJurusan(view.model.getValueAt(row, 3).toString());
                }
            }
        });
        
        // Event tombol Simpan
        view.btnSimpan.addActionListener(e -> tambahData());
        
        // Event tombol Edit
        view.btnEdit.addActionListener(e -> ubahData());
        
        // Event tombol Hapus
        view.btnHapus.addActionListener(e -> hapusData());
        
        // Event tombol Clear
        view.btnClear.addActionListener(e -> view.clearForm());
        
        // Event tombol Cari
        view.btnCari.addActionListener(e -> {
            if (view.getKeyword().trim().isEmpty()) {
                loadData();
            } else {
                cariData(view.getKeyword());
            }
        });
    }
    
    private void loadData() {
        view.showDataInTable(dao.getAll());
    }
    
    private void cariData(String keyword) {
        view.showDataInTable(dao.cariByNama(keyword));
    }
    
    private void tambahData() {
        // Validasi input kosong
        if (view.getNama().trim().isEmpty() || view.getNim().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data Nama dan NIM tidak boleh kosong!");
            return;
        }
        
        // Cek duplikat NIM
        if (dao.isNimExist(view.getNim())) {
            JOptionPane.showMessageDialog(view, "NIM sudah ada di database!");
            return;
        }
        
        Mahasiswa mhs = new Mahasiswa(view.getNama(), view.getNim(), view.getJurusan());
        if (dao.tambah(mhs)) {
            JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
            loadData();
            view.clearForm();
        }
    }
    
    private void ubahData() {
        // Validasi input kosong
        if (view.getNama().trim().isEmpty() || view.getNim().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data Nama dan NIM tidak boleh kosong!");
            return;
        }
        
        Mahasiswa mhs = new Mahasiswa(view.getNama(), view.getNim(), view.getJurusan());
        if (dao.ubah(mhs)) {
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah");
            loadData();
            view.clearForm();
        }
    }
    
    private void hapusData() {
        if (dao.hapus(view.getNim())) {
            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
            loadData();
            view.clearForm();
        }
    }
}