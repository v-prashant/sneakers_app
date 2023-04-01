package com.example.sneakersapp.network.response

import com.example.sneakersapp.network.BaseResponse
import com.example.sneakersapp.ui.home.SneakerItem

data class GetSneakerResponse(val items: ArrayList<SneakerItem>) : BaseResponse()
