package com.example.mykotlinapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //select gender
        val maleButton = findViewById<ImageButton>(R.id.btnMale)
        val femaleButton = findViewById<ImageButton>(R.id.btnFemale)
        maleButton.setOnClickListener {
            maleButton.background = ContextCompat.getDrawable(this,R.drawable.border)
            femaleButton.setBackgroundResource(0)
        }
        femaleButton.setOnClickListener {
            femaleButton.background = ContextCompat.getDrawable(this,R.drawable.border)
            maleButton.setBackgroundResource(0)
        }

        //calculate
        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        calculateButton.setOnClickListener {
            val ageTextView = findViewById<EditText>(R.id.etAge)
            val weightTextView = findViewById<EditText>(R.id.etWeight)
            val heightTextView = findViewById<EditText>(R.id.etHeight)
            val age = ageTextView.text.toString()
            val weight = weightTextView.text.toString()
            val height = heightTextView.text.toString()
            val bmi = weight.toFloat()/((height.toFloat() * height.toFloat()) / 10000)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("AGE", age)

        }

    }
}