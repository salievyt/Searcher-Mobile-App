package online.saliev.searcher

import android.app.Application
import android.widget.Toast
import online.saliev.searcher.data.local.shared.Prefs

class App: Application(){
    companion object{
        lateinit var prefs: Prefs
            private set
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(this)
    }


}