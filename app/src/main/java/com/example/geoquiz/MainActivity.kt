package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var questions: ArrayList<Question>
    lateinit var answered: ArrayList<Boolean>
    var position = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showScore()
        loadQuestions()
        setupViews()
    }

    fun loadQuestions() {
        questions = ArrayList()
        var question = Question("¿Es Lima la capital de Perú?", true)
        questions.add(question)

        question = Question("¿Es Lima la capital de Chile?", false)
        questions.add(question)

        question = Question("¿Es Caracas la capital de Chile?", false)
        questions.add(question)

        question = Question("¿Es Caracas la capital de Venezuela?", true)
        questions.add(question)

        answered = ArrayList()
        for(i in 0 until questions.size){
            answered.add(false)
        }
    }

    fun setupViews() {
        tvPregunta.text = questions[position].sentence
        showSentences()
        showScore()

       // val btYes = findViewById<Button>(R.id.btYes) forma tradicional
        btYes.setOnClickListener {
            if (questions[position].answer){ //==true
                if (!answered[position]){
                    Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
                    answered[position] = true
                    score++
                    showScore()
                }
            }
            else{
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
            }
        }
        //val btNo = findViewById<Button>(R.id.btNo)
        btNo.setOnClickListener {
            if (questions[position].answer==false){
                if (!answered[position]) {
                    Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
                    answered[position] = true
                    score++
                    showScore()
                }
            }
            else{
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
            }

        }

        btNext.setOnClickListener {
            if (position < questions.size-1) {
                position++
                showSentences()
            }
        }

        btPrev.setOnClickListener {
            if (position > 0){
                position--
                showSentences()
            }
        }
    }

    private fun showSentences() {
        tvPregunta.text = questions[position].sentence
        if (position == 0)
            disableButton(btPrev)
        else
            enableButton(btPrev)
        if (position == questions.size-1)
            disableButton(btNext)
        else
            enableButton(btNext)
    }

    fun showScore(){
        tvAciertosCount.text = score.toString()
    }

    fun disableButton(bt: Button){
        bt.isEnabled = false
        bt.isClickable = false
        bt.setBackgroundColor(ContextCompat.getColor(this, R.color.material_on_background_disabled))
        bt.setTextColor(ContextCompat.getColor(this, R.color.grey))
    }

    fun enableButton(bt: Button){
        bt.isEnabled = true
        bt.isClickable = true
        bt.setBackgroundColor(ContextCompat.getColor(this, R.color.navy_blue))
        bt.setTextColor(ContextCompat.getColor(this, R.color.white))
    }


}