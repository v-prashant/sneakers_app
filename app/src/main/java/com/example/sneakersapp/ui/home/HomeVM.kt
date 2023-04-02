package com.example.sneakersapp.ui.home

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.sneakersapp.api.Resource
import com.example.sneakersapp.base.BaseViewModel
import com.example.sneakersapp.network.response.GetCartResponse
import com.example.sneakersapp.network.response.GetSneakerResponse
import com.example.sneakersapp.ui.cart_details.CartItem
import com.example.sneakersapp.utils.common.setSuccess
import javax.inject.Inject

class HomeVM @Inject constructor(val repository: HomeRepository) :
    BaseViewModel() {

    val getSneakerLiveData = MutableLiveData<Resource<GetSneakerResponse>>()
    val getCartLiveData = MutableLiveData<Resource<GetCartResponse>>()

    val tempList = ArrayList<SneakerItem>()
    var dataList = ArrayList<SneakerItem>()
    lateinit var cartList: ArrayList<CartItem>
    var sneaker: SneakerItem? = null

    lateinit var bundle: Bundle

    fun getSneakerList() {
        val response = repository.getSneakerList()
        getSneakerLiveData.setSuccess(response)
    }

    fun getCartList() {
        val response = repository.getCartList()
        getCartLiveData.setSuccess(response)
    }

}