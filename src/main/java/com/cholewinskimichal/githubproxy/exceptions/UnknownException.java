package com.cholewinskimichal.githubproxy.exceptions;

public class UnknownException extends AbstractGithubProxyException {
    public UnknownException(String message) {
        super(message, ExceptionCodes.UNKNOWN_ERROR, null);
    }
}
