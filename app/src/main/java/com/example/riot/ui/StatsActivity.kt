package com.example.riot.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R
import com.example.riot.data.MatchData

class StatsActivity : AppCompatActivity() {

    private lateinit var statsAdapter: StatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val name = intent.getStringExtra("EXTRA_NAME")
        Log.d("MAIN NAME", name.toString())


        val matches = intent.getSerializableExtra(EXTRA_MATCH) as? List<MatchData>
        val filteredMatches = matches?.filter { match ->
            match.players.all_players.any { player ->
                player.name.toString() == name.toString()
            }
        } ?: emptyList()

        

        Log.d("FILTERED", filteredMatches.toString())


//        val recyclerView: RecyclerView = findViewById(R.id.stats_list)
//        statsAdapter = StatsAdapter(filteredMatches)
//        recyclerView.adapter = statsAdapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
