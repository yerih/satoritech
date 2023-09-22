package com.satoritech.pokedex.data.remote

data class PokemonResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<PokemonElement>
){
    data class PokemonElement(
        val name: String,
        val url: String
    )
}

