package cz.blackchameleon.notes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import cz.blackchameleon.notes.presentation.base.OnBackPressedListener

class MainActivity : AppCompatActivity() {

    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
    }

    override fun onBackPressed() {
        navHost.let { hostFragment ->
            val currentFragment = hostFragment.childFragmentManager.fragments.last()
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }
}