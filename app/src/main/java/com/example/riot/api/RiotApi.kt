package com.example.riot.api

import android.accounts.Account
import android.util.Log
import com.example.riot.api.RiotApiService
import com.example.riot.data.MatchData
import com.example.riot.data.MatchInfo
import com.example.riot.data.MatchResponse
import com.example.riot.data.Player
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

    private lateinit var matchHistory: MatchResponse

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.henrikdev.xyz")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(RiotApiService::class.java)
    }

    suspend fun getMatchHistoryByNameAndTag(gameName: String, tagLine: String): MatchResponse {
        matchHistory = apiService.getMatchHistoryByNameAndTag(gameName, tagLine)
        return matchHistory
    }

    //

    suspend fun getMatchInfo(): List<MatchInfo> {
        return matchHistory.data.map { matchData ->
            val players = matchData.players.all_players.map { player -> "${player.name}#${player.tag}" }
            val kills = matchData.players.all_players.map { player -> player.stats.kills }
            val deaths = matchData.players.all_players.map { player -> player.stats.deaths }
            val team = matchData.players.all_players.map { player -> player.team }
            MatchInfo(matchData.metadata.map, players, kills, deaths, team)
        }
    }

}
