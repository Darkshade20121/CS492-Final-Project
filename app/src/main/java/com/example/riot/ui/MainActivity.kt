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

        riotApi = RiotApi(apiKey = "RGAPI-d27e6c1d-cb9b-42c3-847b-0ec34762334b")
        riotAdapter = RiotAdapter(riotApi)

        GlobalScope.launch(Dispatchers.Main) {
            val account = withContext(Dispatchers.IO) {
                riotAdapter.getAccount("vasuleronesdevlo", "69696")
            }

            Log.d("MainActivity", "Account: $account")
        }
    }
}
