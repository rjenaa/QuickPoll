package com.QuickPollDemo.QuickPollDemo.handler;


import com.QuickPollDemo.QuickPollDemo.dto.error.ErrorDetail;
import com.QuickPollDemo.QuickPollDemo.dto.error.ValidationError;
import com.QuickPollDemo.QuickPollDemo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found.");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDevelopersMessage(rnfe.getClass().getName());

        return new ResponseEntity<>(errorDetail, null , HttpStatus.NOT_FOUND);
    }

    @Autowired
    private MessageSource messageSource;

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public @ResponseBody ErrorDetail handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if(requestPath == null){
//            requestPath = request.getRequestURI();
//        }
//        errorDetail.setTitle("Validation Failed");
//        errorDetail.setDetail("Input Validation Failed");
//        errorDetail.setDevelopersMessage(manve.getClass().getName());
//
//        List<FieldError> fieldErrorList = manve.getBindingResult().getFieldErrors();
//        for (FieldError fe: fieldErrorList){
//            List<ValidationError> validationErrors = errorDetail.getError().get(fe.getField());
//            if(validationErrors == null){
//                validationErrors = new ArrayList<ValidationError>();
//                errorDetail.getError().put(fe.getField(), validationErrors);
//
//            }
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fe.getCode());
//            validationError.setMessage(messageSource.getMessage(fe, null));
//            validationErrors.add(validationError);
//        }
//
//        return errorDetail;
//    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDevelopersMessage(ex.getClass().getName());

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Validation Failed");
        errorDetail.setDetail("Input Validation Failed");
        errorDetail.setDevelopersMessage(manve.getClass().getName());

        List<FieldError> fieldErrorList = manve.getBindingResult().getFieldErrors();
        for (FieldError fe: fieldErrorList){
            List<ValidationError> validationErrors = errorDetail.getError().get(fe.getField());
            if(validationErrors == null){
                validationErrors = new ArrayList<ValidationError>();
                errorDetail.getError().put(fe.getField(), validationErrors);

            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrors.add(validationError);
        }

        return handleExceptionInternal(manve, errorDetail, headers, status, request);
    }

}
