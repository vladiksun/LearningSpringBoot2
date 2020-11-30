package com.vb.springboot.webservice.exception;

import com.vb.springboot.webservice.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = createErrorMessage(ex);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserServiceException.class, NullPointerException.class})
    public ResponseEntity<ErrorMessage> handleUserServiceException(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = createErrorMessage(ex);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorMessage createErrorMessage(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimeStamp(new Date());

        String message = ex.getLocalizedMessage();

        if (message == null) {
            message = ex.toString();
        }

        errorMessage.setMessage(message);
        errorMessage.setDeveloperMessage("Internal Server Error. Please contact your administrator.");
        return errorMessage;
    }
}
