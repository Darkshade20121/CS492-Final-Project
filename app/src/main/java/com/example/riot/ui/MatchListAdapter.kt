package com.example.riot.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R
import com.example.riot.data.MatchData

class MatchListAdapter(
    private val onMatchClick: (MatchData) -> Unit
) : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {
    private var matchList = listOf<MatchData>()


    fun updateMatchList(newMatchList: List<MatchData>?) {
        matchList = newMatchList ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = matchList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_list_item, parent, false)
        return MatchViewHolder(itemView, onMatchClick)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    class MatchViewHolder(
        itemView: View,
        private val onClick: (MatchData) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.tv_match_map)
        private var currentMatch: MatchData? = null

        init {
            itemView.setOnClickListener {
                currentMatch?.let(onClick)
            }
        }
        fun bind(matchRepo: MatchData) {
            currentMatch = matchRepo
            nameTV.text = matchRepo.metadata.map
            if(nameTV.text == "Ascent"){
                nameTV.setBackgroundResource(R.drawable.ascent)
            } else if (nameTV.text == "Bind"){
                nameTV.setBackgroundResource(R.drawable.bind)
            } else if (nameTV.text == "Breeze"){
                nameTV.setBackgroundResource(R.drawable.breeze)
            } else if (nameTV.text == "Fracture"){
                nameTV.setBackgroundResource(R.drawable.fracture)
            } else if (nameTV.text == "Haven"){
                nameTV.setBackgroundResource(R.drawable.haven)
            } else if (nameTV.text == "Icebox"){
                nameTV.setBackgroundResource(R.drawable.icebox)
            } else if (nameTV.text == "Lotus"){
                nameTV.setBackgroundResource(R.drawable.lotus)
            } else if (nameTV.text == "Pearl"){
                nameTV.setBackgroundResource(R.drawable.pearl)
            } else if (nameTV.text == "Split"){
                nameTV.setBackgroundResource(R.drawable.split)
            }

            nameTV.background.alpha = 150
        }
    }
}