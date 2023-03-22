package com.example.riot.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R
import com.example.riot.data.MatchData
import com.google.android.material.snackbar.Snackbar

const val EXTRA_MATCH = "MATCH"

class MatchDetailActivity : AppCompatActivity() {
    private var matchData: MatchData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        Log.d("MatchDetailActivity", "In here")
        /*
         * If an intent was used to launch this activity and it contains information about a
         * Match, use that information to populate the UI.
         */
        if (intent != null && intent.hasExtra(EXTRA_MATCH)) {
            matchData = intent.getSerializableExtra(EXTRA_MATCH) as MatchData


            Log.d("This is the intent", intent.toString())

            // Set the map name
            findViewById<TextView>(R.id.tv_match_map).text = matchData!!.metadata.map

            // Set the number of kills

           // findViewById<TextView>(R.id.tv_match_kills).text = "Kills: ${matchData!!.metadata.}"

            // Set the number of deaths
           // findViewById<TextView>(R.id.tv_match_deaths).text = "Deaths: ${matchData!!.info.deaths}"

            // Create a list of player names and their KDA
            val teamColor = mutableListOf<String>()
            val players = mutableListOf<String>()
            val kills = mutableListOf<Int>()
            val deaths = mutableListOf<Int>()
            val assists = mutableListOf<Int>()
            val blueScore = matchData!!.teams.blue.rounds_won
            val redScore = matchData!!.teams.red.rounds_won

            for (player in matchData!!.players.all_players) {
//                players.add("${player.name} - KDA: ${player.stats.kills}/${player.stats.deaths}/${player.stats.assists}")
                teamColor.add(player.team)
                players.add(player.name)
                kills.add(player.stats.kills)
                deaths.add(player.stats.deaths)
                assists.add(player.stats.assists)
            }


            // Set the list of players
            //val recyclerView: RecyclerView = findViewById(R.id.tv_red_team_players)
            //recyclerView.layoutManager = LinearLayoutManager(this)

//            val adapter = PlayerListAdapter(players)
//            recyclerView.adapter = adapter


        }
    }

}
