package vcmsa.ci.practice

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        val minTemp = intent.getIntArrayExtra("minTemp")
        val maxTemp = intent.getIntArrayExtra("maxTemp")
        val conditions = intent.getIntArrayExtra("conditions")

        val txtDetails = findViewById<TextView>(R.id.txtDetails)
        val details = StringBuilder()

        val days =
            arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        for (i in 0..6) {
            details.append(
                "${days[i]}: Min ${minTemp?.get(i)}\u00B0C, Max ${maxTemp?.get()}\u00B0C, ${
                    conditions(
                        i
                    )
                }\n"
            )

        }
        txtDetails.text = details.toString()

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }














        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun conditions(i: Int) {


    }

}