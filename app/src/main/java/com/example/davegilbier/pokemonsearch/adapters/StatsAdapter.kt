package com.example.davegilbier.pokemonsearch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.davegilbier.pokemonsearch.R
import com.example.davegilbier.pokemonsearch.utils.Stats

/**
 * Created by Dave Gilbier on 17/03/2018.
 */


class StatsAdapter(val mPokemon: ArrayList<Stats>) : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.statsnametxt.text = mPokemon[position].stats
        holder.baseStattxt.text = mPokemon[position].base_stat
        holder.baseStattxt.visibility = View.VISIBLE

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.pokemon_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mPokemon.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statsnametxt = itemView.findViewById(R.id.pokeText) as TextView
        val baseStattxt = itemView.findViewById(R.id.baseStat) as TextView
    }
}

