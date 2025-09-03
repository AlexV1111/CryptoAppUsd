package com.myapplication.presentation

import com.myapplication.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.myapplication.CoinApp
import com.myapplication.databinding.ActivityCoinPrceListBinding
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPrceListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[CoinViewModel::class.java]
    }

    private val component by lazy { (application as CoinApp).component }


    private lateinit var adapter: CoinInfoAdapter
    private lateinit var fromSymbol: String

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.priceList().observe(this, Observer { adapter.submitList(it) })

        setupRecyclerView()
    }

    private fun onePaneMode(): Boolean {
        return binding.coinInfoContainer == null
    }


    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.coin_info_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        adapter = CoinInfoAdapter()
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {

            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                fromSymbol = coinPriceInfo.fromSymbol
                if (onePaneMode()) {
                    val intent = CoinDetailActivity.newIntent(
                        this@CoinPriceListActivity,
                        fromSymbol
                    )
                    startActivity(intent)
                } else {
                    launchFragment(CoinDetailFragment.newInstance(fromSymbol))
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
    }

}
