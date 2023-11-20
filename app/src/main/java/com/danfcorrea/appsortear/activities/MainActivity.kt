package com.danfcorrea.appsortear.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.danfcorrea.appsortear.R
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var minValue by Delegates.notNull<Int>()
    private var maxValue by Delegates.notNull<Int>()
    private var amountOfNumbers by Delegates.notNull<Int>()
    private lateinit var randomOrNot: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun generate(view: View) {
        if (validateValues()){
            loadValues()
            if(maxValue < minValue)
                Toast.makeText(baseContext, "Valor mínimo deve ser menor que Valor máximo", Toast.LENGTH_SHORT).show()
            else if (maxValue - minValue < amountOfNumbers-1 && randomOrNot.isChecked)
                Toast.makeText(baseContext, "Não é possível gerar essa quantidade de números sem repetir", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    if (randomOrNot.isChecked)
                        putExtra("NUMBER_LIST", generateValuesNoRepeat(amountOfNumbers, minValue, maxValue) as ArrayList)
                    else
                        putExtra("NUMBER_LIST", generateValues(amountOfNumbers, minValue, maxValue) as ArrayList)
                    }
                startActivity(intent)
            }
        }else{
            Toast.makeText(baseContext, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadValues(){
        minValue = findViewById<EditText>(R.id.minvalue).text.toString().toInt()
        maxValue = findViewById<EditText>(R.id.maxvalue).text.toString().toInt()
        amountOfNumbers = findViewById<EditText>(R.id.amountofnumbers).text.toString().toInt()
        randomOrNot = findViewById(R.id.randomSwitch)
    }

    private fun validateValues() : Boolean{
        val min = findViewById<EditText>(R.id.minvalue).text
        val max = findViewById<EditText>(R.id.maxvalue).text
        val amount = findViewById<EditText>(R.id.amountofnumbers).text

        if(min.isEmpty() || max.isEmpty() || amount.isEmpty())
            return false

        return true
    }

    private fun generateValues(size: Int, min: Int, max: Int): MutableList<Int> {
        val randomList = mutableListOf<Int>()
        while (randomList.size < size) {
            val randomNumber = Random.nextInt(min, max+1)
            randomList.add(randomNumber)
        }
        return randomList
    }

    private fun generateValuesNoRepeat(size: Int, min: Int, max: Int): MutableList<Int> {
        val randomList = mutableListOf<Int>()
        while (randomList.size < size) {
            val randomNumber = Random.nextInt(min, max+1)
            if (!randomList.contains(randomNumber)) {
                randomList.add(randomNumber)
            }
        }
        return randomList
    }
}