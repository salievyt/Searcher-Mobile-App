package online.saliev.searcher.data.local.shared

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {

    private val prefs = context.getSharedPreferences("OnBoard", MODE_PRIVATE)

    fun isShow(): Boolean{
        return prefs.getBoolean("OnBoard", false)
    }
    fun changeShow(show: Boolean){
        prefs.edit().putBoolean("OnBoard", show).apply()
    }

}