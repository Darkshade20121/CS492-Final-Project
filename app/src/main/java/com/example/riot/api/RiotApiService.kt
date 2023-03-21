package com.example.riot.api

import com.example.riot.data.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApiService {
    @GET("/valorant/v3/matches/na/{gameName}/{tagLine}")
    suspend fun getMatchHistoryByNameAndTag(
        @Path("gameName") gameName: String,
        @Path("tagLine") tagLine: String,
    ): MatchResponse
}

