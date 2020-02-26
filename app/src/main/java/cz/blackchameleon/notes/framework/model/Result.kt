package cz.blackchameleon.notes.framework.model

import cz.blackchameleon.notes.framework.ErrorHandlerImpl

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: ErrorEntity) : Result<T>()
}

suspend fun <T> withErrorHandling(block: suspend () -> T): Result<T> = try {
    Result.Success(block())
} catch (e: Throwable) {
    Result.Error(ErrorHandlerImpl().getError(e))
}