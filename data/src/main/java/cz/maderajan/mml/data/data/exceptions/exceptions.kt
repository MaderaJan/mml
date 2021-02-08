package cz.maderajan.mml.data.data.exceptions

import cz.maderajan.mml.data.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed class NetworkBaseException : Throwable() {
    class NetworkException(val status: Int) : NetworkBaseException()
    object UnauthorizedException : NetworkBaseException()
    object TimeoutException : NetworkBaseException()
    object ConnectionException : NetworkBaseException()
    object UnknownException : NetworkBaseException()
    object NullBodyException : NetworkBaseException()
}

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return Result.Error(mapNetworkThrowable(t))
    }

    return if (!response.isSuccessful) {
        Result.Error(NetworkBaseException.UnknownException)
    } else {
        response.body()?.let { body ->
            Result.Success(body)
        } ?: Result.Error(NetworkBaseException.NullBodyException)
    }
}

private fun mapNetworkThrowable(throwable: Throwable): NetworkBaseException = when (throwable) {
    is UnknownHostException -> NetworkBaseException.ConnectionException
    is TimeoutException -> NetworkBaseException.TimeoutException
    is HttpException -> {
        if (throwable.code() == 401) NetworkBaseException.UnauthorizedException else NetworkBaseException.NetworkException(throwable.code())
    }
    else -> NetworkBaseException.UnknownException
}

inline fun <reified T : Any, R : Any> Flow<Result<T>>.mapSuccess(crossinline transform: suspend (value: T) -> R): Flow<R> {
    return this.map {
        when (it) {
            is Result.Success<*> -> {
                transform(it.data as T)
            }
            is Result.Error -> throw it.throwable
        }
    }
}