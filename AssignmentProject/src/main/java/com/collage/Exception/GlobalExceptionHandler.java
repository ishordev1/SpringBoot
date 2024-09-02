package com.collage.Exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.collage.Dto.ApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //handling resource not found exception handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
          logger.info("Exception handler invoke !!");
          ApiResponse responseMessage =  ApiResponse.builder().message(ex.getMessage()).success(true).build();
          return new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
    }


    //MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
              List<ObjectError> allErrors =  ex.getBindingResult().getAllErrors();
              Map<String,Object> response = new HashMap<>();
              allErrors.stream().forEach(ObjectError ->{
                      String message = ObjectError.getDefaultMessage();
                      String feild = ((FieldError)ObjectError).getField();
                      response.put(feild,message);
              });
              return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    //Handle Bad Api Request
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponse> handleBadApiRequest(BadApiRequest ex){
        logger.info("Bad Api Reqiest !!");
        ApiResponse responseMessage =  ApiResponse.builder().message(ex.getMessage()).success(false).build();
        return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
    }
}
