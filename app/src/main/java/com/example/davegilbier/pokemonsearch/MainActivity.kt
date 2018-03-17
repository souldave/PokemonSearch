package com.example.davegilbier.pokemonsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.davegilbier.pokemonsearch.adapters.AbilityAdapter
import com.example.davegilbier.pokemonsearch.adapters.StatsAdapter
import com.example.davegilbier.pokemonsearch.utils.Ability
import com.example.davegilbier.pokemonsearch.utils.Pokemon
import com.example.davegilbier.pokemonsearch.utils.Stats
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private var pokeSearch: String? = null
    private var mpokeSearch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerView2)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        button.setOnClickListener({
            pokeSearch = etext.text.toString()
            mpokeSearch = pokeSearch
            progressBar2.visibility = View.VISIBLE
            textView3.text = "Searching for $mpokeSearch...."


            val url = "https://pokeapi.co/api/v2/pokemon/$mpokeSearch/"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()


            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e("Poke API", "Failed to get pokemon", e)
                    progressBar2.visibility = View.GONE
                    textView3.text = getString(R.string.text_not_found)
                }

                override fun onResponse(call: Call?, response: Response?) {
                    if (response != null && response.isSuccessful) {
                        val json = response.body()?.string()
                        displayPokemon(json)

                    }
                }
            })
        })

    }

    private fun displayPokemon(json: String?) {

        runOnUiThread {
            Log.e("Poke API", "NANA KOS SEARCH LIST")
            val gson = GsonBuilder().create()
            val pokemon = gson.fromJson(json, Pokemon::class.java)
            // val length = JSONObject(json).getJSONArray("abilities").length()

            if (mpokeSearch == pokemon.name) {
                pokeName.text = pokemon.name
                pokeName.text = pokemon.name.toUpperCase()
                Glide.with(this@MainActivity).load(pokemon.sprites.frontDefault).into(imgPokemon)
                weightText.text = pokemon.weight.toString()
                heightText.text = pokemon.height.toString()
                displayAbility(json)
                displayStats(json)
                progressBar2.visibility = View.GONE
                textView3.text = getString(R.string.heres_your_pokemon)
            } else {
                textView3.text = getString(R.string.text_not_found)
            }
        }

    }


    private fun displayAbility(json: String?) {
        val length = JSONObject(json).getJSONArray("abilities").length()
        val abilitylist = ArrayList<Ability>()
        var counter = 0
        for (i in 1..length) {
            var pokeAbility = JSONObject(json).getJSONArray("abilities").getJSONObject(counter).getJSONObject("ability").getString("name")
            abilitylist.add(Ability(pokeAbility))
            Log.e("Poke API", "NANA KO SA POKE ABILITY")
            val adapter = AbilityAdapter(abilitylist)
            recyclerView.adapter = adapter
            counter++
        }
    }

    private fun displayStats(json: String?) {
        val length = JSONObject(json).getJSONArray("stats").length()
        val statslist = ArrayList<Stats>()
        var counter = 0
        for (i in 1..length) {
            var pokeStats = JSONObject(json).getJSONArray("stats").getJSONObject(counter).getJSONObject("stat").getString("name")
            var baseStats = JSONObject(json).getJSONArray("stats").getJSONObject(counter).getString("base_stat").toString()
            statslist.add(Stats(pokeStats, baseStats))
            Log.e("Poke API", "NANA KO SA POKE STATUS")
            val adapter = StatsAdapter(statslist)
            recyclerView2.adapter = adapter
            counter++
        }
    }

}
