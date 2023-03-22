package com.example.riot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R

class PlayerListAdapter(private val players: List<String>) : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {

    // Change the R.id.tv_match_players to the id of the TextView you want to use to display each player
    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Change
        val playerNameTextView: TextView = itemView.findViewById(R.id.tv_match_map)
    }

    // Change the R.layout.activity_match_detail to the layout file you want to use to display each player item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_match_detail, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.playerNameTextView.text = players[position]
    }

    override fun getItemCount(): Int {
        return players.size
    }
}
