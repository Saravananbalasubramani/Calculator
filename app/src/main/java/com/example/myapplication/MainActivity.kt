package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvinput:TextView?=null
    var lastnumber:Boolean=false
    var lastdot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput=findViewById(R.id.tvinput)
    }
    fun ondigit(view:View) {
        tvinput?.append((view as Button).text)
        lastnumber=true
        lastdot=false
    }
    fun unclear(view: View) {
        tvinput?.text=" "
    }
    fun ondecimalview(view: View) {
        if (lastnumber && !lastdot){
            tvinput?.append(".")
            lastnumber=false
            lastdot=true
        }
    }
    fun onoperator(view:View) {
        tvinput?.text?.let {
            if (lastnumber && !isoperation(it.toString())){
                tvinput?.append((view as Button).text)
                lastnumber=false
                lastdot=false
            }
        }

    }
    private fun isoperation(value:String):Boolean {
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    || value.contains("+")
                    || value.contains("*")
                    || value.contains("-")
        }


    }
    private fun removezero(result:String):String{
        var value=result
        if (result.contains(".0"))
             value=result.substring(0,result.length-2)
        return value
    }
    fun inequal(view:View) {
        if(lastnumber){
            var tvvalue=tvinput?.text.toString()
            var prefix=""
            try{
                if(tvvalue.startsWith("-")){
                    prefix="-"
                    tvvalue=tvvalue.substring(1)
                }
                if (tvvalue.contains("-")) {
                    val splitvalue=tvvalue.split("-")
                    var one=splitvalue[0]
                    var two=splitvalue[1]
                    if(prefix.isNotEmpty()) {
                        one=prefix+one
                    }
                    var result=one.toDouble()-two.toDouble()
                    tvinput?.text=result.toString()
                }
                else if (tvvalue.contains("+")) {
                    val splitvalue=tvvalue.split("+")
                    var one=splitvalue[0]
                    var two=splitvalue[1]
                    if(prefix.isNotEmpty()) {
                        one=prefix+one
                    }
                    var result=one.toDouble()+two.toDouble()
                    tvinput?.text= removezero(result.toString())
                }
                else if (tvvalue.contains("/")) {
                    val splitvalue=tvvalue.split("/")
                    var one=splitvalue[0]
                    var two=splitvalue[1]
                    if(prefix.isNotEmpty()) {
                        one=prefix+one
                    }
                    var result=one.toDouble()/two.toDouble()
                    tvinput?.text= removezero(result.toString())
                }
                else if (tvvalue.contains("*")) {
                    val splitvalue=tvvalue.split("*")
                    var one=splitvalue[0]
                    var two=splitvalue[1]
                    if(prefix.isNotEmpty()) {
                        one=prefix+one
                    }
                    var result=one.toDouble()*two.toDouble()
                    tvinput?.text=removezero(result.toString())
                }
            }catch (e:ArithmeticException)
                {
                    e.printStackTrace()
                }
            }
        }

    }
