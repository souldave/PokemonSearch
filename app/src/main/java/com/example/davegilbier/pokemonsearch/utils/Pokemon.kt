package com.example.davegilbier.pokemonsearch.utils

import com.google.gson.annotations.SerializedName

/**
 * Created by Dave Gilbier on 14/03/2018.
 */


data class Pokemon(val name: String,
                   val sprites: Sprite,
                   val weight: Int,
                   val height: Int,
                   val ability: String,
                   val stat: String
)

data class Sprite(@SerializedName("front_default") val frontDefault: String)





