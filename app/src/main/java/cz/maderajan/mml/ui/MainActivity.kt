package cz.maderajan.mml.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import cz.maderajan.common.ui.fragment.currentNavigationFragment
import cz.maderajan.mml.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHost = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        val graph = navHost.navInflater.inflate(R.navigation.navigation_main)
        graph.startDestination = R.id.navigation_spotifysync
        navHost.graph = graph
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.currentNavigationFragment?.onActivityResult(requestCode, resultCode, data)
    }
}