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

package com.satoritech.pokedex.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.satoritech.pokedex.data.TaskRepository
import com.satoritech.pokedex.data.DefaultTaskRepository
import com.satoritech.pokedex.domain.Pokemon
import com.satoritech.pokedex.domain.Response
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsTaskRepository(
        taskRepository: DefaultTaskRepository
    ): TaskRepository

}

class FakeTaskRepository @Inject constructor() : TaskRepository {
    override val tasks: Flow<List<String>> = flowOf(fakeTasks)
    override suspend fun getPokemons(): Response<List<Pokemon>> {
        TODO("Not yet implemented")
    }


    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeTasks = listOf("One", "Two", "Three")
