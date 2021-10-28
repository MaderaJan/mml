package cz.maderajan.common.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import cz.maderajan.navigation.NavigationFlow
import cz.maderajan.navigation.Navigator
import cz.maderajan.navigation.ToFlowNavigatable

class MainActivity : AppCompatActivity(R.layout.activity_main), ToFlowNavigatable {

    private val navigator: Navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

        navigator.navController = navController
        // navView.setupWithNavController(navController)
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}