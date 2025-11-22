/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author asus
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

//    // Bắt lỗi 404 (do chúng ta ném ra từ Service)
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
    // Bắt lỗi 400 (nhập chữ vào ID)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = String.format("Tham số '%s' phải là kiểu '%s'.", ex.getName(), ex.getRequiredType().getSimpleName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Bắt lỗi Validation (từ @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Dữ liệu nhập không hợp lệ", HttpStatus.BAD_REQUEST);
    }

//    // Bắt tất cả lỗi 500 khác
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception ex) {
//        ex.printStackTrace(); // Tạm thời in ra console
//        return new ResponseEntity<>("Đã có lỗi xảy ra ở máy chủ.", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
