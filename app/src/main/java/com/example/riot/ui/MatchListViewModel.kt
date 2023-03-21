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

    fun updateMatchList(gameName: String, tagLine: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val matches = riotApi.getMatchHistoryByNameAndTag(gameName, tagLine).data
                _matchList.postValue(matches)
            } catch (e: Exception) {
                Log.e("MatchListViewModel", "Error getting match history: ${e.message}", e)
            }
        }
    }
}
