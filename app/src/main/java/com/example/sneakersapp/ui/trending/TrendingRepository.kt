package com.example.sneakersapp.ui.trending

import com.example.sneakersapp.api.APIService
import javax.inject.Inject

class TrendingRepository @Inject constructor(private val apiService: APIService) {
     fun getRepositories() = apiService.getRepositories("stars", "100")

}