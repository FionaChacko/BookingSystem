package com.trust.Booking.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleAccessDeniedException(BusinessException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Status", HttpStatus.FORBIDDEN);
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        body.put("errors", details);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
