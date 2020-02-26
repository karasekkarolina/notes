package cz.blackchameleon.notes.framework

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}