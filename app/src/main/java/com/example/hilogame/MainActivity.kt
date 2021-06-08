package com.example.hilogame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var enteredNumber: EditText
    private lateinit var button: Button
    private lateinit var textResult: TextView
    private var random: Int = 0
    private var count: Int = 0
    private var value: Boolean = false

    private fun newGame(): Int{
        random = Random.nextInt(1,100)
        return random
    }


    private fun guessGame() {
        var message = ""
        count++

        try {
            val guessNum = enteredNumber.text.toString().toInt()

            when{
                random > guessNum -> message = "$guessNum is to low. Try again!"
                random < guessNum -> message = "$guessNum is too hi. Try again!"
                else -> { message = "$guessNum is correct. You win! Let's play again?"
                    value = true
                    newGame()
                }
            }
        }catch (e: Exception){
            message = "Enter a whole number between 1 and 100."
        }finally {
            textResult.text = message
            enteredNumber.requestFocus()
            enteredNumber.selectAll()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enteredNumber = findViewById(R.id.enteredNumber)
        button = findViewById(R.id.button)
        textResult = findViewById(R.id.textResult)

        newGame()
    }
    fun buttonOnClick(view: View){
        if (!value){
            (view as? Button)?.text = "Guess!"
            count = 0
            guessGame()
        }
        if (value) {
            (view as? Button)?.text = "Again?"
            val countToast = Toast.makeText(this, "${enteredNumber.text} is correct! You win after $count tries!", Toast.LENGTH_LONG)
            countToast.show()
            value = false
            enteredNumber.text = null
        }
    }
}