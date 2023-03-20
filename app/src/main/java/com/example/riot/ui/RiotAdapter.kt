package com.example.riot.ui

import com.example.riot.api.RiotApi
import com.example.riot.data.Match

class RiotAdapter(private val riotApi: RiotApi) {
    suspend fun getMatchHistory(gameName: String, tagLine: String): Match {
        return riotApi.getMatchHistoryByNameAndTag(gameName, tagLine)
    }

}
