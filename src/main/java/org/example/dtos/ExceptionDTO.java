package org.example.dtos;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(String message, HttpStatus httpStatus) {
}
