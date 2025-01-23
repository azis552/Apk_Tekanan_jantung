package com.example.lintunggalih

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class HasilSixMinute : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_six_minute)
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val respon = sharedPreferences.getString("respon",null)
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
        val keluhan = findViewById<TextView>(R.id.keluhansaatini)
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

        val td_istirahat = sharedPreferences.getString("tdistirahat","0")
        val tdistirahat = findViewById<EditText>(R.id.ed_tdistirahat)
        tdistirahat.setText(td_istirahat)

        val td_hristirahat = sharedPreferences.getString("hristirahat","0")
        val hristirahat = findViewById<EditText>(R.id.ed_hristirahat)
        hristirahat.setText(td_hristirahat)

        val jarak_tempuh = sharedPreferences.getString("jaraktempuh", "0")
        val jaraktempuh = findViewById<EditText>(R.id.ed_jaraktempuh)
        jaraktempuh.setText(jarak_tempuh)

        val td_maksimallatihan = sharedPreferences.getString("tdmaksimal","0")
        val tdmaksimallatihan = findViewById<EditText>(R.id.ed_tdmaksimallatihan)
        tdmaksimallatihan.setText(td_maksimallatihan)

        val hr_maksimallatihan = sharedPreferences.getString("hrmaksimal","0")
        val hrmaksimallatihan = findViewById<EditText>(R.id.ed_hrmaksimallatihan)
        hrmaksimallatihan.setText(hr_maksimallatihan)

        val kapasitas_aerobic = sharedPreferences.getString("kapasitasaerobic","0")
        val kapasitasaerobic = findViewById<EditText>(R.id.ed_kapasitasaerobic)
        kapasitasaerobic.setText(kapasitas_aerobic)

        val threshold_iskemik = sharedPreferences.getString("thresholdiskemik","0")
        val thresholdiskemik = findViewById<EditText>(R.id.ed_thresholdiskemik)
        thresholdiskemik.setText(threshold_iskemik)

        val hasil_mets = sharedPreferences.getString("hasilmets","0")
        val hasilmets = findViewById<TextView>(R.id.mets)
        hasilmets.text = hasil_mets

        val jarak30m = sharedPreferences.getString("jarak30m","0")
        val jarak_30m = findViewById<TextView>(R.id.jarak30menit)
        jarak_30m.text = jarak30m

        val jarak30m06 = sharedPreferences.getString("jarak30m06","0")
        val jarak30_06 = findViewById<TextView>(R.id.nilai06)
        jarak30_06.text = jarak30m06

        val jarak30m07 = sharedPreferences.getString("jarak30m07","0")
        val jarak30_07 = findViewById<TextView>(R.id.nilai07)
        jarak30_07.text = jarak30m07

        val jarak30m085 = sharedPreferences.getString("jarak30m085","0")
        val jarak30_085 = findViewById<TextView>(R.id.nilai085)
        jarak30_085.text = jarak30m085

        val cairan_HF = sharedPreferences.getString("cairanHF","0")
        val cairanHF = findViewById<TextView>(R.id.nilaicairanHF)
        cairanHF.text = cairan_HF

        val cairan_HFNormal = sharedPreferences.getString("cairanNormal","0")
        val cairanHFNormal = findViewById<TextView>(R.id.nilaicairannormal)
        cairanHFNormal.text = cairan_HFNormal

        val hrm_BB = sharedPreferences.getString("hrmBB","0")
        val hrmbb = findViewById<TextView>(R.id.nilaihrmbb)
        hrmbb.text = hrm_BB

        val hrm_tanpaBB = sharedPreferences.getString("hrmtanpaBB","0")
        val hrmtanpabb = findViewById<TextView>(R.id.nilaihrmtanpabb)
        hrmtanpabb.text = hrm_tanpaBB

        val hrr_BB = sharedPreferences.getString("hrr", "0")
        val hrrbb = findViewById<TextView>(R.id.hrrbb)
        hrrbb.text = hrr_BB

        val hrr_tanpaBB = sharedPreferences.getString("hrrtanpa", "0")
        val hrrtanpabb = findViewById<TextView>(R.id.hrrtanpabb)
        hrrtanpabb.text = hrr_tanpaBB

        val hrr_bb20 = sharedPreferences.getString("hrr20","0")
        val hrrbb20 = findViewById<TextView>(R.id.hrrbb20)
        hrrbb20.text = hrr_bb20

        val hrr_bb20tanpa = sharedPreferences.getString("hrr20tanpa","0")
        val hrrbb20tanpa = findViewById<TextView>(R.id.hrrtanpabb20)
        hrrbb20tanpa.text = hrr_bb20tanpa

        val hrr_bb40 = sharedPreferences.getString("hrr40","0")
        val hrrbb40 = findViewById<TextView>(R.id.hrrbb40)
        hrrbb40.text = hrr_bb40

        val hrr_bb40tanpa = sharedPreferences.getString("hrr40tanpa","0")
        val hrrbb40tanpa = findViewById<TextView>(R.id.hrrtanpabb40)
        hrrbb40tanpa.text = hrr_bb40tanpa

        val hrr_bb60 = sharedPreferences.getString("hrr60","0")
        val hrrbb60 = findViewById<TextView>(R.id.hrrbb60)
        hrrbb60.text = hrr_bb60

        val hrr_bb60tanpa = sharedPreferences.getString("hrr60tanpa","0")
        val hrrbb60tanpa = findViewById<TextView>(R.id.hrrtanpabb60)
        hrrbb60tanpa.text = hrr_bb60tanpa

        val hrr_bb80 = sharedPreferences.getString("hrr80","0")
        val hrrbb80 = findViewById<TextView>(R.id.hrrbb80)
        hrrbb80.text = hrr_bb80

        val hrr_bb80tanpa = sharedPreferences.getString("hrr80tanpa","0")
        val hrrbb80tanpa = findViewById<TextView>(R.id.hrrtanpabb80)
        hrrbb80tanpa.text = hrr_bb80tanpa

        val mets_nilai = sharedPreferences.getString("kapasitasaerobic","0")
        val metsnilai = findViewById<TextView>(R.id.nilaimetsakhir)
        metsnilai.text = mets_nilai

        val mets_20 = sharedPreferences.getString("mest20","0")
        val mets20 = findViewById<TextView>(R.id.mets20)
        mets20.text = mets_20

        val mets_40 = sharedPreferences.getString("mest40","0")
        val mets40 = findViewById<TextView>(R.id.mets40)
        mets40.text = mets_40

        val mets_60 = sharedPreferences.getString("mest60","0")
        val mets60 = findViewById<TextView>(R.id.mets60)
        mets60.text = mets_60

        val vo2 = sharedPreferences.getString("vo2","0")
        val vo_2 = findViewById<TextView>(R.id.nilaivo2)
        vo_2.text = vo2

        val vo20 = sharedPreferences.getString("vo220","0")
        val vo_20 = findViewById<TextView>(R.id.vo20)
        vo_20.text = vo20

        val vo40 = sharedPreferences.getString("vo240","0")
        val vo_40 = findViewById<TextView>(R.id.vo40)
        vo_40.text = vo40

        val vo60 = sharedPreferences.getString("vo260","0")
        val vo_60 = findViewById<TextView>(R.id.vo60)
        vo_60.text = vo60

        val nilai_speed = sharedPreferences.getString("speed","0")
        val nilaispeed = findViewById<TextView>(R.id.nilaispeed)
        nilaispeed.text = nilai_speed

        val nilai_speed20 = sharedPreferences.getString("speed20","0")
        val nilaispeed20 = findViewById<TextView>(R.id.speed20)
        nilaispeed20.text = nilai_speed20

        val nilai_speed40 = sharedPreferences.getString("speed40","0")
        val nilaispeed40 = findViewById<TextView>(R.id.speed40)
        nilaispeed40.text = nilai_speed40

        val nilai_speed60 = sharedPreferences.getString("speed60","0")
        val nilaispeed60 = findViewById<TextView>(R.id.speed60)
        nilaispeed60.text = nilai_speed60

        val jarak_berjalan = sharedPreferences.getString("jarakberjalan","0")
        val jarakberjalan = findViewById<TextView>(R.id.nilaijarak)
        jarakberjalan.text = jarak_berjalan

        val jarak_berjalan20 = sharedPreferences.getString("jarakberjalan20","0")
        val jarakberjalan20 = findViewById<TextView>(R.id.jarak20)
        jarakberjalan20.text = jarak_berjalan20

        val jarak_berjalan40 = sharedPreferences.getString("jarakberjalan40","0")
        val jarakberjalan40 = findViewById<TextView>(R.id.jarak40)
        jarakberjalan40.text = jarak_berjalan40

        val jarak_berjalan60 = sharedPreferences.getString("jarakberjalan60","0")
        val jarakberjalan60 = findViewById<TextView>(R.id.jarak60)
        jarakberjalan60.text = jarak_berjalan60
        val ed_kesimpulan = findViewById<EditText>(R.id.ed_kesimpulan)
        val ed_programlatihan = findViewById<EditText>(R.id.ed_programlatihan)
        val btn_pdf = findViewById<Button>(R.id.btn_pdf)
//        val btn_wa = findViewById<Button>(R.id.btn_wa)
        val jenis_metode = findViewById<TextView>(R.id.jenismetode)

        val textViewResponIskemik = findViewById<TextView>(R.id.tv_metode)
        val radioGroupRespon = findViewById<RadioGroup>(R.id.rg_respon)
        val radioButtonPositif = findViewById<RadioButton>(R.id.Positif)
        val radioButtonNegatif = findViewById<RadioButton>(R.id.Negatif)


        if (respon != null){
            jenis_metode.text = "Treadmill Test Metode"
            // Tampilkan TextView dan RadioGroup
            textViewResponIskemik.visibility = View.VISIBLE
            radioGroupRespon.visibility = View.VISIBLE

            // Pilih RadioButton sesuai nilai respon
            when (respon) {
                "Positif" -> radioButtonPositif.isChecked = true
                "Negatif" -> radioButtonNegatif.isChecked = true
            }
        }else{
            jenis_metode.text = "Six Minute Walking Test"
            // Sembunyikan TextView dan RadioGroup jika respon tidak ada
            textViewResponIskemik.visibility = View.GONE
            radioGroupRespon.visibility = View.GONE
        }

//        btn_wa.setOnClickListener {
//            Toast.makeText(this, ed_kesimpulan.text.toString(), Toast.LENGTH_SHORT).show()
//        }
        btn_pdf.setOnClickListener {
            exportPDF()
        }

        val btn_kembali = findViewById<Button>(R.id.btn_kembali)
        btn_kembali.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun exportPDF(){


//        // Buat dokumen PDF
//        val pdfDocument = PdfDocument()
//        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // Ukuran A4 dalam piksel
//        val page = pdfDocument.startPage(pageInfo)
//
//        val canvas = page.canvas
//        val paint = Paint()
//        paint.textSize = 12f
//
//        var x = 20f
//        var y = 40f
//
        // Ambil data dari SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
//        val dokter = sharedPreferences.getString("username", "Guest")
//        val nama_pasien = sharedPreferences.getString("namapasien", "Nama Pasien")
//        val usia_pasien = sharedPreferences.getString("umur", "Umur Pasien")
//        val jenis_kelamin = sharedPreferences.getString("jenisKelamin", "Jenis Kelamin")
//        val alamat_pasien = sharedPreferences.getString("alamat", "Alamat Pasien")
//
//        // Tambahkan data ke PDF
//        canvas.drawText("Nama Dokter: $dokter", x, y, paint)
//        y += 20f
//        canvas.drawText("Nama Pasien: $nama_pasien", x, y, paint)
//        y += 20f
//        canvas.drawText("Usia: $usia_pasien", x, y, paint)
//        y += 20f
//        canvas.drawText("Jenis Kelamin: $jenis_kelamin", x, y, paint)
//        y += 20f
//        canvas.drawText("Alamat: $alamat_pasien", x, y, paint)
//        y += 40f
//
//        // Tambahkan lebih banyak data dari SharedPreferences ke PDF
//        val dataFields = mapOf(
//            "Indikasi Rehab" to sharedPreferences.getString("indikasirehap", "Indikasi Rehab"),
//            "Tanggal Periksa" to sharedPreferences.getString("tanggalPeriksa", "Tanggal Periksa"),
//            "Rekam Medis" to sharedPreferences.getString("rekammedis", "Rekam Medis"),
//            "Faktor Risiko" to sharedPreferences.getString("faktorResiko", "Faktor Risiko"),
//            "Keluhan Saat Ini" to sharedPreferences.getString("keluhan", "Keluhan Pasien"),
//            "Riwayat Keluarga" to sharedPreferences.getString("riwayatKeluarga", "Riwayat Keluarga"),
//            "Riwayat Sekarang" to sharedPreferences.getString("riwayatsekarang", "Riwayat Sekarang"),
//            "Riwayat Pengobatan" to sharedPreferences.getString("riwayatpengobatan", "Riwayat Pengobatan")
//        )
//
//        for ((key, value) in dataFields) {
//            canvas.drawText("$key: $value", x, y, paint)
//            y += 20f
//        }

        // Dimensi kertas PDF (A4)
        val pageWidth = 595
        val pageHeight = 935

        val pdfDocument = PdfDocument()
        val paint = Paint()
        val titlePaint = Paint()

        // Membuat halaman PDF
        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        // Judul Utama
        titlePaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        titlePaint.textSize = 24f
        titlePaint.color = Color.BLACK
        canvas.drawText("Data Result", 220f, 30f, titlePaint)

        // Informasi Identitas Pasien
        var startX = 50f
        var startY = 65f
        val lineHeight = 20f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 14f
        paint.color = Color.BLACK
        canvas.drawText("Identitas Pasien", startX, startY, paint)

        paint.textSize = 12f
        paint.color = Color.BLACK
        val pasienInfo = listOf(
            "Nama" to sharedPreferences.getString("namapasien","0"),
            "Usia" to sharedPreferences.getString("umur","0"),
            "Jenis Kelamin" to sharedPreferences.getString("jenisKelamin","0"),
            "Alamat" to sharedPreferences.getString("alamat","0"),
            "Indikasi Rehab" to sharedPreferences.getString("indikasirehap","0"),
            "Tanggal Periksa" to sharedPreferences.getString("tanggalPeriksa","0"),
            "No. Rekam Medis" to sharedPreferences.getString("rekammedis","0"),
            "Faktor Resiko" to sharedPreferences.getString("faktorResiko","0")
        )

        startY += lineHeight
        paint.typeface = Typeface.DEFAULT
        val labelX = startX              // Posisi X untuk label
        val valueX = startX + 100        // Posisi X untuk value (atur jaraknya sesuai kebutuhan)

        pasienInfo.forEach { (label, value) ->
            // Gambar label di posisi labelX
            canvas.drawText("$label", labelX, startY, paint)
            // Gambar value di posisi valueX
            canvas.drawText(": $value", valueX, startY, paint)

            startY += lineHeight // Pindahkan ke baris berikutnya
        }



// Properti Border
        val borderPaint = Paint()
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = Color.BLACK
        borderPaint.strokeWidth = 2f

// Data Observasi dalam 2 Kolom dengan Border Luar
        startY += 10f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 14f
        paint.color = Color.BLACK

// Judul Data Observation
        val titleY = startY // Simpan posisi judul
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 14f
        paint.color = Color.BLACK
        canvas.drawText("Data Observation", 230f, startY, paint)
        startY += lineHeight // Geser untuk memulai data observasi
        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT
// Data Observasi (4 Data)
        val observasi = listOf(
            "Keluhan Saat ini" to sharedPreferences.getString("keluhan", "0"),
            "Riwayat Keluarga" to sharedPreferences.getString("riwayatKeluarga", "0"),
            "Riwayat Sekarang" to sharedPreferences.getString("riwayatsekarang", "0"),
            "Riwayat Pengobatan" to sharedPreferences.getString("riwayatpengobatan", "0")
        )

// Hitung Kolom
        val column1 = observasi.take(2) // Kolom 1
        val column2 = observasi.drop(2) // Kolom 2
        val columnWidth = 250f
        val rowHeight = lineHeight
        val tableStartY = startY // Awal tabel
        val totalRows = 2
        val tableHeight = totalRows * rowHeight

// Hitung Tinggi Border
        val totalHeight = (titleY - tableStartY) + tableHeight + lineHeight

// Posisi Kolom 2
        val column2X = startX + columnWidth + 20f

// Gambar Border Luar untuk Menutupi Judul dan Kedua Kolom
        val tableEndX = column2X + columnWidth
        canvas.drawRect(
            startX,
            titleY - 14f, // Tambahkan sedikit padding di atas judul
            tableEndX,
            tableStartY + tableHeight + 5f, // Tambahkan padding bawah
            borderPaint
        )

// Gambar Data Kolom 1
        var currentY = tableStartY
        column1.forEach { (label, value) ->
            canvas.drawText(label, startX + 10f, currentY, paint)
            canvas.drawText(": $value", startX + columnWidth / 2, currentY, paint)
            currentY += rowHeight
        }

// Gambar Data Kolom 2
        currentY = tableStartY
        column2.forEach { (label, value) ->
            canvas.drawText(label, column2X + 5f, currentY, paint)
            canvas.drawText(": $value", (column2X + columnWidth / 2)+ 30f, currentY, paint)
            currentY += rowHeight
        }


// Reset paint ke mode default
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f

// Six Minute
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = Color.BLACK
        borderPaint.strokeWidth = 2f

// Data Observasi dalam 2 Kolom dengan Border Luar
        startY += 70f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 14f
        paint.color = Color.BLACK

// Judul Data Observation
        val respon = sharedPreferences.getString("respon",null)
        val titleSix = startY // Simpan posisi judul
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 14f
        paint.color = Color.BLACK
        if (respon != null) {
            canvas.drawText("Treadmill Test Metode", 220f, startY, paint)
        }else{
            canvas.drawText("Six Minute Walking Test", 220f, startY, paint)
        }

        startY += lineHeight // Geser untuk memulai data observasi
        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT

// Data Observasi (4 Data)
        // Data Observasi (4 Data)
        val sixminute = listOf(
            "TD Istirahat" to sharedPreferences.getString("tdistirahat", "0"),
            "HR Istirahat" to sharedPreferences.getString("hristirahat", "0"),
            "Jarak Tempuh" to sharedPreferences.getString("jaraktempuh", "0"),
            "TD Maksimal" to sharedPreferences.getString("tdmaksimal", "0"),
            "HR Maksimal" to sharedPreferences.getString("hrmaksimal", "0"),
            "Kapasitas Aerobic" to sharedPreferences.getString("kapasitasaerobic", "0"),
            "Threshold Iskemik Menit Ke " to sharedPreferences.getString("thresholdiskemik", "0")
        ).toMutableList()

        if (respon != null) {
            sixminute.add("Respon Iskemik" to respon)
        }

// Hitung Kolom
        val column1six = sixminute.take(4) // Kolom 1
        val column2six = sixminute.drop(4) // Kolom 2
        val columnWidthsix = 250f
        val rowHeightsix = lineHeight
        val tableStartYsix = startY // Awal tabel
        val totalRowssix = 2
        val tableHeightsix = totalRowssix * rowHeightsix

// Hitung Tinggi Border
        val totalHeightsix = (titleSix - tableStartYsix) + tableHeightsix + lineHeight

// Posisi Kolom 2
        val column2Xsix = startX + columnWidthsix + 20f

// Gambar Border Luar untuk Menutupi Judul dan Kedua Kolom
        val tableEndXsix = column2Xsix + columnWidthsix
        canvas.drawRect(
            startX,
            titleSix - 14f, // Tambahkan sedikit padding di atas judul
            tableEndXsix,
            tableStartYsix + tableHeightsix + 25f, // Tambahkan padding bawah
            borderPaint
        )

// Gambar Data Kolom 1
        var currentYsix = tableStartYsix
        column1six.forEach { (label, value) ->
            canvas.drawText(label, startX + 10f, currentYsix, paint)
            canvas.drawText(": $value", startX + columnWidthsix / 2, currentYsix, paint)
            currentYsix += rowHeightsix
        }

// Gambar Data Kolom 2
        currentYsix = tableStartYsix
        column2six.forEach { (label, value) ->
            canvas.drawText(label, column2Xsix + 5f, currentYsix, paint)
            canvas.drawText(": $value", (column2Xsix + columnWidthsix / 2) + 30f, currentYsix, paint)
            currentYsix += rowHeightsix
        }


// Reset paint ke mode default
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f
//

//        Mets
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = Color.BLACK
        borderPaint.strokeWidth = 2f

// Data Observasi dalam 2 Kolom dengan Border Luar
        startY += 75f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 14f
        paint.color = Color.BLACK

// Judul Data
        val titlemetsawal = startY // Simpan posisi judul
//        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//        paint.textSize = 14f
//        paint.color = Color.BLACK
//        canvas.drawText("Six Minute Walking Test", 220f, startY, paint)
        startY += lineHeight // Geser untuk memulai data observasi
        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT
// Data Observasi (4 Data)
        val metsawal = listOf(
            "METs" to sharedPreferences.getString("hasilmets", "0"),
            "Jarak 30 Menit" to sharedPreferences.getString("jarak30m", "0"),
            "0.6" to sharedPreferences.getString("jarak30m06", "0"),
            "0.7" to sharedPreferences.getString("jarak30m07", "0"),
            "0.85" to sharedPreferences.getString("jarak30m085", "0"),
            "HF" to "0.7",
            "Cairan HF" to sharedPreferences.getString("cairanHF", "0"),
            "Cairan Normal" to sharedPreferences.getString("cairanNormal", "0"),
        )

// Hitung Kolom
        val column1metsawal = metsawal.take(4) // Kolom 1
        val column2metsawal = metsawal.drop(4) // Kolom 2
        val columnWidthmetsawal = 250f
        val rowHeightmetsawal = lineHeight
        val tableStartYmetsawal = startY // Awal tabel
        val totalRowsmetsawal = 2
        val tableHeightmetsawal = totalRowsmetsawal * rowHeightmetsawal

//// Hitung Tinggi Border
//        val totalHeightmetsawal = (titleSix - tableStartYsix) + tableHeightsix + lineHeight

// Posisi Kolom 2
        val column2Xmetsawal = startX + columnWidthmetsawal + 20f

// Gambar Border Luar untuk Menutupi Judul dan Kedua Kolom
        val tableEndXmetsawal = column2Xmetsawal + columnWidthmetsawal
        canvas.drawRect(
            startX,
            titlemetsawal , // Tambahkan sedikit padding di atas judul
            tableEndXmetsawal,
            tableStartYmetsawal + tableHeightmetsawal+ 25f, // Tambahkan padding bawah
            borderPaint
        )

// Gambar Data Kolom 1
        var currentYmetsawal = tableStartYmetsawal
        column1metsawal.forEach { (label, value) ->
            canvas.drawText(label, startX + 10f, currentYmetsawal, paint)
            canvas.drawText(": $value", startX + columnWidthmetsawal / 2, currentYmetsawal, paint)
            currentYmetsawal+= rowHeightmetsawal
        }

// Gambar Data Kolom 2
        currentYmetsawal = tableStartYmetsawal
        column2metsawal.forEach { (label, value) ->
            canvas.drawText(label, column2Xmetsawal + 5f, currentYmetsawal, paint)
            canvas.drawText(": $value", (column2Xmetsawal + columnWidthmetsawal / 2) + 30f, currentYmetsawal, paint)
            currentYmetsawal += rowHeightmetsawal
        }


// Reset paint ke mode default
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f
//      HRM
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = Color.BLACK
        borderPaint.strokeWidth = 2f

// Data Observasi dalam 2 Kolom dengan Border Luar
        startY += 75f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 14f
        paint.color = Color.BLACK

// Judul Data
        val titlehrm = startY // Simpan posisi judul
//        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//        paint.textSize = 14f
//        paint.color = Color.BLACK
//        canvas.drawText("Six Minute Walking Test", 220f, startY, paint)
        startY += lineHeight // Geser untuk memulai data observasi
        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT
// Data Observasi (4 Data)
        val hrm = listOf(
            "HRM BB" to sharedPreferences.getString("hrmBB", "0"),
            "HRR " to sharedPreferences.getString("hrr", "0"),
            "20 " to sharedPreferences.getString("hrr20", "0"),
            "40 " to sharedPreferences.getString("hrr40", "0"),
            "60 " to sharedPreferences.getString("hrr60", "0"),
            "80 " to sharedPreferences.getString("hrr80", "0"),
            "HRM Tanpa BB" to sharedPreferences.getString("hrmtanpaBB", "0"),
            "HRR " to sharedPreferences.getString("hrrtanpa", "0"),
            "20 " to sharedPreferences.getString("hrr20tanpa", "0"),
            "40 " to sharedPreferences.getString("hrr40tanpa", "0"),
            "60 " to sharedPreferences.getString("hrr60tanpa", "0"),
            "80 " to sharedPreferences.getString("hrr80tanpa", "0"),
        )

// Hitung Kolom
        val column1hrm = hrm.take(6) // Kolom 1
        val column2hrm = hrm.drop(6) // Kolom 2
        val columnWidthhrm = 250f
        val rowHeighthrm = lineHeight
        val tableStartYhrm = startY // Awal tabel
        val totalRowshrm = 2
        val tableHeighthrm = totalRowshrm * rowHeighthrm

//// Hitung Tinggi Border
//        val totalHeightmetsawal = (titleSix - tableStartYsix) + tableHeightsix + lineHeight

// Posisi Kolom 2
        val column2Xhrm = startX + columnWidthhrm + 20f

// Gambar Border Luar untuk Menutupi Judul dan Kedua Kolom
        val tableEndXhrm = column2Xhrm + columnWidthhrm
        canvas.drawRect(
            startX,
            titlehrm , // Tambahkan sedikit padding di atas judul
            tableEndXhrm,
            tableStartYhrm + tableHeighthrm+ 64f, // Tambahkan padding bawah
            borderPaint
        )

// Gambar Data Kolom 1
        var currentYhrm = tableStartYhrm
        column1hrm.forEach { (label, value) ->
            canvas.drawText(label, startX + 10f, currentYhrm, paint)
            canvas.drawText(": $value", startX + columnWidthhrm / 2, currentYhrm, paint)
            currentYhrm+= rowHeighthrm
        }

// Gambar Data Kolom 2
        currentYhrm = tableStartYhrm
        column2hrm.forEach { (label, value) ->
            canvas.drawText(label, column2Xhrm + 5f, currentYhrm, paint)
            canvas.drawText(": $value", (column2Xhrm + columnWidthhrm / 2) + 30f, currentYhrm, paint)
            currentYhrm += rowHeighthrm
        }


// Reset paint ke mode default
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f



//      HRM
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = Color.BLACK
        borderPaint.strokeWidth = 2f

// Data Observasi dalam 2 Kolom dengan Border Luar
        startY += 125f
        paint.textSize = 12f
        paint.color = Color.BLACK

// Judul Data
        val titlehasilrehab = startY // Simpan posisi judul
        paint.textSize = 12f
        paint.color = Color.BLACK
        canvas.drawText("Hasil Rehab", startX+10f, startY, paint)
        canvas.drawText("Target METs Exercise (% Reserved METs)", 280f, startY, paint)
        startY += lineHeight // Geser untuk memulai data observasi
        val paintBackground20 = Paint().apply {
            color = Color.YELLOW // Warna background
            style = Paint.Style.FILL // Mengisi warna pada persegi
        }
        val paintBackground40 = Paint().apply {
            color = Color.argb(173, 173, 216, 230) // Warna light blue dengan alpha 173
            style = Paint.Style.FILL // Mengisi warna pada persegi
        }
        val paintBackground60 = Paint().apply {
            color = Color.argb(173, 255, 182, 193) // Light red dengan alpha 173
            style = Paint.Style.FILL // Mengisi warna pada persegi
        }


        val textPadding = 10f // Padding di sekitar teks
        val textHeight = paint.textSize // Tinggi teks dari Paint yang digunakan
        // Gambar background di belakang teks "20%"
        val textWidth20 = paint.measureText("20%")
        canvas.drawRect(
            280f - textPadding,
            startY - textHeight - textPadding +10f,
            280f + textWidth20 + textPadding,
            startY + textPadding,
            paintBackground20
        )
        canvas.drawText("20%", 280f, startY+5f, paint)
        canvas.drawRect(
            startX,
            665f , // Tambahkan sedikit padding di atas judul
            570f,
            795f , // Tambahkan padding bawah
            borderPaint
        )
        val textWidth40 = paint.measureText("40%")
        canvas.drawRect(
            380f - textPadding,
            startY - textHeight - textPadding+10f,
            380f + textWidth40 + textPadding,
            startY + textPadding,
            paintBackground40
        )
        canvas.drawText("40%", 380f, startY+5f, paint)
        // Gambar background di belakang teks "60%"
        val textWidth60 = paint.measureText("60%")
        canvas.drawRect(
            480f - textPadding,
            startY - textHeight - textPadding+10f,
            480f + textWidth60 + textPadding,
            startY + textPadding,
            paintBackground60
        )
        canvas.drawText("60%", 480f, startY+5f, paint)
        startY += lineHeight // Geser untuk memulai data observasi



//        Hasil mets
        val metshasil = sharedPreferences.getString("kapasitasaerobic", "0")
        val mets20 = sharedPreferences.getString("mest20", "0")
        val mets40 = sharedPreferences.getString("mest40", "0")
        val mets60 = sharedPreferences.getString("mest60", "0")

        canvas.drawText("METS", startX+10f, startY, paint)
            canvas.drawText(": $metshasil", 160f, startY, paint)
        canvas.drawRect(
            280f - textPadding,
            startY - textHeight - textPadding +10f,
            280f + textWidth20 + textPadding,
            startY + textPadding,
            paintBackground20
        )
        canvas.drawText(mets20.toString(), 280f, startY+5f, paint)
        canvas.drawRect(
            380f - textPadding,
            startY - textHeight - textPadding+10f,
            380f + textWidth40 + textPadding,
            startY + textPadding,
            paintBackground40
        )
        canvas.drawText(mets40.toString(), 380f, startY+5f, paint)
        // Gambar background di belakang teks "60%"
        canvas.drawRect(
            480f - textPadding,
            startY - textHeight - textPadding+10f,
            480f + textWidth60 + textPadding,
            startY + textPadding,
            paintBackground60
        )
        canvas.drawText(mets60.toString(), 480f, startY+5f, paint)
        startY += lineHeight // Geser untuk memulai data observasi

//        Hasil VO2
        val vo2 = sharedPreferences.getString("vo2", "0")
        val vo220 = sharedPreferences.getString("vo220", "0")
        val vo240 = sharedPreferences.getString("vo240", "0")
        val vo260 = sharedPreferences.getString("vo260", "0")

        canvas.drawText("VO 2", startX+10f, startY, paint)
        canvas.drawText(": $vo2", 160f, startY, paint)
        canvas.drawRect(
            280f - textPadding,
            startY - textHeight - textPadding +10f,
            280f + textWidth20 + textPadding,
            startY + textPadding,
            paintBackground20
        )
        canvas.drawText(vo220.toString(), 280f, startY+5f, paint)
        canvas.drawRect(
            380f - textPadding,
            startY - textHeight - textPadding+10f,
            380f + textWidth40 + textPadding,
            startY + textPadding,
            paintBackground40
        )
        canvas.drawText(vo240.toString(), 380f, startY+5f, paint)
        // Gambar background di belakang teks "60%"
        canvas.drawRect(
            480f - textPadding,
            startY - textHeight - textPadding+10f,
            480f + textWidth60 + textPadding,
            startY + textPadding,
            paintBackground60
        )
        canvas.drawText(vo260.toString(), 480f, startY+5f, paint)

        startY += lineHeight // Geser untuk memulai data observasi

        //        Hasil Speed
        val speed = sharedPreferences.getString("speed", "0")
        val speed20 = sharedPreferences.getString("speed20", "0")
        val speed40 = sharedPreferences.getString("speed40", "0")
        val speed60 = sharedPreferences.getString("speed20", "0")

        canvas.drawText("Speed", startX+10f, startY, paint)
        canvas.drawText(": $speed", 160f, startY, paint)
        canvas.drawRect(
            280f - textPadding,
            startY - textHeight - textPadding +10f,
            280f + textWidth20 + textPadding,
            startY + textPadding,
            paintBackground20
        )
        canvas.drawText(speed20.toString(), 280f, startY+5f, paint)
        canvas.drawRect(
            380f - textPadding,
            startY - textHeight - textPadding+10f,
            380f + textWidth40 + textPadding,
            startY + textPadding,
            paintBackground40
        )
        canvas.drawText(speed40.toString(), 380f, startY+5f, paint)
        // Gambar background di belakang teks "60%"
        canvas.drawRect(
            480f - textPadding,
            startY - textHeight - textPadding+10f,
            480f + textWidth60 + textPadding,
            startY + textPadding,
            paintBackground60
        )
        canvas.drawText(speed60.toString(), 480f, startY+5f, paint)

        startY += lineHeight // Geser untuk memulai data observasi

        //        Hasil Speed
        val jarakberjalan = sharedPreferences.getString("jarakberjalan", "0")
        val jarakberjalan20 = sharedPreferences.getString("jarakberjalan20", "0")
        val jarakberjalan40 = sharedPreferences.getString("jarakberjalan40", "0")
        val jarakberjalan60 = sharedPreferences.getString("jarakberjalan20", "0")

        canvas.drawText("Jarak Berjalan", startX+10f, startY, paint)
        canvas.drawText(": $jarakberjalan", 160f, startY, paint)
        canvas.drawRect(
            280f - textPadding,
            startY - textHeight - textPadding +10f,
            280f + textWidth20 + textPadding,
            startY + textPadding,
            paintBackground20
        )
        canvas.drawText(jarakberjalan20.toString(), 280f, startY+5f, paint)
        canvas.drawRect(
            380f - textPadding,
            startY - textHeight - textPadding+10f,
            380f + textWidth40 + textPadding,
            startY + textPadding,
            paintBackground40
        )
        canvas.drawText(jarakberjalan40.toString(), 380f, startY+5f, paint)
        // Gambar background di belakang teks "60%"
        canvas.drawRect(
            480f - textPadding,
            startY - textHeight - textPadding+10f,
            480f + textWidth60 + textPadding,
            startY + textPadding,
            paintBackground60
        )
        canvas.drawText(jarakberjalan60.toString(), 480f, startY+5f, paint)

        startY += lineHeight // Geser untuk memulai data observasi
        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT

        paint.style = Paint.Style.FILL
        paint.strokeWidth = 0f

        // Program Latihan
        startY += 10f
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("Conclusion :", startX, startY, paint)
        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT
        startY += lineHeight
        val conclusion = findViewById<EditText>(R.id.ed_kesimpulan)
        canvas.drawText(conclusion.text.toString(), startX, startY, paint)
        // Program Latihan
        startY += lineHeight
        startY += 10f
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("Program Latihan :", startX, startY, paint)

        paint.textSize = 12f
        paint.typeface = Typeface.DEFAULT
        startY += lineHeight
        val program = findViewById<EditText>(R.id.ed_programlatihan)
        canvas.drawText(program.text.toString(), startX, startY, paint)


        // Physician
        paint.color = Color.BLACK
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("Physician", 480f, startY, paint)
        paint.typeface = Typeface.DEFAULT
        val dokter = sharedPreferences.getString("username", "0")
        canvas.drawText(dokter.toString(), 480f, startY+20f, paint)

        // Selesaikan halaman dan tambahkan ke dokumen PDF
        pdfDocument.finishPage(page)
        val nama_pasien = sharedPreferences.getString("namapasien","Nama Pasien")
        val currentTime = SimpleDateFormat("ss").format(Date()) // Format waktu hanya detik
        // Simpan PDF ke penyimpanan
        val directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
        val filePath = "$directoryPath/HasilTes_${nama_pasien}_$currentTime.pdf"

        try {
            // Menyimpan file PDF
            val file = File(filePath)
            pdfDocument.writeTo(FileOutputStream(file))

            // Menggunakan FileProvider untuk membuka PDF di Android 7.0 dan lebih tinggi
            val fileUri: Uri = FileProvider.getUriForFile(this, "${applicationContext.packageName}.provider", file)

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Menambahkan izin untuk membaca file
                addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY) // Tidak menyimpan riwayat aplikasi
            }

            // Membuka PDF
            startActivity(intent)

            // Menampilkan Toast untuk konfirmasi
            Toast.makeText(this, "PDF berhasil disimpan di $filePath", Toast.LENGTH_LONG).show()

        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Gagal membuat PDF: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            pdfDocument.close()
        }
    }
}