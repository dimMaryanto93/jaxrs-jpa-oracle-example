package com.maryanto.dimas.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MASTER_NASABAH")
public class Nasabah {

    @Id
    @Column(name = "customer_information", nullable = false, unique = true)
    private String cif;
    @Column(name = "nama_lengkap", nullable = false, length = 50)
    private String namaLengkap;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

}
