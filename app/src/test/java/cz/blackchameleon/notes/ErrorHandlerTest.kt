package cz.blackchameleon.notes

import cz.blackchameleon.notes.framework.ErrorHandlerImpl
import cz.blackchameleon.notes.framework.model.ErrorEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.HttpException

class ErrorHandlerTest {

    @Test
    fun whenHttp404_notFoundEntity() {
        val exception = mock(HttpException::class.java)
        Mockito.`when`(exception.code()).thenReturn(404)

        val expected = ErrorEntity.NotFound(exception)
        val current = ErrorHandlerImpl().getError(exception)

        assertEquals(expected, current)
    }

    @Test
    fun whenHttp403_forbiddenEntity() {
        val exception = mock(HttpException::class.java)
        Mockito.`when`(exception.code()).thenReturn(403)

        val expected = ErrorEntity.AccessDenied(exception)
        val current = ErrorHandlerImpl().getError(exception)

        assertEquals(expected, current)
    }

    @Test
    fun whenHttp503_unavailableEntity() {
        val exception = mock(HttpException::class.java)
        Mockito.`when`(exception.code()).thenReturn(503)

        val expected = ErrorEntity.ServiceUnavailable(exception)
        val current = ErrorHandlerImpl().getError(exception)

        assertEquals(expected, current)
    }
}