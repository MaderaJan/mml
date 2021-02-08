package cz.maderajan.mml.data.data

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val throwable: Throwable) : Result<Nothing>()
}