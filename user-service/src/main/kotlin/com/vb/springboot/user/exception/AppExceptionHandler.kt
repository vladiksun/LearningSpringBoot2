package com.vb.springboot.user.exception

import com.vb.springboot.user.model.response.ErrorMessage
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class AppExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception, request: WebRequest?): ResponseEntity<ErrorMessage> {
        val errorMessage = createErrorMessage(ex)
        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @ExceptionHandler(UserServiceException::class, NullPointerException::class)
    fun handleUserServiceException(ex: Exception, request: WebRequest?): ResponseEntity<ErrorMessage> {
        val errorMessage = createErrorMessage(ex)
        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {

        val errorMessage = createErrorMessage(ex)
        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.BAD_REQUEST)
    }

    private fun createErrorMessage(ex: Exception): ErrorMessage {
        val errorMessage = ErrorMessage()
        errorMessage.timeStamp = Date()
        var message = ex.localizedMessage
        if (message == null) {
            message = ex.toString()
        }
        errorMessage.message = message
        errorMessage.developerMessage = "Internal Server Error. Please contact your administrator."
        return errorMessage
    }
}