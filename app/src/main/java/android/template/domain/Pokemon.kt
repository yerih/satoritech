package android.template.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val urlImg: String,
)

val FakePokemon = Pokemon(0, name = "Fake Bulbasaur", urlImg = "https://pokeapi.co/api/v2/pokemon/fakeurl")
