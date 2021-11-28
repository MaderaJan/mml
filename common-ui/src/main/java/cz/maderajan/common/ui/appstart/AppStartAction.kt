package cz.maderajan.common.ui.appstart

import cz.maderajan.common.ui.viewmodel.IAction

sealed class AppStartAction : IAction {
    object Start : AppStartAction()
}