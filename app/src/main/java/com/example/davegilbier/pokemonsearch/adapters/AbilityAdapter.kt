package com.example.davegilbier.pokemonsearch.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.davegilbier.pokemonsearch.utils.Ability
import com.example.davegilbier.pokemonsearch.R

/**
 * Created by Dave Gilbier on 17/03/2018.
 */
class AbilityAdapter(val mPokemon: ArrayList<Ability>) : RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.abilitynametxt.text = mPokemon[position].abilities
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.pokemon_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mPokemon.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val abilitynametxt = itemView.findViewById(R.id.pokeText) as TextView
    }
}

