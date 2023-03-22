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



        }else{
            Log.d("INTENT IS NULL BUT:", "BUT:")
            val name = intent.getStringExtra("EXTRA_NAME")
            Log.d("MAIN NAME", name.toString())

        }
    }
}
