package com.example.riot.api

import android.accounts.Account
import android.util.Log
import com.example.riot.api.RiotApiService
import com.example.riot.data.Match
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RiotApi(private val apiKey: String = "RGAPI-d27e6c1d-cb9b-42c3-847b-0ec34762334b") {

    val apiService: RiotApiService

    init {
        val retrofit = Retrofit.Builder()
        ///riot/account/v1/accounts/by-riot-id/vasuleronesdevlo/69696?api_key=RGAPI-d27e6c1d-cb9b-42c3-847b-0ec34762334b
            .baseUrl("https://na1.api.riotgames.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(RiotApiService::class.java)
    }

//    suspend fun getMatchIdsByPuuid(puuid: String, start: Int, count: Int): List<String> {
//        return apiService.getMatchIdsByPuuid(puuid, start, count, apiKey)
//    }
//
//    suspend fun getMatchDetailsByMatchId(matchId: String): Match {
//        return apiService.getMatchDetailsByMatchId(matchId, apiKey)
//    }

    suspend fun getAccountByRiotId(gameName: String, tagLine: String): Account {
        Log.d("Here 1", "Here 1")
        return apiService.getAccountByRiotId(gameName, tagLine, apiKey)
    }
}
