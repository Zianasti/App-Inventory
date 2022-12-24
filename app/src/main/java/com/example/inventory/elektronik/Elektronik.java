package com.example.inventory.elektronik;

public class Elektronik {
    private long id;
    private String nama_elektronik;
    private String stok;
    private String harga_beli;
    private String harga_jual;
    private String description;

    public Elektronik(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_elektronik() {
        return nama_elektronik;
    }

    public void setNama_elektronik(String nama_elektronik) {
        this.nama_elektronik = nama_elektronik;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(String harga_beli) {
        this.harga_beli = harga_beli;
    }

    public String getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return  nama_elektronik ;
    }
}
