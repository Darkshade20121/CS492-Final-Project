package com.example.riot.ui

import com.example.riot.api.RiotApi
import android.accounts.Account

class RiotAdapter(private val riotApi: RiotApi) {
    suspend fun getAccount(gameName: String, tagLine: String): Account {
        return riotApi.getAccountByRiotId(gameName, tagLine)
    }
}
