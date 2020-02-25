package cz.blackchameleon.notes

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import cz.blackchameleon.notes.presentation.base.OnBackPressedListener

class MainActivity : AppCompatActivity() {

    private lateinit var navHost: NavHostFragment

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