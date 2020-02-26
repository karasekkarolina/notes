package cz.blackchameleon.notes.framework

import cz.blackchameleon.notes.framework.model.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}