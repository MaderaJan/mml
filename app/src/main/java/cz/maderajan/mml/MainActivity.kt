package cz.maderajan.mml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import cz.maderajan.navigation.NavigationFlowBus
import cz.maderajan.navigation.Navigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator: Navigator by inject()
    private val navigationFlowBus: NavigationFlowBus by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        navigator.navController = navController

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val conf = NavigationViewDataSource.data.firstOrNull { it.fragmentId == destination.id }
            bottomNavigation.isVisible = conf!!.showBottomNavigation
        }

        lifecycleScope.launchWhenCreated {
            navigationFlowBus.navigationFlow.consumeAsFlow()
                .collect { flow ->
                    navigator.navigateToFlow(flow)
                }
        }
    }
}