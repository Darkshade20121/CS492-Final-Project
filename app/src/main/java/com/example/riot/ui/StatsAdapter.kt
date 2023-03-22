package com.example.riot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R
import com.example.riot.data.Player
import kotlin.reflect.KFunction1

class StatsAdapter(private val players: List<Player>) : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_stats, parent, false)
        return StatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int = players.size

    inner class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerName: TextView = itemView.findViewById(R.id.tv_player_name)
        private val playerKills: TextView = itemView.findViewById(R.id.tv_player_kills)
        private val playerDeaths: TextView = itemView.findViewById(R.id.tv_player_deaths)
        private val playerAssists: TextView = itemView.findViewById(R.id.tv_player_assists)

        fun bind(player: Player) {
            playerName.text = player.name
            playerKills.text = "Kills: ${player.stats.kills}"
            playerDeaths.text = "Deaths: ${player.stats.deaths}"
            playerAssists.text = "Assists: ${player.stats.assists}"

            itemView.setOnClickListener { onMatchClick.invoke(player.matchData) }
        }
    }
}
