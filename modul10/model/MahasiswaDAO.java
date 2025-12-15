/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10.model;

/**
 *
 * @author Hype AMD
 */
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {
    
    // CREATE
    public boolean tambah(Mahasiswa mhs) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, mhs.getNama());
            pst.setString(2, mhs.getNim());
            pst.setString(3, mhs.getJurusan());
            pst.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan: " + e.getMessage());
            return false;
        }
    }
    
    // READ ALL
    public List<Mahasiswa> getAll() {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
            while (res.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setId(res.getInt("id"));
                mhs.setNama(res.getString("nama"));
                mhs.setNim(res.getString("nim"));
                mhs.setJurusan(res.getString("jurusan"));
                list.add(mhs);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Load Data: " + e.getMessage());
        }
        return list;
    }
    
    // READ BY NAMA (SEARCH)
    public List<Mahasiswa> cariByNama(String keyword) {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setId(res.getInt("id"));
                mhs.setNama(res.getString("nama"));
                mhs.setNim(res.getString("nim"));
                mhs.setJurusan(res.getString("jurusan"));
                list.add(mhs);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Cari: " + e.getMessage());
        }
        return list;
    }
    
    // UPDATE
    public boolean ubah(Mahasiswa mhs) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, mhs.getNama());
            pst.setString(2, mhs.getJurusan());
            pst.setString(3, mhs.getNim());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Edit: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE
    public boolean hapus(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            pst.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Hapus: " + e.getMessage());
            return false;
        }
    }
    
    // CEK DUPLIKAT NIM
    public boolean isNimExist(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet res = pst.executeQuery();
            if (res.next() && res.getInt(1) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}