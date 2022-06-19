package com.example.uas_pam.database;

public class HelmModel {
    String id,nama, nik, alamat, kode_helm, no_tlp;

    public HelmModel(String nama, String nik, String alamat, String kode_helm, String no_tlp) {
        this.nama = nama;
        this.nik = nik;
        this.alamat = alamat;
        this.kode_helm = kode_helm;
        this.no_tlp = no_tlp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKode_helm() {
        return kode_helm;
    }

    public void setKode_helm(String kode_helm) {
        this.kode_helm = kode_helm;
    }

    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }
}
