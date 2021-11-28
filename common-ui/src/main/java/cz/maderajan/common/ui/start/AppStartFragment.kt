package cz.maderajan.common.ui.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cz.maderajan.common.ui.NavFlowEffect
import cz.maderajan.common.ui.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class AppStartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel: AppStartViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->
                    when (effect) {
                        is NavFlowEffect -> {
                            viewModel.navigator.send(effect.navFlow)
                        }
                    }
                }
        }

        viewModel.send(AppStartAction.Start)
    }
}