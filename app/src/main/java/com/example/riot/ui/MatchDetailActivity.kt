package com.example.riot.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.riot.R
import com.example.riot.data.MatchData
import com.google.android.material.snackbar.Snackbar

const val EXTRA_MATCH = "MATCH"

class MatchDetailActivity : AppCompatActivity() {
    private var repo: MatchData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        /*
         * If an intent was used to launch this activity and it contains information about a
         * Match, use that information to populate the UI.
         */
        if (intent != null && intent.hasExtra(EXTRA_MATCH)) {
            repo = intent.getSerializableExtra(EXTRA_MATCH) as MatchData

            findViewById<TextView>(R.id.tv_match_map).text = repo!!.metadata.map
        }
    }
}