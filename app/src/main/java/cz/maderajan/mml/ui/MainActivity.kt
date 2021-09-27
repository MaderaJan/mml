package cz.maderajan.mml.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import cz.maderajan.common.ui.SuccessEffect
import cz.maderajan.common.ui.fragment.currentNavigationFragment
import cz.maderajan.mml.R
import cz.maderajan.mml.ui.viewmodel.MainActivityViewModel
import cz.maderajan.mml.ui.viewmodel.Start
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->
                    if (effect is SuccessEffect && effect.data is Int) {
                        val startDestination = effect.data as? Int ?: return@collect
                        setNavigation(startDestination)
                    }
                }
        }

        viewModel.send(Start)
    }

    private fun setNavigation(startDestination: Int) {
        val navHost = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        val graph = navHost.navInflater.inflate(R.navigation.navigation_main)
        graph.startDestination = startDestination
        navHost.graph = graph
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.currentNavigationFragment?.onActivityResult(requestCode, resultCode, data)
    }
}