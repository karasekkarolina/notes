package cz.blackchameleon.notes.framework.model

import cz.blackchameleon.notes.framework.ErrorHandlerImpl

/**
 * Encapsulates result of IO operations
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: ErrorEntity) : Result<T>()
}

/**
 * Defines that processed [Result] should have an error handling
 */
suspend fun <T> withErrorHandling(block: suspend () -> T): Result<T> = try {
    Result.Success(block())
} catch (e: Throwable) {
    Result.Error(ErrorHandlerImpl().getError(e))
}