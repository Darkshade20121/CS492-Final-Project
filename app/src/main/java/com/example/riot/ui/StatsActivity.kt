package com.example.riot.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.riot.R
import com.example.riot.data.MatchData
import android.view.View
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.TextView

import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

import com.example.riot.data.Player



class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        // Find the back button view by its ID
        val backButton = findViewById<View>(R.id.matchButton)

        // Set an OnClickListener to the button to finish the activity when it's clicked
        backButton.setOnClickListener {
            finish()
        }

        val name = intent.getStringExtra("EXTRA_NAME")
        Log.d("MAIN NAME", name.toString())

        val matches = intent.getSerializableExtra(EXTRA_MATCH) as? List<MatchData>
        val filteredPlayers = mutableListOf<Player>()

        matches?.forEach { match ->
            match.players.all_players.forEach { player ->
                if (name != null) {
                    if (player.name.lowercase() == name.lowercase()) {
                        filteredPlayers.add(player)
                    }
                }
            }
        }

        val numPlayers = filteredPlayers.size
        var totalKills = 0.0F
        var totalAssists = 0.0F
        var totalDeaths = 0.0F
        var totalHeadshots = 0.0F
        var totalBodyshots = 0.0F
        var totalLegshots = 0.0F
        var playerRank = ""
        var imageUrl = ""
        var currenttier = 0

        filteredPlayers.forEach { player ->
            totalKills += player.stats.kills
            totalAssists += player.stats.assists
            totalDeaths += player.stats.deaths
            totalHeadshots += player.stats.headshots
            totalBodyshots += player.stats.bodyshots
            totalLegshots += player.stats.legshots

            // Set player to max rank out of those 5 games
            val newTier = player.currenttier
            if (newTier > currenttier && player.currenttier_patched != "Unrated") {
                currenttier = newTier
                playerRank = player.currenttier_patched
            }

            // Used to set image URL
            imageUrl = player.assets.card.small
        }

        val kdaAverage = ((totalKills + totalAssists) / totalDeaths).toDouble()
        val headshotsAverage = (totalHeadshots / numPlayers).toDouble()
        val bodyshotsAverage = (totalBodyshots / numPlayers).toDouble()
        val legshotsAverage = (totalLegshots / numPlayers).toDouble()

        val userName = findViewById<TextView>(R.id.riot_id)
        val cardView = findViewById<RelativeLayout>(R.id.player_layout)
        val captureButton = findViewById<ImageView>(R.id.saveButton)
        val idSV = findViewById<SearchView>(R.id.idSV)
        val riotLogoRelink = findViewById<ImageView>(R.id.riotLogoRelink)

        cardView.setBackgroundColor(Color.parseColor("#FF2121"))

        userName.text = name

        captureButton.setOnClickListener {
            backButton.visibility = View.INVISIBLE
            captureButton.visibility = View.INVISIBLE
            idSV.visibility = View.INVISIBLE
            riotLogoRelink.visibility = View.INVISIBLE

            val bitmap = getScreenShotFromView(cardView)
            if (bitmap != null) {
                saveMediaToStorage(bitmap)
            }

            backButton.visibility = View.VISIBLE
            captureButton.visibility = View.VISIBLE
            idSV.visibility = View.VISIBLE
            riotLogoRelink.visibility = View.VISIBLE
        }

        val searchView = findViewById<SearchView>(R.id.idSV)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(this@StatsActivity, MainActivity::class.java)
                intent.putExtra("QUERY_STRING", query)
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        Log.d("FILTERED PLAYERS", filteredPlayers.toString())



        val relativeLayout: RelativeLayout = findViewById(R.id.player_layout)

        val playerImage: ImageView = relativeLayout.findViewById(R.id.pfp)
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(playerImage)

        val kdaTextView: TextView = relativeLayout.findViewById(R.id.player_kda)
        val headshotsTextView: TextView = relativeLayout.findViewById(R.id.player_headshot)
        val bodyshotsTextView: TextView = relativeLayout.findViewById(R.id.player_bodyshot)
        val legshotsTextView: TextView = relativeLayout.findViewById(R.id.player_legshot)
        val rankTextView: TextView = relativeLayout.findViewById(R.id.player_rank)

        kdaTextView.text = String.format("%.2f", kdaAverage)
        headshotsTextView.text = String.format("%.2f", headshotsAverage)
        bodyshotsTextView.text = String.format("%.2f", bodyshotsAverage)
        legshotsTextView.text = String.format("%.2f", legshotsAverage)
        rankTextView.text = playerRank

    }

    private fun getScreenShotFromView(v: View): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot because:" + e.message)
        }
        return screenshot
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this , "Captured View and saved to Gallery" , Toast.LENGTH_SHORT).show()
        }
    }
}
