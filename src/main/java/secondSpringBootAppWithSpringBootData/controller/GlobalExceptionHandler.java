package secondSpringBootAppWithSpringBootData.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import secondSpringBootAppWithSpringBootData.dto.errorDto.ErrorResponseDto;
import secondSpringBootAppWithSpringBootData.dto.errorDto.FieldErrorDto;
import secondSpringBootAppWithSpringBootData.dto.validationErrorDto.ValidationErrorDto;
import secondSpringBootAppWithSpringBootData.dto.validationErrorDto.ValidationErrorsDto;
import secondSpringBootAppWithSpringBootData.exception.AlreadyExistException;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerNotFoundException(NotFoundException exception){
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handlerAlreadyExistException(AlreadyExistException exception){
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDto> handlerNullPointerException(NullPointerException exception){
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //при валилации данных когда @RequestParam или @PathVariable
    //а также при валидации сущностей, управляемых JPA
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handlerConstraintViolationException(ConstraintViolationException exception){
        List<FieldErrorDto> fieldErrors = new ArrayList<>();

        exception.getConstraintViolations().forEach(violation -> {
            FieldErrorDto fieldError = FieldErrorDto.builder()
                    .field(violation.getPropertyPath().toString())
                    .message(violation.getMessage())
                    .rejectedValue(violation.getInvalidValue())
                    .build();
            fieldErrors.add(fieldError);
        });

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .message("Validation failed")
                .fieldErrors(fieldErrors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //при валидации данных с @RequestBody
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage()));
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleValidationException(MethodArgumentNotValidException e) {

        List<ValidationErrorDto> validationErrors = new ArrayList<>();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        for (ObjectError error : errors) {
            FieldError fieldError = (FieldError)error;
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();

            if (fieldError.getRejectedValue() != null) {
                errorDto.setRejectedValue(fieldError.getRejectedValue().toString());
            }

            validationErrors.add(errorDto);
        }

        return ResponseEntity
                .badRequest()
                .body(ValidationErrorsDto.builder()
                        .errors(validationErrors)
                        .build());
    }
}
