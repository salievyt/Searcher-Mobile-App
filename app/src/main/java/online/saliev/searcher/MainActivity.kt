package online.saliev.searcher

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import online.saliev.searcher.databinding.ActivityMainBinding
import online.saliev.searcher.viewmodel.Roles
import online.saliev.searcher.viewmodel.Links


class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val userPermission = Roles.Tester;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (userPermission == Roles.Dev){
            installSplashScreen()
        }

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (userPermission == Roles.Dev) {
            Toast.makeText(this, "Режим разработчика : Включен", Toast.LENGTH_SHORT).show()
        }
    }
}