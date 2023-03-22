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
import android.net.Uri
import android.widget.ImageView
import android.widget.RelativeLayout
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

import com.example.riot.data.Player


class StatsActivity : AppCompatActivity() {

    private lateinit var statsAdapter: StatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

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

        val cardView = findViewById<RelativeLayout>(R.id.player_layout)
        val captureButton = findViewById<ImageView>(R.id.saveButton)
        captureButton.setOnClickListener {
            val bitmap = getScreenShotFromView(cardView)
            if (bitmap != null) {
                saveMediaToStorage(bitmap)
            }
        }
        Log.d("FILTERED PLAYERS", filteredPlayers.toString())



//        val recyclerView: RecyclerView = findViewById(R.id.stats_list)
//        statsAdapter = StatsAdapter(filteredMatches)
//        recyclerView.adapter = statsAdapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
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
