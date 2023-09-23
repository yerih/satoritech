package com.satoritech.pokedex

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun Any.log(msg: String, tag: String = "TGB") = Log.i(tag, msg)

//fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun ViewModel.launch(action: suspend ()->Unit) = viewModelScope.launch { action() }

fun Any.toast(context: Context, msg: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context, msg, length).show()
}


