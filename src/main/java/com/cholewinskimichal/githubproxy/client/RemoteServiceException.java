package com.cholewinskimichal.githubproxy.client;

import com.cholewinskimichal.githubproxy.exceptions.AbstractGithubProxyException;
import com.cholewinskimichal.githubproxy.exceptions.ExceptionCodes;

public class RemoteServiceException extends AbstractGithubProxyException {
    public RemoteServiceException(String message, Exception e) {
        super(message, ExceptionCodes.EXTERNAL_SERVICE_ERROR, e);
    }
}
