package com.example.lintunggalih

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilSixMinute : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_six_minute)
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val dokter = sharedPreferences.getString("username", "Guest")
        val nama_pasien = sharedPreferences.getString("namapasien","Nama Pasien")
        val namapasien = findViewById<TextView>(R.id.nama_pasien)
        namapasien.text = nama_pasien

        val usia_pasien = sharedPreferences.getString("umur","Umur Pasien")
        val usiapasien = findViewById<TextView>(R.id.usia_pasien)
        usiapasien.text = usia_pasien

        val jenis_kelamin = sharedPreferences.getString("jenisKelamin","Jenis Kelamin")
        val jeniskelamin = findViewById<TextView>(R.id.jk_pasien)
        jeniskelamin.text = jenis_kelamin

        val alamat_pasien = sharedPreferences.getString("alamat","Alamat Pasien")
        val alamatpasien = findViewById<TextView>(R.id.alamat_pasien)
        alamatpasien.text = alamat_pasien

        val indikasi_rehab = sharedPreferences.getString("indikasirehap", "Indikasi Rehab")
        val indikasirehab = findViewById<TextView>(R.id.indikasi_pasien)
        indikasirehab.text = indikasi_rehab

        val tanggal_periksa = sharedPreferences.getString("tanggalPeriksa", "Tanggal Periksa")
        val tanggalperiksa = findViewById<TextView>(R.id.tanggalperiksa_pasien)
        tanggalperiksa.text = tanggal_periksa

        val no_rekammedis = sharedPreferences.getString("rekammedis", "Rekam Medis")
        val rekammedis = findViewById<TextView>(R.id.rekammedis)
        rekammedis.text = no_rekammedis

        val faktor_resiko = sharedPreferences.getString("faktorResiko", "Faktor Resiko")
        val faktorresiko = findViewById<TextView>(R.id.faktorresiko)
        faktorresiko.text = faktor_resiko

        val keluhan_saatini = sharedPreferences.getString("keluhan", "Keluhan Pasien")
        val keluhan = findViewById<TextView>(R.id.keluhan)
        keluhan.text = keluhan_saatini

        val riwayat_keluarga = sharedPreferences.getString("riwayatKeluarga", "Riwayat Keluarga")
        val riwayatkeluarga = findViewById<TextView>(R.id.riwayatkeluarga)
        riwayatkeluarga.text = riwayat_keluarga

        val riwayatsaatini = sharedPreferences.getString("riwayatsekarang", "Riwayat Sekarang")
        val riwayat_saatini = findViewById<TextView>(R.id.riwayatsaatini)
        riwayat_saatini.text = riwayatsaatini

        val riwayatpengobatan = sharedPreferences.getString("riwayatpengobatan", "Riwayat Pengobatan")
        val riwayat_pengobatan = findViewById<TextView>(R.id.riwayatpengobatan)
        riwayat_pengobatan.text = riwayatpengobatan




    }
}