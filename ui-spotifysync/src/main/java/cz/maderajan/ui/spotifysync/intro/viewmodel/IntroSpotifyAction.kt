package cz.maderajan.ui.spotifysync.intro.viewmodel

import cz.maderajan.common.ui.viewmodel.IAction

sealed class IntroSpotifyAction : IAction {
    class PersistSpotifyLoginToken(val token: String?) : IntroSpotifyAction()
    object Skip : IntroSpotifyAction()
}