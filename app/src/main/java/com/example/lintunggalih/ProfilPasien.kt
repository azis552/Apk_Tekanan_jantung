package com.example.lintunggalih

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
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
        val cbdiabetes
        if()







    }
}