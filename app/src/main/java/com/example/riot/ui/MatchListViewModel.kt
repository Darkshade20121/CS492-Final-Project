package com.example.riot.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riot.api.RiotApi
import com.example.riot.data.MatchData
import kotlinx.coroutines.*
import androidx.lifecycle.viewModelScope

class MatchListViewModel(private val riotApi: RiotApi) : ViewModel() {
    private val _matchList = MutableLiveData<List<MatchData>>(emptyList())
    val matchList: LiveData<List<MatchData>> = _matchList

    suspend fun updateMatchList(gameName: String, tagLine: String) {
        val matches = riotApi.getMatchHistoryByNameAndTag(gameName, tagLine).data
        _matchList.postValue(matches)

    }
}
