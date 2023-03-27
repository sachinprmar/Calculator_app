package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
    private var resText :TextView? = null
    var lastNumeric:Boolean = false // for checking if last digit is numeric
    var lastDot:Boolean = false // for checking if last digit is dot

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resText = findViewById(R.id.tvInput) //Assign text
    }

    fun onDigit(view: View){ //will add digit in main string
        resText?.append((view as Button).text)
        lastDot = false
        lastNumeric = true
    }
    fun onClear(view:View){ // will clear main string
        resText?.text = ""
    }
    fun onDecimalPoint(view: View){ //for decimal point checking there should last digit be an integer
                                    //and no decimal before
        if(lastNumeric && !lastDot){
            resText?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        resText?.text?.let { // text?. because text can be empty
            if(lastNumeric && !isOperatorAdded(it.toString())){// checking if any operator already present
                                                                // in main string
                                                                // it represent resText?.text?.
                resText?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = resText?.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){ //if start with - sign
                    prefix = "-"
                    tvValue = tvValue.substring(1) // Will start with second letter
                    // -99 will be 99

                }
               if(tvValue.contains("-")){
                   val splitValue = tvValue.split("-") // Only use for Strings
                   //Provide Output in Array

                   var one = splitValue[0]
                   var two = splitValue[1]
                   if(prefix.isNotEmpty()){
                       one = prefix + one
                   }

                   resText?.text = removeZero((one.toDouble() - two.toDouble()).toString())
               }
                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+") // Only use for Strings
                    //Provide Output in Array

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    resText?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvValue.contains("X")){
                    val splitValue = tvValue.split("X") // Only use for Strings
                    //Provide Output in Array

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    resText?.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }
                else{
                    val splitValue = tvValue.split("/") // Only use for Strings
                    //Provide Output in Array

                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    resText?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }


            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }
    private fun removeZero(result: String):String{
        var value = result
        if(value.contains(".0")){
            value = value.substring(0,value.length -2)
        }
        return value
    }
    private fun isOperatorAdded(value : String):Boolean{
        return if(value.startsWith("-")){ // if start with - sign than another operator can be added
            false
        }else{
            value.contains("/") || value.contains("X") || value.contains("+")
                    || value.contains("-")
        }
    }
}