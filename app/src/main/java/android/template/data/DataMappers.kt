package android.template.data

import android.template.data.remote.PokemonResponse
import android.template.data.remote.PokemonService
import android.template.domain.Pokemon


fun PokemonResponse.toDomain() = results.mapIndexed { i, p ->
    Pokemon(
        i+1,
        p.name,
        PokemonService.buildUrlImgById(i+1)
    )
}

