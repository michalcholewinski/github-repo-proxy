package com.cholewinskimichal.githubproxy.client;

import com.cholewinskimichal.githubproxy.exceptions.AbstractGithubProxyException;
import com.cholewinskimichal.githubproxy.exceptions.ExceptionCodes;

public class ClientBadRequestException extends AbstractGithubProxyException {
    public ClientBadRequestException(String message, Exception e) {
        super(message, ExceptionCodes.BAD_REQUEST_EXCEPTION, e);
    }
}
