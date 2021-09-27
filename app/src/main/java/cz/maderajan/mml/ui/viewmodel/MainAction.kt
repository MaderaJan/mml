package cz.maderajan.mml.ui.viewmodel

import cz.maderajan.common.ui.viewmodel.IAction

sealed class MainAction : IAction

object Start : MainAction()