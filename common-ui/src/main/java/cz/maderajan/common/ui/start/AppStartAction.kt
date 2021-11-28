package cz.maderajan.common.ui.start

import cz.maderajan.common.ui.viewmodel.IAction

sealed class AppStartAction : IAction {
    object Start : AppStartAction()
}