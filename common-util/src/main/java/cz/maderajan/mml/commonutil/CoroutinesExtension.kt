package cz.maderajan.mml.commonutil

//fun <T> Flow<T>.handleErrors(onError: (error: Throwable) -> Unit): Flow<T> =
//    catch { e ->
//        Timber.e(e)
//        onError(e)
//    }

//fun <T> Flow<T>.doOnError(onError: (Throwable) -> Unit): Flow<T> {
//    return flow {
//        try {
//            collect { value ->
//                emit(value)
//            }
//        } catch (e: Exception) {
//            onError(e)
//            throw e
//        }
//    }
//}

