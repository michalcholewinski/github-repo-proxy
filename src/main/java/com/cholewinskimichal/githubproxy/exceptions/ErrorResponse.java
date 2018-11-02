package com.cholewinskimichal.githubproxy.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String message;
    private final String errorCode;
    private final int httpCode;
}
