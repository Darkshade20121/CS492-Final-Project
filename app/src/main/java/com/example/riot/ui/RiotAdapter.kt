package com.example.riot.ui

import com.example.riot.api.RiotApi
import com.example.riot.data.MatchResponse

class RiotAdapter(private val riotApi: RiotApi) {
    suspend fun getMatchHistory(gameName: String, tagLine: String): MatchResponse {
        return riotApi.getMatchHistoryByNameAndTag(gameName, tagLine)
    }

}
