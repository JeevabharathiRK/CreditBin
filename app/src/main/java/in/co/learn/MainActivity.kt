package `in`.co.learn

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_WIFI_ADD_NETWORKS
import android.provider.Settings.ADD_WIFI_RESULT_ADD_OR_UPDATE_FAILED
import android.provider.Settings.ADD_WIFI_RESULT_ALREADY_EXISTS
import android.provider.Settings.ADD_WIFI_RESULT_SUCCESS
import android.provider.Settings.EXTRA_WIFI_NETWORK_LIST
import android.provider.Settings.EXTRA_WIFI_NETWORK_RESULT_LIST
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    private lateinit var wifiManager: WifiManager

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)
        val name_change: TextView = findViewById(R.id.textView7)
        val qr_btn: ImageView = findViewById(R.id.imageView6)


        show_promt()

        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val isFirst: String? = sharedPreferences.getString("RECIVE", "")
        val edit: SharedPreferences.Editor = sharedPreferences.edit()
        if (isFirst.equals("YES")) {
            name_change.text = sharedPreferences.getString("LOAD", "USER")
        } else {
            val receivedData = intent.getStringExtra("sharedData")
            val name: String = receivedData.toString() + "⚡"
            edit.putString("LOAD", name)
            edit.putString("RECIVE", "YES")
            edit.apply()
            name_change.text = name
        }

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        qr_btn.setOnClickListener {

            if (wifiManager.isWifiEnabled) {
                val intentIntegrator = IntentIntegrator(this@MainActivity)
                intentIntegrator.setBeepEnabled(false)
                intentIntegrator.setCameraId(0)
                intentIntegrator.setPrompt("Sacn The Product QR")
                intentIntegrator.setBarcodeImageEnabled(false)
                intentIntegrator.initiateScan()
            } else {
                Toast.makeText(this, "PLEASE Connect to CreditBin (WIFI)", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    fun show_promt() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Connect To CreditBin✨")
        builder.setMessage("Open Wifi And Establish Connection With CreditBin.")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("USE_WIFI") { dialog, which ->
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            startActivity(intent)
        }
        builder.show()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("MainActivity", "Scanned")
                Toast.makeText(this, "Scanned -> " + result.contents, Toast.LENGTH_SHORT)
                    .show()
                val textView:TextView = findViewById(R.id.textView23)
                textView.text = 0.02.toString()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}