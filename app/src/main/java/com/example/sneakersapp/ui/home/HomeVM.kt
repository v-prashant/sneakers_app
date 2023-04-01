package com.example.sneakersapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.sneakersapp.api.Resource
import com.example.sneakersapp.base.BaseViewModel
import com.example.sneakersapp.network.response.GetSneakerResponse
import com.example.sneakersapp.utils.common.setSuccess
import javax.inject.Inject

class HomeVM @Inject constructor(val repository: HomeRepository) :
    BaseViewModel() {

    val getSneakerLiveData = MutableLiveData<Resource<GetSneakerResponse>>()
    val tempList = ArrayList<SneakerItem>()
    var dataList = ArrayList<SneakerItem>()

    fun getSneakerList() {
        val response = repository.getSneakerList()
        getSneakerLiveData.setSuccess(response)
    }

}