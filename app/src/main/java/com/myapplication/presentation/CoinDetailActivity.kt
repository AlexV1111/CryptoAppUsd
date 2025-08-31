package com.myapplication.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R


class CoinDetailActivity : AppCompatActivity() {

    private lateinit var fromSymbol: String

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        parseIntent()

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.coin_info_container, CoinDetailFragment.newInstance(fromSymbol))
                .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            throw RuntimeException("Param screen mode is absent")
        } else {
            fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: ""
        }
    }

}
