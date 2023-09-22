package com.satoritech.pokedex.data

import com.satoritech.pokedex.data.remote.PokemonResponse
import com.satoritech.pokedex.data.remote.PokemonService
import com.satoritech.pokedex.domain.Pokemon


fun PokemonResponse.toDomain() = results.mapIndexed { i, p ->
    Pokemon(
        i+1,
        p.name,
        PokemonService.buildUrlImgById(i+1)
    )
}

