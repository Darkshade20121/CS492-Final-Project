package com.example.riot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R
import com.example.riot.data.MatchData
import kotlin.reflect.KFunction1

class StatsAdapter(private val player: KFunction1<MatchData, Unit>) : RecyclerView.Adapter<StatsAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_player_detail, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        when (position) {
//            0 -> holder.bind("Player Rank", player.rank.toString())
//            1 -> holder.bind("Average KDA", player.kda.toString())
//            2 -> holder.bind("Average Headshots", player.headshots.toString())
//            3 -> holder.bind("Average Bodyshots", player.bodyshots.toString())
//            4 -> holder.bind("Average Legshots", player.legshots.toString())
            else -> holder.bind("", "")
        }
    }

    override fun getItemCount() = 5 // number of data fields in the layout

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val labelTextView: TextView = itemView.findViewById(R.id.player_kda)
        private val valueTextView: TextView = itemView.findViewById(R.id.player_bodyshot)

        fun bind(label: String, value: String) {
            labelTextView.text = "label"
            valueTextView.text = "value"
        }
    }
}
