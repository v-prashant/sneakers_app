package com.example.sneakersapp.ui.trending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.api.Resource
import com.example.sneakersapp.base.BaseViewModel
import com.example.sneakersapp.network.response.GetRepositoriesResponse
import com.example.sneakersapp.utils.common.setError
import com.example.sneakersapp.utils.common.setLoading
import com.example.sneakersapp.utils.common.setSuccess
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