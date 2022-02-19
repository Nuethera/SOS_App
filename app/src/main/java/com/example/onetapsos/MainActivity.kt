package com.example.onetapsos

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val permissions = arrayOf(
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        checkPermission(permissions, 1)

        val PhoneNO1 = findViewById<EditText>(R.id.editTextPhone)
        val SavePh = findViewById<Button>(R.id.button1)
        val Phnview1 = findViewById<TextView>(R.id.textBox)
        val send = findViewById<Button>(R.id.button2)
        val SaveMes = findViewById<Button>(R.id.button3)
        val MessageView = findViewById<TextView>(R.id.textView2)
        val MessageEdit = findViewById<EditText>(R.id.Message)
        val Phnview2 = findViewById<TextView>(R.id.phoneview2)
        val Phnview3 = findViewById<TextView>(R.id.phoneview3)
        val PhoneNO2 = findViewById<EditText>(R.id.editTextPhone2)
        val PhoneNO3 = findViewById<EditText>(R.id.editTextPhone3)
        lateinit var address: String
        lateinit var latitudee: String
        lateinit var longitudee: String
        val iAction = intent.action.toString()

        val sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()






        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                val geocoder = Geocoder(this, Locale.getDefault())
                latitudee = location!!.latitude.toString()
                longitudee = location.longitude.toString()
                val addresses = geocoder.getFromLocation(
                    location!!.latitude,
                    location.longitude,
                    1
                )

                address = addresses[0].getAddressLine(0)
                editor.putString("address", address)
                editor.putString("longitude", longitudee)
                editor.putString("latitude", latitudee)
                editor.apply()
            }
        address = sharedPref.getString("address", "null").toString()
        longitudee = sharedPref.getString("longitude", "null").toString()
        latitudee = sharedPref.getString("latitude", "null").toString()
        val smsManager: SmsManager = SmsManager.getDefault()





        SavePh.setOnClickListener {
            editor.putString("PhoneNO1.", PhoneNO1.text.toString())
            editor.putString("PhoneNO2.", PhoneNO2.text.toString())
            editor.putString("PhoneNO3.", PhoneNO3.text.toString())
            editor.apply();

            Phnview1.text = sharedPref.getString("PhoneNO1.", "1091")
            Phnview2.text = sharedPref.getString("PhoneNO2.", "100")
            Phnview3.text = sharedPref.getString("PhoneNO3.", "101")
        }
        Phnview1.text = sharedPref.getString("PhoneNO1.", "1091")
        Phnview2.text = sharedPref.getString("PhoneNO2.", "100")
        Phnview3.text = sharedPref.getString("PhoneNO3.", "101")

        SaveMes.setOnClickListener {
            editor.putString("Message", MessageEdit.text.toString())
            editor.apply()
            MessageView.text = sharedPref.getString("Message", "Help me please")
        }
        MessageView.text = sharedPref.getString("Message", "Help me please")

        send.setOnClickListener {
            //send message
            try {
               // val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    Phnview1.text.toString(),
                    null,
                    MessageView.text.toString() + "--  \n" + address+"  \n  "+latitudee+"  \n  "+longitudee,
                    null,
                    null
                )
                //val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    Phnview2.text.toString(),
                    null,
                    MessageView.text.toString() + "--  \n" + address+"  \n  "+latitudee+"  \n  "+longitudee,
                    null,
                    null
                )
                //val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    Phnview3.text.toString(),
                    null,
                    MessageView.text.toString() + "--  \n" + address+"  \n  "+latitudee+"  \n  "+longitudee,
                    null,
                    null
                )

                Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Grant Permission", Toast.LENGTH_LONG).show()
            }
        }
        if (iAction != "android.intent.action.MAIN") {

            try {

                smsManager.sendTextMessage(
                    Phnview1.text.toString(),
                    null,
                    MessageView.text.toString() + "--  \n" + address + "  \n  " + latitudee + "  \n  " + longitudee,
                    null,
                    null
                )
                //val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    Phnview2.text.toString(),
                    null,
                    MessageView.text.toString() + "--  \n" + address + "  \n  " + latitudee + "  \n  " + longitudee,
                    null,
                    null
                )
                //val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    Phnview3.text.toString(),
                    null,
                    MessageView.text.toString() + "--  \n" + address + "  \n  " + latitudee + "  \n  " + longitudee,
                    null,
                    null
                )

                Toast.makeText(applicationContext, "Messages Sent", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun checkPermission(permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "SMS Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "SMS Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

}