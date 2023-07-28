package `in`.co.learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
//import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_registration)

        val butt_main = findViewById<Button>(R.id.buttonregpage)
        val button_sign = findViewById<TextView>(R.id.signinregpg)
        button_sign.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        butt_main.setOnClickListener {
//
//            val nav = Intent(this, MainActivity::class.java)
//            startActivity(nav)

            val editTextName = findViewById<EditText>(R.id.editTextText)
            val name = editTextName.text.toString()
//            Toast.makeText(this, "Name saved: $name", Toast.LENGTH_SHORT).show()
            val dataToShare = name
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("sharedData", dataToShare)
            startActivity(intent)
        }

    }
}