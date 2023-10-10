package com.sleepisdead.travelmakerbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@Setter
public class ApiExceptionDto {

    private int state;
    private String message;

}