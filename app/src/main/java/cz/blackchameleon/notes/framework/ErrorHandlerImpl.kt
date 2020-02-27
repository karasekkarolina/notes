package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.framework.model.ErrorEntity
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Implementation of [ErrorHandler] specifying possible caught exceptions
 *
 * @see ErrorHandler
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when(throwable) {
            is IOException -> ErrorEntity.Network(throwable)
            is HttpException -> {
                when(throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound(throwable)
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied(throwable)
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable(throwable)
                    else -> ErrorEntity.Unknown(throwable)
                }
            }
            else -> ErrorEntity.Unknown(throwable)
        }
    }
}