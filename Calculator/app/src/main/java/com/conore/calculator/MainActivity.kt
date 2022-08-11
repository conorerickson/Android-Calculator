package com.conore.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDecimalPoint : Boolean = false


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
        lastNumeric = true
        lastDecimalPoint = false
    }



    //Sets text view content to an empty string
    fun onClear(view: View) {
        tvInput?.text = ""
    }



    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDecimalPoint) {
            tvInput?.append(".")
            lastNumeric = false
            lastDecimalPoint = true
        }
    }



    fun onOperator(view: View) {
        /*Checks if there is an operator and
        disables ability to append more operators */

        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDecimalPoint = false
            }
        }
    }



    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {

                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if (tvValue.contains("+")) {

                    if (tvValue.startsWith("-")) {
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }
                    if (tvValue.contains("+")) {

                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                } else if (tvValue.contains("*"))

                    if (tvValue.startsWith("-")) {
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }
                if (tvValue.contains("*")) {

                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                } else {
                    if (tvValue.startsWith("/")) {
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }
                    if (tvValue.contains("/")) {

                        val splitValue = tvValue.split("/")
                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                }

            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }



    private fun removeZeroAfterDot(result : String): String {
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length -2)
        }
        return value
    }



    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

}