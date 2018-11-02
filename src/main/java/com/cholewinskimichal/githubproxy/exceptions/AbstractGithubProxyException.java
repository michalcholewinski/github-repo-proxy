package com.cholewinskimichal.githubproxy.exceptions;

public abstract class AbstractGithubProxyException extends Exception {
    private final String code;

    public AbstractGithubProxyException(String message, String code, Exception e) {
        super(message, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
