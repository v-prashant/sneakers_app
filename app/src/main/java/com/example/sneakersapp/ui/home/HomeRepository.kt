package com.example.sneakersapp.ui.home

import com.example.sneakersapp.api.APIService
import com.example.sneakersapp.network.utils.SneakerFunction
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: APIService) {
     fun getSneakerList() = SneakerFunction.getSneakerList()

     fun getCartList() = SneakerFunction.getCartList()

}