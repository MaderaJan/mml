package cz.maderajan.ui.spotifysync.intro

import cz.maderajan.common.ui.viewmodel.IAction

sealed class IntroSpotifyAction : IAction

class PersistSpotifyLoginToken(val token: String?) : IntroSpotifyAction()