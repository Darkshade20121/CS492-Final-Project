package com.example.riot.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.riot.R
import com.example.riot.data.MatchData
import java.io.Serializable

class StatsActivity : AppCompatActivity() {
    private var matchData: MatchData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        // TODO: Add your implementation for displaying stats data here
    }
}
