package com.example.riot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R

class PlayerListAdapter(private val players: List<String>, private val kills: List<Int>, private val deaths: List<Int>, private val assists: List<Int>, private val teamColor: List<String>) :
    RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.tv_player_name)
        val playerKills: TextView = itemView.findViewById(R.id.tv_player_kills)
        val playerDeaths: TextView = itemView.findViewById(R.id.tv_player_deaths)
        val playerAssists: TextView = itemView.findViewById(R.id.tv_player_assists)
        val playerTeam: ImageView = itemView.findViewById(R.id.iv_player_team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_list_item, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.playerName.text = players[position]
        holder.playerKills.text = "Kills: ${kills[position]}"
        holder.playerDeaths.text = "Deaths: ${deaths[position]}"
        holder.playerAssists.text = "Assists: ${assists[position]}"

        val team = teamColor[position]
        if (team == "Blue") {
            holder.playerTeam.setImageResource(R.drawable.ic_blue_team)
        } else {
            holder.playerTeam.setImageResource(R.drawable.ic_red_team)
        }
    }

    override fun getItemCount() = players.size
}
