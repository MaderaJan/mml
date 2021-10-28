package cz.maderajan.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.maderajan.navigation.NavigationFlow
import cz.maderajan.navigation.ToFlowNavigatable

class StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // decide where to go on the first app launch, check auth tokens if login needed etc...
        (0..1).random().let {
            when (it) {
                0 -> (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.SpotifySync)
                1 -> (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.SpotifySync)
            }
        }
    }
}