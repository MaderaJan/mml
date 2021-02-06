package cz.maderajan.mml.commonutil

class UnauthorizedException(message: String = "User token was nul") : IllegalStateException(message)