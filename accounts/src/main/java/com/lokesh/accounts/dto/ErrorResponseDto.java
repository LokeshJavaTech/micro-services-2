package com.lokesh.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class ErrorResponseDto {
    private  String apiPath;
    private HttpStatus errorCode;
    private String errorMsg;
    private LocalDateTime errorTime;
}
