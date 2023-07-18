package `in`.co.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val count_button = findViewById<Button>(R.id.counter_btn)
        val count_txt = findViewById<TextView>(R.id.counter_txt)
        var counter_var = 0;

        count_button.setOnClickListener {
            counter_var = counter_var + 1
            count_txt.text = "You clicked : " + counter_var + " times"
            if (counter_var == 10) {
                counter_var = 0
                Toast.makeText(this, "Hey You reached the Limit!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}