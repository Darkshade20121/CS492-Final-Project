package com.example.riot.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.riot.R
import com.example.riot.api.RiotApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : Activity() {

    private lateinit var riotApi: RiotApi
    private lateinit var riotAdapter: RiotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        riotApi = RiotApi()
        riotAdapter = RiotAdapter(riotApi)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val matches = withContext(Dispatchers.IO) {
                    riotAdapter.getMatchHistory("vasuleronesdevlo", "69696")
                }

                // Iterate though the maps
                for (match in matches.data) {
                    val map = match.metadata.map
                    Log.d("Maps", map)
                }

                Log.d("MainActivity", "Match: $matches")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error getting match history: ${e.message}", e)
            }
        }
    }
}
