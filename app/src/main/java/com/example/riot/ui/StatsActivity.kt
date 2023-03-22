package com.example.riot.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R
import com.example.riot.data.MatchData
import java.io.Serializable

class StatsActivity : AppCompatActivity() {
    private var matchData: MatchData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)


        if (intent != null && intent.hasExtra(EXTRA_MATCH)) {
            val name = intent.getStringExtra("EXTRA_NAME")
            Log.d("MAIN NAME", name.toString())
            matchData = intent.getSerializableExtra(EXTRA_MATCH) as MatchData


            // Set the map name


            findViewById<TextView>(R.id.red_score).text = matchData!!.teams.red.rounds_won.toString()
            findViewById<TextView>(R.id.blue_score).text = matchData!!.teams.blue.rounds_won.toString()


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
            // Set up the RecyclerView with the custom adapter
            val recyclerView: RecyclerView = findViewById(R.id.tv_players)
            recyclerView.layoutManager = LinearLayoutManager(this)

//            val adapter = StatsAdapter()
//            recyclerView.adapter = adapter


        }else{
            Log.d("INTENT IS NULL BUT:", "BUT:")
            val name = intent.getStringExtra("EXTRA_NAME")
            Log.d("MAIN NAME", name.toString())

        }
    }
}
