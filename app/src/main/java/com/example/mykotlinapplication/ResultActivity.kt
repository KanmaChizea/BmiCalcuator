package com.example.mykotlinapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.math.log

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
         lateinit var age: String
        lateinit var height: String
        lateinit var weight: String
        lateinit var bmi: String
        val bundle = intent.extras
bundle?.let {
                age = bundle.getString("AGE") ?: "0"
                weight = bundle.getString("WEIGHT") ?: "0"
                 height = bundle.getString("HEIGHT") ?: "0"
                 bmi = bundle.getString("BMI") ?: "0"
            }
            val ageTextView = findViewById<TextView>(R.id.tvAgeValue)
            val weightTextView = findViewById<TextView>(R.id.tvWeightValue)
            val heightTextView = findViewById<TextView>(R.id.tvHeightValue)
            val bmiTextView = findViewById<TextView>(R.id.tvIndex)
            val descriptionTextView = findViewById<TextView>(R.id.tvDescription)

            ageTextView.text = age
            weightTextView.text = weight
            heightTextView.text = height
            bmiTextView.text = String.format("%.2f",bmi.toFloat())
            descriptionTextView.text = getDescription(bmi!!.toFloat())


        val retryButton = findViewById<Button>(R.id.btnRetry)
        retryButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }


    private fun getDescription(bmi:Float):String{
       return when{
            bmi < 18.5 -> "Underweight"
           bmi in 18.5..24.9 -> "Normal weight"
           bmi in 25.0..29.9 -> "Overweight"
           else -> "Obese"
        }
    }
}