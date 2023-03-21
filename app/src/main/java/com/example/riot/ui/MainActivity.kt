package com.example.riot.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
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


        // Set up the Riot logo relink
        val riotLogoRelink = findViewById<ImageView>(R.id.riotLogoRelink)
        riotLogoRelink.setOnClickListener {
            val uri = Uri.parse("https://www.riotgames.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}


// Player name
    // Player Picture
// Map1 - Name
    //Players
        // Agent
        // KDA
    // Win

// Map2 - Name
// Map3 - Name
// Map4 - Name
// Map5 - Name

//GlobalScope.launch(Dispatchers.Main) {
//    try {
//        val matches = withContext(Dispatchers.IO) {
//            riotAdapter.getMatchHistory("vasuleronesdevlo", "69696")
//        }
//
//        // Iterate though the maps
////                for (match in matches.data) {
////                    val map = match.metadata.map
////                    Log.d("Maps", map)
////                }
//        Log.d("HERE", riotApi.getMatchInfo().toString())
//
//
//
//        Log.d("MainActivity", "Match: $matches")
//    } catch (e: Exception) {
//        Log.e("MainActivity", "Error getting match history: ${e.message}", e)
//    }
//}

