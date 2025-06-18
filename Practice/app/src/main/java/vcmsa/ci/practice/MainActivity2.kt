package vcmsa.ci.practice



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    val days = arrayOf("Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    var minTemp = IntArray(7)
    var maxTemp = IntArray(7)
    var conditions = IntArray(7) { "" }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val edtMin = findViewById<EditText>(R.id.edtMinTemp)
        val edtMax = findViewById<EditText>(R.id.edtMaxTemp)
        val edtWeather = findViewById<EditText>(R.id.edtWeatherCon)
        val txtAve = findViewById<TextView>(R.id.txtAverage)

        var index = 0

        findViewById<Button>(R.id.btnEnterData).setOnClickListener {
            if (edtMin.text.isNotEmpty() && edtMax.text.isNotEmpty() && edtWeather.text.isNotEmpty()) {
                minTemp[index] = edtMin.text.toString().toInt()
                maxTemp[index] = edtMax.text.toString().toInt()
                conditions[index] = edtWeather.text.toString()
                index++

                Toast.makeText(this, "${days[index - 1]} data saved", Toast.LENGTH_SHORT).show()
                edtMin.text.clear()
                edtMax.text.clear()
                edtWeather.text.clear()

                if (index == 7) Toast.makeText(this, "All data is entered", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this,"Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnCalculate).setOnClickListener {
            var total = 0.0
            for (i in 0..6) {
                val avgDay = (minTemp[i] + maxTemp[i] / 2.0)
                total += avgDay
            }
            val weeklyAverage = total / 7
            txtAve.text = "Weekly Average Temp: %.2f".format(weeklyAverage)
        }

        findViewById<Button>(R.id.btnDetails).setOnClickListener {
            val intent = Intent(this,MainActivity3::class.java)
            intent.putExtra("minTemps", minTemp)
            intent.putExtra("maxTemps", maxTemp)
            intent.putExtra("conditions",conditions)
            startActivity(intent)

        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            minTemp = IntArray(7)
            maxTemp = IntArray(7)
            conditions = Array(7) { "" }
            index = 0
            txtAve.text = ""
            Toast.makeText(this, "Data cleared",Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnExit).setOnClickListener {
            finish()
        }













        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}