package android.template.ui.notifications

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.template.R
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class PokemonNotificationService(
    private val context: Context
){
    @RequiresApi(Build.VERSION_CODES.M)
    private val notificationManager=context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification(){
        val notification= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationCompat.Builder(context, idNotification)
                .setContentTitle("Pokemon found!")
                .setContentText("Let's check it out!")
                .setSmallIcon(R.drawable.pokeball)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .build()
        } else {
            TODO("VERSION.SDK_INT < N")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager.notify(
                Random.nextInt(),
                notification
            )
        }
    }

    companion object {
        const val idNotification = "pokemon_notification"
        const val name = "PokemonNotification"
    }
}
