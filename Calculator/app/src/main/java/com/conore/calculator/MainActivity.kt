package com.conore.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        /* Finds text content of view (button in this case)
        and appends the value to the input text view
        if the value is null, nothing happens */
        tvInput?.append((view as Button).text)
    }

    //Sets text view content to an empty string
    fun onClear(view: View) {
        tvInput?.text = ""
    }
}