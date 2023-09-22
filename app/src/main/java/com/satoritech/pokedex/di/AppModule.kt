package com.satoritech.pokedex.di

import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.satoritech.pokedex.data.remote.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Singleton
    @Provides
    fun providePokemonService(): PokemonService = PokemonService.buildRetrofitWith().create()

    @Singleton
    @Provides
    fun provideLocationManager(app: Application): LocationManager = (app.applicationContext
        .getSystemService(Context.LOCATION_SERVICE) as LocationManager)

}


