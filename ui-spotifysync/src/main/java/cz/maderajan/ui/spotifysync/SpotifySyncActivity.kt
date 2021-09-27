package cz.maderajan.ui.spotifysync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import cz.maderajan.common.ui.fragment.currentNavigationFragment
import cz.maderajan.ui.spotifysync.databinding.ActivitySpotifySyncBinding

class SpotifySyncActivity : AppCompatActivity(R.layout.activity_spotify_sync) {

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.navHostFragment).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySpotifySyncBinding.inflate(layoutInflater)
        binding.navHostFragment
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.currentNavigationFragment?.onActivityResult(requestCode, resultCode, data)
    }
}