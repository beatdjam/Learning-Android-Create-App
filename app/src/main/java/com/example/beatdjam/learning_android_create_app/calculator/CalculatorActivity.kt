package com.example.beatdjam.learning_android_create_app.calculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.beatdjam.learning_android_create_app.R
import kotlinx.android.synthetic.main.activity_calculator.calculate
import kotlinx.android.synthetic.main.activity_calculator.discount
import kotlinx.android.synthetic.main.activity_calculator.price

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        calculate.setOnClickListener {
            val isValidPrice = validEditText(price, getString(R.string.price_error))
            val isValidDiscount = validEditText(discount, getString(R.string.discount_error))

            if (isValidPrice && isValidDiscount) {
                Intent(this, CalculatorResultActivity::class.java).also {
                    it.putExtra(CalculatorResultActivity.PRICE, price.toInt())
                    it.putExtra(CalculatorResultActivity.DISCOUNT, discount.toInt())
                    startActivity(it)
                }
            }
        }
    }

    private fun validEditText(editText: EditText, errorMessage : String? = "error") : Boolean {
        return editText.text.toString().let {
            if (it.isEmpty()) {
                editText.error = errorMessage
            }
            return@let !it.isEmpty()
        }
    }

    private fun EditText.toInt () : Int = this.text.toString().toInt()
}
