package `in`.co.learn

import android.app.ActionBar
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class WelcomeActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(100)
//        installSplashScreen()
        setTheme(R.style.Base_Theme_Learn)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setContentView(R.layout.activity_welcome)
        val txt_signin = findViewById<TextView>(R.id.signinwelpg)

//        window.decorView.windowInsetsController!!.hide(
//    android.view.WindowInsets.Type.statusBars()
//            or android.view.WindowInsets.Type.navigationBars()

        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val isFirst:String? = sharedPreferences.getString("BOOL","")

        if (isFirst.equals("YES")){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else{
        val edit : SharedPreferences.Editor = sharedPreferences.edit()
        edit.putString("BOOL","YES")
        edit.apply()
        }

        val btn_register = findViewById<Button>(R.id.button2)
        btn_register.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        txt_signin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}