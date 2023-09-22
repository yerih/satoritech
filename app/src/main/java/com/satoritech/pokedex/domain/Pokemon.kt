package com.satoritech.pokedex.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val urlImg: String,
)

val FakePokemon = Pokemon(0, name = "Loading Pokemon...\nmove or press button", urlImg = "https://pokeapi.co/api/v2/pokemon/fakeurl")
