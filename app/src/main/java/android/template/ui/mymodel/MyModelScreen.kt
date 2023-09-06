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

package android.template.ui.mymodel

import android.template.domain.FakePokemon
import android.template.domain.Pokemon
import android.template.log
import android.template.ui.mymodel.MyModelViewModel.UiEvent.*
import android.template.ui.permission.PermissionRequester
import android.template.ui.theme.MyApplicationTheme
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest
@Composable
fun MyModelScreen(
    viewModel: MyModelViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    MyModelScreen(
        pokemon = state.pokemon,
        onClickShowBtn = viewModel::getPokemon
    )

    LaunchedEffect(key1 = Unit){
        viewModel.event.collectLatest {event ->
            when(event){
                is ToastMessage -> Toast.makeText(context, event.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }


    PermissionRequester(context = context) {
        Toast.makeText(context, "location changed: $it", Toast.LENGTH_SHORT).show()
        viewModel.getPokemon()
    }


}

@Composable
internal fun MyModelScreen(
    pokemon: Pokemon,
    onClickShowBtn: () -> Unit = {},
) {
    Box(
        modifier = Modifier.padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier.wrapContentSize()
        ){
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Card(
                    modifier = Modifier
                ){
                    AsyncImage(
                        model = pokemon.urlImg,
                        contentDescription = "pokemon image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .aspectRatio(1f),
                    )
                }
                Text(pokemon.name, fontSize = 20.sp)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClickShowBtn,
                    shape = RoundedCornerShape(20.dp),
                ){
                    Text(text = "show")
                }
            }
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme { MyModelScreen(pokemon = FakePokemon,) }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme { MyModelScreen(pokemon = FakePokemon) }
}
