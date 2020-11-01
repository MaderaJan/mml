package cz.maderajan.ui.spotifysync

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import cz.maderajan.common.ui.activity.viewBinding
import cz.maderajan.ui.spotifysync.databinding.ActivitySpotifySyncBinding

class SpotifySyncActivity : AppCompatActivity(R.layout.activity_spotify_sync) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.navHostFragment).navigateUp()
}