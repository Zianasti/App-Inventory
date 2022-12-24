package com.example.inventory.fashion;

public class Fashion {
    private long id;
    private String nama_fashion;
    private String stok;
    private String harga_beli;
    private String harga_jual;
    private String description;

    public Fashion(){

    }

    //getter and setter the id


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_fashion() {
        return nama_fashion;
    }

    public void setNama_fashion(String nama_fashion) {
        this.nama_fashion = nama_fashion;
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
        return  nama_fashion ;
    }
}
