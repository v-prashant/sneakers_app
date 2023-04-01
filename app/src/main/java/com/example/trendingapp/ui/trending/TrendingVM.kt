package com.example.trendingapp.ui.trending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trendingapp.api.Resource
import com.example.trendingapp.base.BaseViewModel
import com.example.trendingapp.network.response.GetRepositoriesResponse
import com.example.trendingapp.utils.common.setError
import com.example.trendingapp.utils.common.setLoading
import com.example.trendingapp.utils.common.setSuccess
import com.example.trendingapp.utils.responseSubscribe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrendingVM @Inject constructor(val repository: TrendingRepository) :
    BaseViewModel() {

    val getRepositoriesLiveData = MutableLiveData<Resource<GetRepositoriesResponse>>()
    fun getRepositories() {
        getRepositoriesLiveData.setLoading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getRepositories()
                getRepositoriesLiveData.setSuccess(response.blockingGet())
            } catch (exception: Exception) {
                val response = GetRepositoriesResponse()
                getRepositoriesLiveData.setError(response)
            }
        }
    }

}