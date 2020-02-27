package cz.blackchameleon.notes

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import cz.blackchameleon.notes.presentation.base.OnBackPressedListener

/**
 * MainActivity
 * Main activity in single activity architecture.
 *
 * @see AppCompatActivity
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class MainActivity : AppCompatActivity() {

    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        // Gets the main navigation component
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

    override fun onStart() {
        super.onStart()

        // Checks if internet connection is available and notifies user
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).registerNetworkCallback(
            NetworkRequest.Builder().build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) =
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.online_announcement),
                        Toast.LENGTH_LONG
                    ).show()

                override fun onLost(network: Network) =
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.offline_error),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        )
    }
}