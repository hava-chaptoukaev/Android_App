package com.example.mycalulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        btnOne.setOnClickListener { appendExpr("1", true) }
        btnTwo.setOnClickListener { appendExpr("2", true) }
        btnThree.setOnClickListener { appendExpr("3", true) }
        btnFour.setOnClickListener { appendExpr("4", true) }
        btnFive.setOnClickListener { appendExpr("5", true) }
        btnSix.setOnClickListener { appendExpr("6", true) }
        btnSeven.setOnClickListener { appendExpr("7", true) }
        btnEight.setOnClickListener { appendExpr("8", true) }
        btnNine.setOnClickListener { appendExpr("9", true) }
        btnZero.setOnClickListener { appendExpr("0", true) }
        btnPoint.setOnClickListener { appendExpr(".", true) }

        //Operators
        btnAdd.setOnClickListener { appendExpr("+", false) }
        btnSubstract.setOnClickListener { appendExpr("-", false) }
        btnMultiply.setOnClickListener { appendExpr("*", false) }
        btnDivide.setOnClickListener { appendExpr("/", false) }
        btnOpen.setOnClickListener { appendExpr("(", false) }
        btnClose.setOnClickListener { appendExpr(")", false) }
        btnPower.setOnClickListener { appendExpr("^", false) }
        btnSqrt.setOnClickListener { appendExpr("sqrt", false) }

        btnClear.setOnClickListener {
            txtExpression.text = ""
            txtResult.text = ""
        }


        btnEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(txtExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result==longResult.toDouble())
                    txtResult.text = longResult.toString()
                else
                    txtResult.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
                txtResult.text= "Error"
            }
        }

    }

    fun appendExpr(string: String, canClear: Boolean) {

        if(txtResult.text.isNotEmpty()){
            txtExpression.text = ""
        }
        if (canClear) {
            txtResult.text = ""
            txtExpression.append(string)
        } else {
            txtExpression.append(txtResult.text)
            txtExpression.append(string)
            txtResult.text = ""
        }
    }
}
