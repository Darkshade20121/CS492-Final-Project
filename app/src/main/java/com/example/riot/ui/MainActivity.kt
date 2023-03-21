package com.example.riot.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView
import com.example.riot.R
import com.example.riot.api.RiotApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.example.riot.data.MatchData

class MainActivity : AppCompatActivity() {

    private lateinit var riotApi: RiotApi
    private lateinit var riotAdapter: RiotAdapter

    private lateinit var matchListAdapter: MatchListAdapter
    private lateinit var viewModel: MatchListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up RecyclerView and adapter
        val recyclerView: RecyclerView = findViewById(R.id.match_list)
        matchListAdapter = MatchListAdapter(this::onMatchClick)
        recyclerView.adapter = matchListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up ViewModel
        val riotApi = RiotApi()
        viewModel = MatchListViewModel(riotApi)
        viewModel.matchList.observe(this, Observer { matchList ->
            matchListAdapter.updateMatchList(matchList)
        })

        val searchView = findViewById<SearchView>(R.id.idSV)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                var searchQuery = query
                Log.d("Search Query", searchQuery)
                if (searchQuery == "a" || searchQuery == "A") {
                    searchQuery = "vasuleronesdevlo#69696"
                    Log.d("Hello", "IN HERE")
                }


                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        val matches = withContext(Dispatchers.IO) {
                            riotAdapter.getMatchHistory(searchQuery.split("#")[0], searchQuery.split("#")[1])
                        }
                        // TODO: display the match history data in the UI
                        Log.d("MainActivity", "Match history: $matches")
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error getting match history: ${e.message}", e)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        val riotLogoRelink = findViewById<ImageView>(R.id.riotLogoRelink)
        riotLogoRelink.setOnClickListener {
            val uri = Uri.parse("https://www.riotgames.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }
    private fun onMatchClick(matchData: MatchData) {
        // Handle click on match item
        Log.d("MainActivity", "Match clicked: $matchData")
    }
}




