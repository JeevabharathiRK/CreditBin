package `in`.co.learn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
<<<<<<< Updated upstream
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
=======
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.zxing.integration.android.IntentIntegrator
import com.google.android.gms.maps.SupportMapFragment
import android.net.Uri
import android.view.View
import com.google.android.gms.maps.GoogleMap

>>>>>>> Stashed changes
class MainActivity : AppCompatActivity() {
    lateinit var btnBarcode: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< Updated upstream
        title = "KotlinApp"
        btnBarcode = findViewById(R.id.button)
        textView = findViewById(R.id.txtContent)
        btnBarcode.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this@MainActivity)
            intentIntegrator.setBeepEnabled(false)
            intentIntegrator.setCameraId(0)
            intentIntegrator.setPrompt("SCAN")
            intentIntegrator.setBarcodeImageEnabled(false)
            intentIntegrator.initiateScan()
        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
=======

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

        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        qr_btn.setOnClickListener {
            if (wifiManager.isWifiEnabled) {
                val intentIntegrator = IntentIntegrator(this@MainActivity)
                intentIntegrator.setBeepEnabled(false)
                intentIntegrator.setCameraId(0)
                intentIntegrator.setPrompt("Scan The Product QR")
                intentIntegrator.setBarcodeImageEnabled(false)
                intentIntegrator.initiateScan()
            } else {
                Toast.makeText(this, "PLEASE Connect to CreditBin (WIFI)", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val locationButton: ImageView = findViewById(R.id.imageView7)
        locationButton.setOnClickListener {
            openGoogleMaps()
        }d
    }

    fun show_promt() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Connect To CreditBin✨")
        builder.setMessage("Open Wifi And Establish Connection With CreditBin.")

        builder.setPositiveButton("USE_WIFI") { dialog, which ->
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            startActivity(intent)
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
>>>>>>> Stashed changes
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("MainActivity", "Scanned")
                Toast.makeText(this, "Scanned -> " + result.contents, Toast.LENGTH_SHORT)
                    .show()
<<<<<<< Updated upstream
                textView.text = String.format("Scanned Result: %s", result)

=======
                val textView: TextView = findViewById(R.id.textView23)
                textView.text = 0.02.toString()
>>>>>>> Stashed changes
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun openGoogleMaps() {
        // Specify the latitude and longitude of the location you want to open in Google Maps
        val latitude = 37.7749
        val longitude = -122.4194

        // Create a URI to construct the Google Maps URL
        val uri = Uri.parse("geo:$latitude,$longitude")

        // Create an Intent with the ACTION_VIEW action and set the data URI
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)

        // Set the package to ensure Google Maps is used
        mapIntent.setPackage("com.google.android.apps.maps")

        // Check if an app can handle the Intent
        if (mapIntent.resolveActivity(packageManager) != null) {
            // Start the activity
            startActivity(mapIntent)
        }
    }
}
