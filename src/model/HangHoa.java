/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class HangHoa {
    private int maHang;
    private String tenHang;
    private String donViTinh;
    private double giaBan;
    private int soLuong;
    private String moTa;
    private String loaiHang;
    
    // Constructor
    public HangHoa() {}
    
    public HangHoa(int maHang, String tenHang, String donViTinh, double giaBan, 
                  int soLuong, String moTa, String loaiHang) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.donViTinh = donViTinh;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.loaiHang = loaiHang;
    }
    
    // Getters and Setters
    public int getMaHang() {
        return maHang;
    }
    
    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }
    
    public String getTenHang() {
        return tenHang;
    }
    
    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }
    
    public String getDonViTinh() {
        return donViTinh;
    }
    
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    
    public double getGiaBan() {
        return giaBan;
    }
    
    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public String getLoaiHang() {
        return loaiHang;
    }
    
    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }
    
    @Override
    public String toString() {
        return tenHang;
    }
}