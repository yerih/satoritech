/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.satoritech.pokedex.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.satoritech.pokedex.data.TaskRepository
import com.satoritech.pokedex.data.repositories.LocationRepository
import com.satoritech.pokedex.domain.FakePokemon
import com.satoritech.pokedex.domain.Pokemon
import com.satoritech.pokedex.launch
import com.satoritech.pokedex.ui.task.HomeViewModel.UiEvent.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val locationRepository: LocationRepository,
) : ViewModel() {

    data class UiState(
        val loading: Boolean = true,
        val showImage: Boolean = false,
        val list: List<Pokemon> = listOf(),
        val pokemon: Pokemon = FakePokemon,
    )

    val state = MutableStateFlow(UiState())

    sealed interface UiEvent {
        data class ToastMessage(val msg: String) : UiEvent
        data object Notification : UiEvent
    }

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    fun getPokemon() = viewModelScope.launch { getPokemonFromRepo() }

    private suspend fun getPokemonFromRepo() = taskRepository.getPokemons().also { resp ->
        when {
            resp.isValid() -> {
                val pokemon = resp.value!![(Random.nextInt(resp.value.size))]
                state.update { it.copy(pokemon = pokemon, loading = false) }
            }
            else -> _event.send(ToastMessage(resp.error.toString()))
        }
    }

    fun getLocation() = launch {
        locationRepository.getLocation(
            { launch { _event.send(ToastMessage("location disabled")) } }
        ) {
            launch {
                getPokemonFromRepo()
                _event.send(ToastMessage("location changed"))
                _event.send(Notification)
            }
        }

    }
}

