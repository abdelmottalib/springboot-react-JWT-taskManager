//package com.example.demo.user.config;
//
//
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ProblemDetail;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.nio.file.AccessDeniedException;
//
//
//@RestControllerAdvice
//public class CustomExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ProblemDetail handleSecurityException(Exception e) {
//        ProblemDetail problemDetail = null;
//        if (e instanceof BadCredentialsException) {
//            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
//        }
//        if(e instanceof AccessDeniedException){
//            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
//        }
//        return problemDetail;
//    }
//}