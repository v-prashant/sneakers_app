package com.example.sneakersapp.network.response

import com.example.sneakersapp.network.BaseResponse
import com.example.sneakersapp.ui.cart_details.CartItem
import com.example.sneakersapp.ui.home.SneakerItem

data class GetCartResponse(val items: ArrayList<CartItem>) : BaseResponse()
