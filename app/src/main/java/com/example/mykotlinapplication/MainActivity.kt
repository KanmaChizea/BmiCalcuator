package com.example.mykotlinapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

class MainActivity : AppCompatActivity() {
   private lateinit var ageEditText : EditText
    private lateinit var weightEditText : EditText
    private lateinit var heightEditText : EditText
    private lateinit var age:String
    private lateinit var weight:String
    private lateinit var height:String
    private var gender = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //select gender
        val maleButton = findViewById<ImageButton>(R.id.btnMale)
        val femaleButton = findViewById<ImageButton>(R.id.btnFemale)
        maleButton.setOnClickListener {
            gender = true
            maleButton.background = ContextCompat.getDrawable(this,R.drawable.border)
            femaleButton.setBackgroundResource(0)
        }
        femaleButton.setOnClickListener {
            gender = true
            femaleButton.background = ContextCompat.getDrawable(this,R.drawable.border)
            maleButton.setBackgroundResource(0)
        }

        //calculate
        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        calculateButton.setOnClickListener {
             ageEditText = findViewById(R.id.etAge)
            weightEditText = findViewById(R.id.etWeight)
            heightEditText = findViewById(R.id.etHeight)
             age = ageEditText.text.toString()
            weight = weightEditText.text.toString()
            height = heightEditText.text.toString()
            if(validate()) {
                val bmi = weight.toFloat() / ((height.toFloat() * height.toFloat()) / 10000)
                val bundle = Bundle()
                bundle.putString("AGE", age)
                bundle.putString("WEIGHT", weight)
                bundle.putString("HEIGHT", height)
                bundle.putString("BMI", bmi.toString())
               val intent = Intent(this, ResultActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent, bundle)
            }

        }

    }


    private fun validate() : Boolean {

        val errorAge = findViewById<TextView>(R.id.errorAge)
        val errorHeight = findViewById<TextView>(R.id.errorHeight)
        val errorWeight = findViewById<TextView>(R.id.errorWeight)
        val fields = arrayListOf(true, true, true,gender)

        //handle gender
        if(!gender){
            Toast.makeText(this,"Select your gender", Toast.LENGTH_SHORT).show()
        }

        //handle age
        if (age == "") {
            setErrorState(errorAge, ageEditText)
            fields[0] = false
        } else {
            fields[0] = true
            clearErrorState(errorAge,ageEditText)
        }

        //handle height
        if (height == "") {
            setErrorState(errorHeight, heightEditText)
            fields[1] = false
        } else {
            fields[1] = true
            clearErrorState(errorHeight,heightEditText)
        }

        //handle weight
        if (weight == "") {
            setErrorState(errorWeight, weightEditText)
            fields[2] = false
        } else {
            fields[2] = true
            clearErrorState(errorWeight,weightEditText)
        }
      return  when(fields.contains(false)){
            true -> false
            false -> true
        }


    }

    private fun setErrorState(tv: TextView, et:EditText){
        val errorText = "Field cannot be empty"
        DrawableCompat.setTint(
            et.background,
            ContextCompat.getColor(this, R.color.error)
        )
        tv.text = errorText
    }

    private fun clearErrorState(tv: TextView, et:EditText){
        DrawableCompat.setTint(
            et.background,
            ContextCompat.getColor(this, R.color.white)
        )
        tv.text = ""
    }
}
