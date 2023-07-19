package `in`.co.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countbutton = findViewById<Button>(R.id.counter_btn)
        val counttxt = findViewById<TextView>(R.id.counter_txt)
        var countervar = 0

        countbutton.setOnClickListener {
            countervar += 1
            counttxt.text = "You clicked : " + countervar + " times"
            if (countervar == 10) {
                countervar = 0
                Toast.makeText(this, "Hey You reached the Limit!", Toast.LENGTH_LONG).show()
            }
        }
    }
}