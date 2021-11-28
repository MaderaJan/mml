package cz.maderajan.common.ui.appstart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cz.maderajan.common.ui.R
import cz.maderajan.common.ui.UiEffect
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppStartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel: AppStartViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->
                    when (effect) {
                        is UiEffect.NavFlowUiEffect -> {
                            viewModel.navigator.send(effect.navFlow)
                        }
                    }
                }
        }

        viewModel.send(AppStartAction.Start)
    }
}