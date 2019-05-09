package com.example.beatdjam.learning_android_create_app.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_calculator_result.expression_label
import kotlinx.android.synthetic.main.activity_calculator_result.result_label

class CalculatorResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_result)
        val price = intent.getIntExtra(PRICE, 0)
        val discount = intent.getIntExtra(DISCOUNT, 0)

        expression_label.also {
            it.text = getString(R.string.expression, price, discount)
        }

        result_label.also {
            val discountedPrice = price * (100 - discount) / 100
            it.text = getString(R.string.result, discountedPrice)
        }
    }

    companion object {
        const val PRICE = "price"
        const val DISCOUNT = "discount"
    }
}
