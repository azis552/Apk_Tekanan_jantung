package com.example.lintunggalih

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.util.Calendar

class ProfilPasien : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_pasien)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val username = sharedPreferences.getString("username", "Guest")
//        val namapasien = sharedPreferences.getString("namapasien","")
//        val tanggalLahir = sharedPreferences.getString("tanggalLahir", "")
//        val jenisKelamin = sharedPreferences.getString("jenisKelamin","")
//        val alamat = sharedPreferences.getString("alamat", "")
//        val indikasirehap = sharedPreferences.getString("indikasirehap","")
//        val tanggalPeriksa = sharedPreferences.getString("tanggalPeriksa", "")
//        val rekammedis = sharedPreferences.getString("rekammedis","")
//        val faktorResiko = sharedPreferences.getString("faktorResiko", "")
//        val lainlain = sharedPreferences.getString("tx_lainlain","")
//        val keluhan = sharedPreferences.getString("keluhan","")
//        val riwayatkeluarga = sharedPreferences.getString("riwayatkeluarga","")
//        val riwayatpengobatan = sharedPreferences.getString("riwayatpengobatan","")
//        val metodeTest = sharedPreferences.getString("metodeTest","")

//        val ed_nama = findViewById<EditText>(R.id.et_nama)
//        ed_nama.setText(namapasien.toString())
//        val tv_tanggallahir = findViewById<TextView>(R.id.tv_tanggallahir)
//        tv_tanggallahir.setText(tanggalLahir.toString())
//        // Temukan RadioGroup dan RadioButton
//        val rgJenisKelamin = findViewById<RadioGroup>(R.id.rg_jeniskelamin)
//        val rbLakiLaki = findViewById<RadioButton>(R.id.lakilaki)
//        val rbPerempuan = findViewById<RadioButton>(R.id.perempuan)
//        when (jenisKelamin) {
//            "Laki-Laki" -> rbLakiLaki.isChecked = true
//            "Perempuan" -> rbPerempuan.isChecked = true
//        }
//        val ed_alamat = findViewById<EditText>(R.id.alamat)
//        ed_alamat.setText(alamat.toString())
//        val ed_indikasirehap = findViewById<EditText>(R.id.indikasirehab)
//        ed_indikasirehap.setText(indikasirehap.toString())
//        val tv_tanggalperiksa = findViewById<TextView>(R.id.tv_tanggalperiksa)
//        tv_tanggalperiksa.text = tanggalPeriksa
//        val ed_rekammedis = findViewById<EditText>(R.id.rekammedis)
//        ed_rekammedis.setText(rekammedis)
//
//        val cb_diabetes = findViewById<CheckBox>(R.id.diabetes)
//        val cb_hipertensi = findViewById<CheckBox>(R.id.hipertensi)
//        val cb_dislipidemia = findViewById<CheckBox>(R.id.displidemia)
//        val cb_perokok = findViewById<CheckBox>(R.id.perokok)
//        val cb_riwayatkeluarga = findViewById<CheckBox>(R.id.riwayatkeluarga)
//        val cb_lain = findViewById<CheckBox>(R.id.lainlain)
//        val ed_lain = findViewById<EditText>(R.id.tx_lain)
//
//        val faktorList = faktorResiko!!?.split(",")
//        if (faktorList != null) {
//            cb_diabetes.isChecked = faktorList.contains("Diabetes")
//        }
//        if (faktorList != null) {
//            cb_hipertensi.isChecked = faktorList.contains("Hipertensi")
//        }
//        if (faktorList != null) {
//            cb_dislipidemia.isChecked = faktorList.contains("Dislipidemia")
//        }
//        if (faktorList != null) {
//            cb_perokok.isChecked = faktorList.contains("Perokok")
//        }
//        if (faktorList != null) {
//            cb_riwayatkeluarga.isChecked = faktorList.contains("Riwayat Keluarga")
//        }
//        if (faktorList != null) {
//            cb_lain.isChecked = faktorList.contains("Lain-lain")
//        }
//        ed_lain.setText(lainlain.toString())
//
//        val ed_keluhan =


        val tvUsername = findViewById<TextView>(R.id.tv_username)
        tvUsername.text = "Dokter : $username"

        val buttonPickDate = findViewById<Button>(R.id.btn_tgllahir)
        val textViewDate = findViewById<TextView>(R.id.tv_tanggallahir)
        buttonPickDate.setOnClickListener {
            // Inisialisasi Calendar untuk mendapatkan tanggal saat ini
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Membuat dialog date picker
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Menampilkan tanggal yang dipilih
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    textViewDate.text = selectedDate
                },
                year, month, day
            )

            // Tampilkan dialog
            datePickerDialog.show()
        }
        val btnTglPeriksa = findViewById<Button>(R.id.btn_tglperiksa)
        val tv_tanggalperiksa = findViewById<TextView>(R.id.tv_tanggalperiksa)
        btnTglPeriksa.setOnClickListener {
            // Inisialisasi Calendar untuk mendapatkan tanggal saat ini
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Membuat dialog date picker
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Menampilkan tanggal yang dipilih
                    val selectedDateperiksa = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    tv_tanggalperiksa.text = selectedDateperiksa
                },
                year, month, day
            )

            // Tampilkan dialog
            datePickerDialog.show()
        }

        val btnSelanjutnya = findViewById<Button>(R.id.btnSelanjutnya)

//       btn
        btnSelanjutnya.setOnClickListener {
            val nama_pasien = findViewById<EditText>(R.id.et_nama).text.toString()
            val tgl_lahir = findViewById<TextView>(R.id.tv_tanggallahir).text.toString()
            val rg_jeniskelamin = findViewById<RadioGroup>(R.id.rg_jeniskelamin)
            val radiolakilaki = findViewById<RadioButton>(R.id.lakilaki)
            val radioperempuan = findViewById<RadioButton>(R.id.perempuan)
//RadioButton
            val selectedRadioButtonId = rg_jeniskelamin.checkedRadioButtonId
            val selectedRB = findViewById<RadioButton>(selectedRadioButtonId)
            val jenis_kelamin = selectedRB.text.toString()

            val alamat = findViewById<EditText>(R.id.alamat).text.toString()
            val indikasiRehab = findViewById<EditText>(R.id.indikasirehab).text.toString()
            val tgl_periksa = findViewById<TextView>(R.id.tv_tanggalperiksa).text.toString()
            val rekammedis = findViewById<EditText>(R.id.rekammedis).text.toString()
//Checkbox
            val faktoresiko = mutableListOf<String>()


            val cbdiabetes = findViewById<CheckBox>(R.id.diabetes)
            val cbhipertensi = findViewById<CheckBox>(R.id.hipertensi)
            val cbdisplidemia = findViewById<CheckBox>(R.id.displidemia)
            val cbperokok = findViewById<CheckBox>(R.id.perokok)
            val cbriwayatkeluarga = findViewById<CheckBox>(R.id.riwayatkeluarga)
            val cblainlain = findViewById<CheckBox>(R.id.lainlain)
            val t_lain = findViewById<EditText>(R.id.tx_lain)

            if(cbdiabetes.isChecked) faktoresiko.add("Diabetes")
            if(cbhipertensi.isChecked) faktoresiko.add("Hipertensi")
            if(cbdisplidemia.isChecked) faktoresiko.add("Displidemia")
            if(cbperokok.isChecked) faktoresiko.add("Perokok")
            if(cbriwayatkeluarga.isChecked) faktoresiko.add("Riwayat Keluarga")
            if(cblainlain.isChecked) faktoresiko.add("Lain-lain ," + t_lain.text.toString())

            val keluhan = findViewById<EditText>(R.id.keluhan).text.toString()
            val ed_riwayatkeluarga = findViewById<EditText>(R.id.ed_riwayatkeluarga).text.toString()
            val riwayatsekarang = findViewById<EditText>(R.id.ed_riwayatsekarang).text.toString()
            val riwayatpengobatan = findViewById<EditText>(R.id.riwayatpengobatan).text.toString()



            val metodetest = findViewById<RadioGroup>(R.id.rg_metodetest)
            val rb_six_minute = findViewById<RadioButton>(R.id.rb_six_minute)
            val rb_treadmill = findViewById<RadioButton>(R.id.rb_treadmill)

            val selectedMetodeTest = metodetest.checkedRadioButtonId
            val selectMetodeTest = findViewById<RadioButton>(selectedMetodeTest)
            val metodetestakhir = selectMetodeTest.text.toString()

            if(nama_pasien.isNotEmpty()){
                val editor = sharedPreferences.edit()
                editor.putString("namapasien", nama_pasien)
                editor.putString("tanggalLahir", tgl_lahir)
                editor.putString("jenisKelamin", jenis_kelamin)
                editor.putString("alamat", alamat)
                editor.putString("indikasirehap", indikasiRehab)
                editor.putString("tanggalPeriksa", tgl_periksa)
                editor.putString("rekammedis", rekammedis)
                editor.putString("faktorResiko", faktoresiko.toString())
                editor.putString("keluhan", keluhan)
                editor.putString("riwayatKeluarga", ed_riwayatkeluarga)
                editor.putString("riwayatsekarang", riwayatsekarang)
                editor.putString("riwayatpengobatan", riwayatpengobatan)
                editor.putString("metodeTest", metodetestakhir)
                editor.putString("tx_lainlain", t_lain.text.toString())
                editor.apply()
            }

            if(metodetestakhir == "Six Minute Walking Test"){
                val intent = Intent(this, SixMinuteWalkingTest::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, TreadmillTestMetode::class.java)
                startActivity(intent)
            }
        }
    }
}