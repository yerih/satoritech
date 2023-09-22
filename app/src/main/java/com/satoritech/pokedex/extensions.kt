package com.satoritech.pokedex

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun Any.log(msg: String, tag: String = "TGB") = Log.i(tag, msg)

//fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

@Composable
fun toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        LocalContext.current,
        msg,
        length
    ).show()
}

fun ViewModel.launch(action: suspend ()->Unit) = viewModelScope.launch { action() }


