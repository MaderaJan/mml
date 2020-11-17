package cz.maderajan.ui.spotifysync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import cz.maderajan.common.ui.fragment.currentNavigationFragment

class SpotifySyncActivity : AppCompatActivity(R.layout.activity_spotify_sync) {

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.navHostFragment).navigateUp()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.currentNavigationFragment?.onActivityResult(requestCode, resultCode, data)
    }
}