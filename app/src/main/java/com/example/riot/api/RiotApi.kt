package com.example.riot.api

import android.accounts.Account
import android.util.Log
import com.example.riot.api.RiotApiService
import com.example.riot.data.Match
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This api to find the different paths to use since riot has like 300 different requests.
//https://docs.henrikdev.xyz/valorant.html
//https://api.henrikdev.xyz/valorant/v3/matches/na/Vasuleronesdevlo/69696

//https://api.henrikdev.xyz

// Get Riot PUUID using Name and Tag
///riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}
// Take PUID and get match history
class RiotApi() {

    val apiService: RiotApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.henrikdev.xyz")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(RiotApiService::class.java)
    }

    suspend fun getMatchHistoryByNameAndTag(gameName: String, tagLine: String): Match {
        return apiService.getMatchHistoryByNameAndTag(gameName, tagLine)
    }
}
