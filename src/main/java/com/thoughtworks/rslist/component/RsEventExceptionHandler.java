package com.thoughtworks.rslist.component;

import com.thoughtworks.rslist.domain.RsEvent;
import com.thoughtworks.rslist.domain.User;
import com.thoughtworks.rslist.exception.Error;
import com.thoughtworks.rslist.exception.RsEventNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RsEventExceptionHandler {

    @ExceptionHandler({RsEventNotValidException.class, MethodArgumentNotValidException.class,
            IndexOutOfBoundsException.class, IllegalArgumentException.class})
    public ResponseEntity RsEventExceptionHandler(Exception e) {
        String errorMessage = "";
        if (e instanceof MethodArgumentNotValidException) {
             MethodArgumentNotValidException methodArgumentNotValidException= ((MethodArgumentNotValidException) e);
            if (methodArgumentNotValidException.getBindingResult().getTarget() instanceof RsEvent) {
                errorMessage = "invalid param";
            }
            if (methodArgumentNotValidException.getBindingResult().getTarget() instanceof User) {
                errorMessage = "invalid user";
            }

        } else if (e instanceof IndexOutOfBoundsException || e instanceof IllegalArgumentException) {
            errorMessage = "invalid request param";
        } else {
            errorMessage = e.getMessage();
        }

        Error error = new Error();
        error.setError(errorMessage);

        return ResponseEntity.badRequest().body(error);
    }
}
