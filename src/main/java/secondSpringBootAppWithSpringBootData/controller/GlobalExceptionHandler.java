package secondSpringBootAppWithSpringBootData.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import secondSpringBootAppWithSpringBootData.exception.AlreadyExistException;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> handlerAlreadyExistException(AlreadyExistException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(NullPointerException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException exception){

        StringBuilder responseMessage = new StringBuilder();

        exception.getConstraintViolations()
                .forEach(constraintViolation -> {
                    String message = constraintViolation.getMessage();
                    responseMessage.append(message);
                    responseMessage.append("\n");
                });


        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.BAD_REQUEST);
    }
}
