package cz.blackchameleon.notes.framework

suspend fun <T> withErrorHandling(block: () -> T): Result<T> = try {
    Result.Success(block())
} catch (e: Throwable) {
    Result.Error(ErrorHandlerImpl().getError(e))
}