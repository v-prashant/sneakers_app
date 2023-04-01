package com.example.sneakersapp.ui.trending

import android.os.Bundle
import android.view.View
import com.example.sneakersapp.R
import com.example.sneakersapp.api.Status
import com.example.sneakersapp.base.BaseActivity
import com.example.sneakersapp.databinding.ActivityTrendingBinding
import com.example.sneakersapp.network.response.GetRepositoriesResponse
import com.example.sneakersapp.utils.SharedPreferenceUtil
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

const val EXPIRE_TIME_STAMP = 7200000
@AndroidEntryPoint
class TrendingActivity : BaseActivity<TrendingVM, ActivityTrendingBinding>() {

    var dataList = ArrayList<TrendingItems>()
    var storedTime:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCacheSystem()
        observeData()
        initViews()
        callRepositoriesApi()
        onClickListener()
    }

    private fun setCacheSystem() {
        val localList = SharedPreferenceUtil.getSharedPrefObject(this, SharedPreferenceUtil.TRENDING_DATA, object : TypeToken<ArrayList<TrendingItems>>() {} )
        storedTime = SharedPreferenceUtil.getLongSharedPreference(this, SharedPreferenceUtil.TRENDING_DATA_TS)
        if(localList != null){
            dataList = localList
        }
    }

    private fun callRepositoriesApi() {
        // dataList will be empty in case of user opening first time app or user has cleared data.
        if(dataList.isEmpty() || (System.currentTimeMillis() - storedTime) > EXPIRE_TIME_STAMP)
            viewModel.getRepositories()
        else
            binding.rvTrendingList.adapter = TrendingAdapter(this, dataList)
    }

    private fun onClickListener() {
        binding.noInternet.btnRetry.setOnClickListener {
            onRetry()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            onRefresh()
        }
    }

    private fun onRefresh() {
        viewModel.getRepositories()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun observeData() {
        viewModel.getRepositoriesLiveData.observe(this){
            when(it.status){
                Status.LOADING->{
                    showSkeltonLoading()
                }
                Status.SUCCESS->{
                    getRepositoriesResponse(it.data)
                    hideSkeltonLoading()
                }
                Status.ERROR->{
                    showErrorMessage(it.data.toString())
                    hideSkeltonLoading()
                }
                Status.THROWABLE -> {
                    showNoInternetMessage()
                    hideSkeltonLoading()
                }
            }
        }
    }

    private fun showSkeltonLoading() {
        with(binding){
            noInternet.root.visibility = View.GONE
            rvTrendingList.visibility = View.GONE
            shimmerLayout.visibility = View.VISIBLE
            shimmerLayout.startShimmerAnimation()
        }
    }

    private fun hideSkeltonLoading() {
        with(binding){
            rvTrendingList.visibility = View.VISIBLE
            shimmerLayout.visibility = View.GONE
            shimmerLayout.stopShimmerAnimation()
        }
    }

    private fun showNoInternetMessage() {
        showErrorMessage(getString(R.string.server_error))
         with(binding){
             rvTrendingList.visibility = View.GONE
             noInternet.root.visibility = View.VISIBLE
         }
    }

    private fun onRetry() {
         viewModel.getRepositories()
    }

    private fun getRepositoriesResponse(res: GetRepositoriesResponse?) {
        with(binding){
            rvTrendingList.visibility = View.VISIBLE
            noInternet.root.visibility = View.GONE
        }
        dataList.clear()
        if(res?.items != null)
            for(item in res.items!!)
                dataList.add(TrendingItems(null, item.name, item.htmlUrl, item.description, item.language, item.watchersCount, item.forksCount))

        SharedPreferenceUtil.setSharedPrefObject(this, SharedPreferenceUtil.TRENDING_DATA, dataList)
        SharedPreferenceUtil.setSharedPreference(this, SharedPreferenceUtil.TRENDING_DATA_TS, System.currentTimeMillis())

        binding.rvTrendingList.adapter = TrendingAdapter(this, dataList)
    }

    private fun initViews() {

    }

    override val layoutId: Int
        get() = R.layout.activity_trending
}
