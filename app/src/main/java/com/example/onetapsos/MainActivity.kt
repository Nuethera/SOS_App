package com.example.onetapsos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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



    }
}