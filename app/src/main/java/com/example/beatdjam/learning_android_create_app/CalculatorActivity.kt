package com.example.beatdjam.learning_android_create_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        findViewById<Button>(R.id.calculate).setOnClickListener {
            val priceEditText = findViewById<EditText>(R.id.price)
            val isValidPrice = validEditText(priceEditText, getString(R.string.price_error))

            val discountEditText = findViewById<EditText>(R.id.discount)
            val isValidDiscount = validEditText(discountEditText, getString(R.string.discount_error))

            if (isValidPrice && isValidDiscount) {
                Intent(this, CalculatorResultActivity::class.java).also {
                    it.putExtra(CalculatorResultActivity.PRICE, priceEditText.toInt())
                    it.putExtra(CalculatorResultActivity.DISCOUNT, discountEditText.toInt())
                    startActivity(it)
                }
            }
        }
    }

    private fun validEditText(editText: EditText, errorMessage : String? = "error") : Boolean {
        val text = editText.text.toString()
        if (text.isEmpty()) {
            editText.error = errorMessage
        }
        return !text.isEmpty()
    }

    private fun EditText.toInt () : Int = this.text.toString().toInt()
}
