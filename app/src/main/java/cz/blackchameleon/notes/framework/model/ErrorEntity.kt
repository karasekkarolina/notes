package cz.blackchameleon.notes.framework.model

/**
 * Representation of errors which can appear in the app
 * Encapsulates original exception together with additional info about it
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
sealed class ErrorEntity {
    abstract val originalException: Throwable
    data class Network(override val originalException: Throwable) : ErrorEntity()
    data class NotFound(override val originalException: Throwable) : ErrorEntity()
    data class AccessDenied(override val originalException: Throwable) : ErrorEntity()
    data class ServiceUnavailable(override val originalException: Throwable) : ErrorEntity()
    data class Unknown(override val originalException: Throwable) : ErrorEntity()
}