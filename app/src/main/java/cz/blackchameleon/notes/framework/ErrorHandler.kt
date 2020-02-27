package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.framework.model.ErrorEntity

/**
 * Interface encapsulating original error into [ErrorEntity] object
 *
 * @author Karolina Klepackova <klepackova.karolina@email.cz>
 * @since ver 1.0
 */
interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}