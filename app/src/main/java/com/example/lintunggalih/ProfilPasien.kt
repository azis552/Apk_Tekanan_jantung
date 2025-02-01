package com.example.lintunggalih

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text
import java.util.Calendar

class ProfilPasien : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var container: LinearLayout
    private lateinit var sharedRiwayat: SharedPreferences
    private val riwayatList = mutableListOf<Pair<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_pasien)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val username = sharedPreferences.getString("username", "Guest")
        container = findViewById(R.id.containerLayout)
        sharedRiwayat = getSharedPreferences("RiwayatPengobatan", MODE_PRIVATE)
        addInput()
        sharedRiwayat.edit().remove("riwayat_pengobatan")
        findViewById<Button>(R.id.plus).setOnClickListener { addInput() }
//        loadSavedData() // Load data saat aplikasi dibuka
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

            if (cbdiabetes.isChecked) faktoresiko.add("Diabetes")
            if (cbhipertensi.isChecked) faktoresiko.add("Hipertensi")
            if (cbdisplidemia.isChecked) faktoresiko.add("Displidemia")
            if (cbperokok.isChecked) faktoresiko.add("Perokok")
            if (cbriwayatkeluarga.isChecked) faktoresiko.add("Riwayat Keluarga")
            if (cblainlain.isChecked) faktoresiko.add("Lain-lain ," + t_lain.text.toString())

            val keluhan = findViewById<EditText>(R.id.keluhan).text.toString()
            val ed_riwayatkeluarga = findViewById<EditText>(R.id.ed_riwayatkeluarga).text.toString()
            val riwayatsekarang = findViewById<EditText>(R.id.ed_riwayatsekarang).text.toString()
//            val riwayatpengobatan = findViewById<EditText>(R.id.riwayatpengobatan).text.toString()


            val metodetest = findViewById<RadioGroup>(R.id.rg_metodetest)
            val rb_six_minute = findViewById<RadioButton>(R.id.rb_six_minute)
            val rb_treadmill = findViewById<RadioButton>(R.id.rb_treadmill)
            val rb_ergo = findViewById<RadioButton>(R.id.rb_ergo)

            val selectedMetodeTest = metodetest.checkedRadioButtonId
            val selectMetodeTest = findViewById<RadioButton>(selectedMetodeTest)
            val metodetestakhir = selectMetodeTest.text.toString()

            if (nama_pasien.isNotEmpty()) {
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
//                editor.putString("riwayatpengobatan", riwayatpengobatan)
                editor.putString("metodeTest", metodetestakhir)
                editor.putString("tx_lainlain", t_lain.text.toString())
                editor.apply()
                saveData()
            }
//            val jsonString = sharedRiwayat.getString("riwayat_pengobatan", null)
//            Toast.makeText(this, jsonString, Toast.LENGTH_LONG).show()

            if (metodetestakhir == "Six Minute Walking Test") {
                val intent = Intent(this, SixMinuteWalkingTest::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, TreadmillTestMetode::class.java)
                startActivity(intent)
            }
        }
    }
    private fun addInput(): LinearLayout {
        val rowLayout = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            orientation = LinearLayout.HORIZONTAL
        }

        fun Int.dpToPx(context: Context): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
            ).toInt()
        }

// Di dalam Activity atau Fragment
        val marginTop = 20.dpToPx(this) // Konversi 20dp ke pixel
        val marginEnd = 2.dpToPx(this)

        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2f).apply {
            setMargins(0, marginTop, marginEnd, 0) // Gunakan hasil konversi ke pixel


        }
        val params1 = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
            setMargins(0, marginTop, marginEnd, 0) // Gunakan hasil konversi ke pixel
        }
        val params2 = LinearLayout.LayoutParams(70, LinearLayout.LayoutParams.WRAP_CONTENT, 0f).apply {
            setMargins(0, marginTop, marginEnd, 0) // Gunakan hasil konversi ke pixel
        }
        val obatInput = EditText(this).apply {
            hint = "Obat"
            textSize = 20f
            setBackgroundResource(R.drawable.input_background)

            layoutParams = params // Gunakan layoutParams yang sudah dibuat
        }

        val dosisInput = EditText(this).apply {
            hint = "Dosis"
            textSize = 20f
            layoutParams = params1
            setBackgroundResource(R.drawable.input_background)
        }
        val buttonBackground = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor("#832525")) // Warna background
            cornerRadius = 20f // Radius sudut
        }
        val removeButton = Button(this).apply {
            text = "-"
            layoutParams = params2
            background = buttonBackground // Atur background dengan GradientDrawable
            setOnClickListener {
                container.removeView(rowLayout)
                 // Simpan perubahan setelah menghapus
            }

        }

        rowLayout.addView(obatInput)
        rowLayout.addView(dosisInput)
        rowLayout.addView(removeButton)
        container.addView(rowLayout)

        return rowLayout // Pastikan fungsi ini mengembalikan LinearLayout
    }

    private fun saveData() {
        val jsonArray = JSONArray()

        for (i in 0 until container.childCount) {
            val row = container.getChildAt(i) as LinearLayout
            val obat = (row.getChildAt(0) as EditText).text.toString()
            val dosis = (row.getChildAt(1) as EditText).text.toString()

            val jsonObject = JSONObject()
            jsonObject.put("obat", obat)
            jsonObject.put("dosis", dosis)

            jsonArray.put(jsonObject)
        }

        sharedRiwayat.edit().putString("riwayat_pengobatan", jsonArray.toString()).apply()
    }

    private fun loadSavedData() {
        val jsonString = sharedRiwayat.getString("riwayat_pengobatan", null)
        if (!jsonString.isNullOrEmpty()) {
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val obat = jsonObject.getString("obat")
                val dosis = jsonObject.getString("dosis")

                // Tambahkan input baru dan isi datanya
                val row = addInput()
                (row.getChildAt(0) as? EditText)?.setText(obat)
                (row.getChildAt(1) as? EditText)?.setText(dosis)
            }
        }
    }

}