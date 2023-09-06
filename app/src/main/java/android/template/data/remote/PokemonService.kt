package android.template.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonService {

    companion object{
        private const val urlLastFm = "https://pokeapi.co/api/v2/"
        val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }).build()

        private val gson = GsonBuilder().setLenient().create()

        fun buildRetrofitWith(url: String = urlLastFm, client: OkHttpClient = okHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

    }

//    @GET("https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0")
    @GET("pokemon?limit=100000&offset=0")
    suspend fun getPokemons(): PokemonResponse

}