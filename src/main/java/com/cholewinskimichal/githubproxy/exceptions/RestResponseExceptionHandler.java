package com.cholewinskimichal.githubproxy.exceptions;

import com.cholewinskimichal.githubproxy.client.ClientBadRequestException;
import com.cholewinskimichal.githubproxy.client.GithubRepositoryNotFoundException;
import com.cholewinskimichal.githubproxy.client.RemoteServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RemoteServiceException.class, UnknownException.class})
    protected ResponseEntity<Object> handleRemoteServiceIssues(AbstractGithubProxyException ex, WebRequest request) {
        return handleException(ex, request, SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(GithubRepositoryNotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundIssue(AbstractGithubProxyException ex, WebRequest request) {
        return handleException(ex, request, NOT_FOUND);
    }

    @ExceptionHandler(ClientBadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(AbstractGithubProxyException ex, WebRequest request) {
        return handleException(ex, request, BAD_REQUEST);
    }



    private ResponseEntity<Object> handleException(AbstractGithubProxyException ex, WebRequest request, HttpStatus status) {
        return handleExceptionInternal(ex, getResponseBody(ex, status),
                                       new HttpHeaders(), status, request);
    }


    private ErrorResponse getResponseBody(AbstractGithubProxyException ex, HttpStatus httpStatus) {
        return new ErrorResponse(ex.getMessage(), ex.getCode(), httpStatus.value());
    }
}
