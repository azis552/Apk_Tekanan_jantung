package com.example.lintunggalih

import android.annotation.SuppressLint
import android.content.Intent
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
        val username = sharedPreferences.getString("username", "guest")

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
        val tinggibadan = findViewById<EditText>(R.id.ed_tinggibadan)
        val beratbadan = findViewById<EditText>(R.id.ed_beratbadan)
        val tdistirahat = findViewById<EditText>(R.id.ed_tdistirahat)
        val hristirahat = findViewById<EditText>(R.id.ed_hristirahat)
        val jaraktempuh = findViewById<EditText>(R.id.ed_jaraktempuh)
        val tdmaksimal = findViewById<EditText>(R.id.ed_tdmaksimallatihan)
        val hrmaksimal = findViewById<EditText>(R.id.ed_hrmaksimallatihan)
        val kapasitasaerobic = findViewById<EditText>(R.id.ed_kapasitasaerobic)
        val thresholdiskemik = findViewById<EditText>(R.id.ed_thresholdiskemik)



        val editor = sharedPreferences.edit()
        val btnSelanjutnya = findViewById<Button>(R.id.btn_selanjutnya)
        btnSelanjutnya.setOnClickListener {

            editor.putString("umur", umur.toString())
            editor.putString("tinggibadan", tinggibadan.text.toString())
            editor.putString("beratbadan", beratbadan.text.toString())
            editor.putString("tdistirahat", tdistirahat.text.toString())
            editor.putString("hristirahat", hristirahat.text.toString())
            editor.putString("jaraktempuh", jaraktempuh.text.toString())
            editor.putString("tdmaksimal", tdmaksimal.text.toString())
            editor.putString("hrmaksimal", hrmaksimal.text.toString())
            editor.putString("kapasitasaerobic",kapasitasaerobic.text.toString())
            editor.putString("thresholdiskemik",thresholdiskemik.text.toString())

            val hasilMets = calculateMets(jaraktempuh.text.toString())
            val jarak30 = calculateJarak30(jaraktempuh.text.toString())
            val cal06 = calculate06(jarak30.toString())
            val cal07 = calculate06(jarak30.toString())
            val cal085 = calculate085(jarak30.toString())

            editor.putString("hasilmets", hasilMets.toString())
            editor.putString("jarak30m", jarak30.toString())
            editor.putString("jarak30m06", cal06.toString())
            editor.putString("jarak30m07", cal07.toString())
            editor.putString("jarak30m085", cal085.toString())

            val cairanHF = calculateTotalCairanHF(beratbadan.text.toString())
            val cairanNormal = calculateTotalCairanNormal(beratbadan.text.toString())

            editor.putString("cairanHF", cairanHF.toString())
            editor.putString("cairanNormal", cairanNormal.toString())

            val hrmBB = calculateHRMBB(umur.toString())
            val hrr = calculateHrr(hrmBB.toString(),hrmaksimal.text.toString())
            val hrr20 = calculatehrr20(hrr.toString(), hrmaksimal.text.toString())
            val hrr40 = calculatehrr40(hrr.toString(), hrmaksimal.text.toString())
            val hrr60 = calculatehrr60(hrr.toString(), hrmaksimal.text.toString())
            val hrr80 = calculatehrr80(hrr.toString(), hrmaksimal.text.toString())

            editor.putString("hrmBB", hrmBB.toString())
            editor.putString("hrr",hrr.toString())
            editor.putString("hrr20", hrr20.toString())
            editor.putString("hrr40", hrr40.toString())
            editor.putString("hrr60", hrr60.toString())
            editor.putString("hrr80", hrr80.toString())

            val hrmtanpaBB = calculateHRMtanpaBB(umur.toString())
            val hrrtanpa = calculateHrrtanpabb(hrmtanpaBB.toString(),hrmaksimal.text.toString())
            val hrr20tanpa = calculatehrr20tanpa(hrrtanpa.toString(), hrmaksimal.text.toString())
            val hrr40tanpa = calculatehrr40tanpa(hrrtanpa.toString(), hrmaksimal.text.toString())
            val hrr60tanpa = calculatehrr60tanpa(hrrtanpa.toString(), hrmaksimal.text.toString())
            val hrr80tanpa = calculatehrr80tanpa(hrrtanpa.toString(), hrmaksimal.text.toString())

            editor.putString("hrmtanpaBB", hrmtanpaBB.toString())
            editor.putString("hrrtanpa", hrrtanpa.toString())
            editor.putString("hrr20tanpa",hrr20tanpa.toString())
            editor.putString("hrr40tanpa",hrr40tanpa.toString())
            editor.putString("hrr60tanpa",hrr60tanpa.toString())
            editor.putString("hrr80tanpa",hrr80tanpa.toString())

//            hasil rehab
//            mest
            val mest20 = calculatemets20(kapasitasaerobic.text.toString())
            val mest40 = calculatemets40(kapasitasaerobic.text.toString())
            val mest60 = calculatemets60(kapasitasaerobic.text.toString())

            editor.putString("mest20", mest20.toString())
            editor.putString("mest40", mest40.toString())
            editor.putString("mest60", mest60.toString())
//            vo2
            val vo2 = calculatev02(kapasitasaerobic.text.toString())
            val vo220 = calculatev0220(mest20.toString())
            val vo240 = calculatev0240(mest40.toString())
            val vo260 = calculatev0260(mest60.toString())

            editor.putString("vo2", vo2.toString())
            editor.putString("vo220", vo220.toString())
            editor.putString("vo240", vo240.toString())
            editor.putString("vo260", vo260.toString())
//            Spped
            val speed = calculatespeed(vo2.toString())
            val speed20 = calculatespeedvo2(vo220.toString())
            val speed40 = calculatespeedvo4(vo240.toString())
            val speed60 = calculatespeedvo6(vo260.toString())

            editor.putString("speed", speed.toString())
            editor.putString("speed20", speed20.toString())
            editor.putString("speed40", speed40.toString())
            editor.putString("speed60", speed60.toString())

//            jarakberjalan
            val jarakberjalan = calculatejarakberjalan(speed.toString())
            val jarakberjalan20 = calculatejarakberjalan20(speed20.toString())
            val jarakberjalan40 = calculatejarakberjalan40(speed40.toString())
            val jarakberjalan60 = calculatejarakberjalan60(speed60.toString())

            editor.putString("jarakberjalan", jarakberjalan.toString())
            editor.putString("jarakberjalan20", jarakberjalan20.toString())
            editor.putString("jarakberjalan40", jarakberjalan40.toString())
            editor.putString("jarakberjalan60", jarakberjalan60.toString())
            editor.apply()
            val intent = Intent(this, HasilSixMinute::class.java)
            startActivity(intent)
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
            .divide(BigDecimal("3.5"), 2, RoundingMode.HALF_UP) // 2 adalah jumlah desimal yang diinginkan

        return mets
    }

    private fun calculateJarak30(jaraktempuh: String): Int {
        val jaraktempuhInt = jaraktempuh.toInt()

        val jarak30 = jaraktempuhInt*5

        return jarak30
    }

    private fun calculate06(jarak30: String): BigDecimal? {
        val jarak30BD = BigDecimal(jarak30)

        val cal06 = jarak30BD.multiply(BigDecimal("0.6")).setScale(1,RoundingMode.HALF_UP)

        return cal06
    }
    private fun calculate07(jarak30: String): BigDecimal? {
        val jarak30BD = BigDecimal(jarak30)

        val cal07 = jarak30BD.multiply(BigDecimal("0.7")).setScale(1,RoundingMode.HALF_UP)

        return cal07
    }
    private fun calculate085(jarak30: String): BigDecimal? {
        val jarak30BD = BigDecimal(jarak30)

        val cal085 = jarak30BD.multiply(BigDecimal("0.85")).setScale(1,RoundingMode.HALF_UP)

        return cal085
    }

    private fun calculateTotalCairanHF(beratBadan: String): BigDecimal? {
        val beratBadanBD = BigDecimal(beratBadan)
        val cairanHF = beratBadanBD.multiply(BigDecimal("0.7")).multiply(BigDecimal("30")).setScale(1,RoundingMode.HALF_UP)
        return cairanHF
    }
    private fun calculateTotalCairanNormal(beratBadan: String): BigDecimal? {
        val beratBadanBD = BigDecimal(beratBadan)
        val cairanNormal = beratBadanBD.multiply(BigDecimal("30")).setScale(1,RoundingMode.HALF_UP)
        return cairanNormal
    }


    private fun calculateHRMBB(usia: String): BigDecimal?{
        val usiaHR = BigDecimal(usia)
        val hrmbb = BigDecimal("164").subtract((usiaHR.multiply(BigDecimal(0.7)))).setScale(1,RoundingMode.HALF_UP)

        return hrmbb
    }
    private fun calculateHrr(hrmbb: String, nadi: String): BigDecimal?{
        val hrmbbhr = BigDecimal(hrmbb)
        val nadiHR = BigDecimal(nadi)
        val hrr = hrmbbhr.subtract(nadiHR).setScale(1,RoundingMode.HALF_UP)
        return hrr
    }
    private fun calculatehrr20(hrr: String,nadi: String): BigDecimal{
        val hrr02 = BigDecimal(hrr)
        val hrr20 = hrr02.multiply(BigDecimal("0.2"))
        val hrr20akhir = hrr20.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr20akhir
    }
    private fun calculatehrr40(hrr: String,nadi: String): BigDecimal{
        val hrr04 = BigDecimal(hrr)
        val hrr40 = hrr04.multiply(BigDecimal("0.4"))
        val hrr40akhir = hrr40.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr40akhir
    }
    private fun calculatehrr60(hrr: String ,nadi: String): BigDecimal{
        val hrr06 = BigDecimal(hrr)
        val hrr60 = hrr06.multiply(BigDecimal("0.6"))
        val hrr60akhir = hrr60.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr60akhir
    }
    private fun calculatehrr80(hrr: String ,nadi: String): BigDecimal{
        val hrr08 = BigDecimal(hrr)
        val hrr80 = hrr08.multiply(BigDecimal("0.8"))
        val hrr80akhir = hrr80.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr80akhir
    }

    private fun calculateHRMtanpaBB(usia: String): BigDecimal?{
        val usiaHR = BigDecimal(usia)
        val hrmtanpabb = BigDecimal("220").subtract(usiaHR).setScale(1,RoundingMode.HALF_UP)

        return hrmtanpabb
    }
    private fun calculateHrrtanpabb(hrmtanpabb: String, nadi: String): BigDecimal?{
        val hrmtanpabbhr = BigDecimal(hrmtanpabb)
        val nadiHR = BigDecimal(nadi)
        val hrr = hrmtanpabbhr.subtract(nadiHR).setScale(1,RoundingMode.HALF_UP)
        return hrr
    }
    private fun calculatehrr20tanpa(hrr: String,nadi: String): BigDecimal{
        val hrr02 = BigDecimal(hrr)
        val hrr20 = hrr02.multiply(BigDecimal("0.2"))
        val hrr20akhir = hrr20.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr20akhir
    }
    private fun calculatehrr40tanpa(hrr: String,nadi: String): BigDecimal{
        val hrr04 = BigDecimal(hrr)
        val hrr40 = hrr04.multiply(BigDecimal("0.4"))
        val hrr40akhir = hrr40.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr40akhir
    }
    private fun calculatehrr60tanpa(hrr: String ,nadi: String): BigDecimal{
        val hrr06 = BigDecimal(hrr)
        val hrr60 = hrr06.multiply(BigDecimal("0.6"))
        val hrr60akhir = hrr60.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr60akhir
    }
    private fun calculatehrr80tanpa(hrr: String ,nadi: String): BigDecimal{
        val hrr08 = BigDecimal(hrr)
        val hrr80 = hrr08.multiply(BigDecimal("0.8"))
        val hrr80akhir = hrr80.add(BigDecimal(nadi)).setScale(1,RoundingMode.HALF_UP)
        return hrr80akhir
    }


//    Hasil Rehab fase 2
//    Mets
    private fun calculatemets20(mets: String): BigDecimal{
        val mets = BigDecimal(mets)
        val mets20 = ((mets.subtract(BigDecimal("1")).multiply(BigDecimal("0.2"))).add(BigDecimal("1"))).setScale(1,RoundingMode.HALF_UP)
        return mets20
    }
    private fun calculatemets40(mets: String): BigDecimal{
        val mets = BigDecimal(mets)
        val mets40 = ((mets.subtract(BigDecimal("1")).multiply(BigDecimal("0.4"))).add(BigDecimal("1"))).setScale(1,RoundingMode.HALF_UP)
        return mets40
    }
    private fun calculatemets60(mets: String): BigDecimal{
        val mets = BigDecimal(mets)
        val mets60 = ((mets.subtract(BigDecimal("1")).multiply(BigDecimal("0.6"))).add(BigDecimal("1"))).setScale(1,RoundingMode.HALF_UP)
        return mets60
    }
//      v02
    private fun calculatev02(mets: String): BigDecimal{
        val mets = BigDecimal(mets)
        val vo2 = mets.multiply(BigDecimal(3.5)).setScale(1,RoundingMode.HALF_UP)
        return vo2
    }
    private fun calculatev0220(mets20: String): BigDecimal{
        val mets = BigDecimal(mets20)
        val vo2 = mets.multiply(BigDecimal(3.5)).setScale(1,RoundingMode.HALF_UP)
        return vo2
    }
    private fun calculatev0240(mets40: String): BigDecimal{
        val mets = BigDecimal(mets40)
        val vo2 = mets.multiply(BigDecimal(3.5)).setScale(1,RoundingMode.HALF_UP)
        return vo2
    }
    private fun calculatev0260(mets60: String): BigDecimal{
        val mets = BigDecimal(mets60)
        val vo2 = mets.multiply(BigDecimal(3.5)).setScale(1,RoundingMode.HALF_UP)
        return vo2
    }
// speed
    private fun calculatespeed(vo: String): BigDecimal{
        val vo = BigDecimal(vo)
        val speed = ((vo.subtract(BigDecimal("3.5"))).divide(BigDecimal("0.1"))).setScale(1,RoundingMode.HALF_UP)
        return speed
    }
    private fun calculatespeedvo2(vo20: String): BigDecimal{
        val vo = BigDecimal(vo20)
        val speed = ((vo.subtract(BigDecimal("3.5"))).divide(BigDecimal("0.1"))).setScale(1,RoundingMode.HALF_UP)
        return speed
    }
    private fun calculatespeedvo4(vo40: String): BigDecimal{
        val vo = BigDecimal(vo40)
        val speed = ((vo.subtract(BigDecimal("3.5"))).divide(BigDecimal("0.1"))).setScale(1,RoundingMode.HALF_UP)
        return speed
    }
    private fun calculatespeedvo6(vo60: String): BigDecimal{
        val vo = BigDecimal(vo60)
        val speed = ((vo.subtract(BigDecimal("3.5"))).divide(BigDecimal("0.1"))).setScale(1,RoundingMode.HALF_UP)
        return speed
    }
//    jarak
    private fun calculatejarakberjalan(speed: String): BigDecimal{
        val speed = BigDecimal(speed)
        val jarak = speed.multiply(BigDecimal("30")).setScale(1,RoundingMode.HALF_UP)
        return jarak
    }
    private fun calculatejarakberjalan20(speed: String): BigDecimal{
        val speed = BigDecimal(speed)
        val jarak = speed.multiply(BigDecimal("30")).setScale(1,RoundingMode.HALF_UP)
        return jarak
    }
    private fun calculatejarakberjalan40(speed: String): BigDecimal{
        val speed = BigDecimal(speed)
        val jarak = speed.multiply(BigDecimal("30")).setScale(1,RoundingMode.HALF_UP)
        return jarak
    }
    private fun calculatejarakberjalan60(speed: String): BigDecimal{
        val speed = BigDecimal(speed)
        val jarak = speed.multiply(BigDecimal("30")).setScale(1,RoundingMode.HALF_UP)
        return jarak
    }


}
