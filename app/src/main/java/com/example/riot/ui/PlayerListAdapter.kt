package com.example.riot.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R

class PlayerListAdapter(private val players: List<String>, private val kills: List<Int>, private val deaths: List<Int>, private val assists: List<Int>, private val teamColor: List<String>, private val blueScore: Int, private val redScore: Int) :
    RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.tv_player_name)
        val playerKills: TextView = itemView.findViewById(R.id.tv_player_kills)
        val playerDeaths: TextView = itemView.findViewById(R.id.tv_player_deaths)
        val playerAssists: TextView = itemView.findViewById(R.id.tv_player_assists)

        val playerTeam: LinearLayout = itemView.findViewById(R.id.tv_player_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_list_item, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.playerName.text = players[position]
        holder.playerKills.text = "K: ${kills[position]}"
        holder.playerDeaths.text = " D: ${deaths[position]}"
        holder.playerAssists.text = " A: ${assists[position]}"

        if(teamColor[position] == "Red" || teamColor[position] == "red"){
            holder.playerTeam.background = ColorDrawable(Color.RED)
        }else if(teamColor[position] == "Blue" || teamColor[position] == "blue"){
            holder.playerTeam.background = ColorDrawable(Color.BLUE)
        }
        holder.playerTeam.background.alpha = 120
    }

    override fun getItemCount() = players.size
}
