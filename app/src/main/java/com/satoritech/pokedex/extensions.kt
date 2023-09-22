package com.satoritech.pokedex

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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
