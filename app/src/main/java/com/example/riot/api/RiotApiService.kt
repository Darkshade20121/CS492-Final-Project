package com.example.riot.api

import android.accounts.Account
import com.example.riot.data.Match
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotApiService {
//    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
//    suspend fun getMatchIdsByPuuid(
//        @Path("puuid") puuid: String,
//        @Query("start") start: Int,
//        @Query("count") count: Int,
//        @Query("api_key") apiKey: String
//    ): List<String>

    //https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/vasuleronesdevlo/69696?api_key=RGAPI-d27e6c1d-cb9b-42c3-847b-0ec34762334b
    @GET("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getAccountByRiotId(
        @Path("gameName") gameName: String,
        @Path("tagLine") tagLine: String,
        @Query("api_key") apiKey: String
    ): Account

//    @GET("/lol/match/v5/matches/{matchId}")
//    suspend fun getMatchDetailsByMatchId(
//        @Path("matchId") matchId: String,
//        @Query("api_key") apiKey: String
//    ): Match
}