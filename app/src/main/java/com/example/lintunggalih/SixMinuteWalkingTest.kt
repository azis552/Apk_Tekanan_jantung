package com.example.lintunggalih

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class SixMinuteWalkingTest : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_six_minute_walking_test)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val tanggallahir = sharedPreferences.getString("tanggalLahir", null)

        if (tanggallahir.isNullOrEmpty() || !isValidDate(tanggallahir)) {
            Toast.makeText(this, "Tanggal lahir tidak valid!", Toast.LENGTH_SHORT).show()
            return
        }

        fun calculateAge(selectedDate: String): Int {
            val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter.ofPattern("d/M/yyyy")
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val birthDate = LocalDate.parse(selectedDate, formatter)
            val currentDate = LocalDate.now()
            return ChronoUnit.YEARS.between(birthDate, currentDate).toInt()
        }

        val umur = calculateAge(tanggallahir)
        val tinggibadan = findViewById<EditText>(R.id.tinggibadan)
        val beratbadan = findViewById<EditText>(R.id.beratbadan)
        val tdistirahat = findViewById<EditText>(R.id.ed_tdistirahat)
        val hristirahat = findViewById<EditText>(R.id.ed_hristirahat)
        val jaraktempuh = findViewById<EditText>(R.id.ed_jaraktempuh)
        val tdmaksimal = findViewById<EditText>(R.id.ed_tdmaksimallatihan)
        val hrmaksimal = findViewById<EditText>(R.id.ed_hrmaksimallatihan)
        val kapasitasaerobic = findViewById<EditText>(R.id.ed_kapasitasaerobic)
        val thresholdiskemik = findViewById<EditText>(R.id.ed_thresholdiskemik)




        val btnSelanjutnya = findViewById<Button>(R.id.btn_selanjutnya)
        btnSelanjutnya.setOnClickListener {
            val hasilMets = calculateMets(jaraktempuh.text.toString())
            val jarak30 = calculateJarak30(jaraktempuh.text.toString())
            val cal06 = calculate06(jarak30.toString())
            val cal07 = calculate06(jarak30.toString())
            val cal085 = calculate085(jarak30.toString())

            val cairanHF = calculateTotalCairanHF(beratbadan.text.toString())
            val cairanNormal = calculateTotalCairanNormal(beratbadan.text.toString())
            
            Toast.makeText(this, jarak30.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidDate(date: String): Boolean {
        return try {
            val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter.ofPattern("d/M/yyyy")
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            LocalDate.parse(date, formatter)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun calculateMets(jaraktempuh: String): BigDecimal {
        // Konversi jarak tempuh ke BigDecimal
        val jarakTempuhBD = BigDecimal(jaraktempuh)

        // Perhitungan METs
        val mets = jarakTempuhBD.multiply(BigDecimal("0.03"))
            .add(BigDecimal("3.98"))
            .divide(BigDecimal("3.5"), 9, RoundingMode.HALF_UP) // 2 adalah jumlah desimal yang diinginkan

        return mets
    }

    private fun calculateJarak30(jaraktempuh: String): Int {
        val jaraktempuhInt = jaraktempuh.toInt()

        val jarak30 = jaraktempuhInt*5

        return jarak30
    }

    private fun calculate06(jarak30: String): BigDecimal? {
        val jarak30BD = BigDecimal(jarak30)

        val cal06 = jarak30BD.multiply(BigDecimal("0.6"))

        return cal06
    }
    private fun calculate07(jarak30: String): BigDecimal? {
        val jarak30BD = BigDecimal(jarak30)

        val cal07 = jarak30BD.multiply(BigDecimal("0.7"))

        return cal07
    }
    private fun calculate085(jarak30: String): BigDecimal? {
        val jarak30BD = BigDecimal(jarak30)

        val cal085 = jarak30BD.multiply(BigDecimal("0.85"))

        return cal085
    }

    private fun calculateTotalCairanHF(beratBadan: String): BigDecimal? {
        val beratBadanBD = BigDecimal(beratBadan)
        val cairanHF = beratBadanBD.multiply(BigDecimal("0.7")).multiply(BigDecimal("30"))
        return cairanHF
    }
    private fun calculateTotalCairanNormal(beratBadan: String): BigDecimal? {
        val beratBadanBD = BigDecimal(beratBadan)
        val cairanNormal = beratBadanBD.multiply(BigDecimal("0.7")).multiply(BigDecimal("30"))
        return cairanNormal
    }
}
