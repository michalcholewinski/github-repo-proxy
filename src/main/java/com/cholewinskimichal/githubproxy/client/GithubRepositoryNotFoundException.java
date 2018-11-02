package com.cholewinskimichal.githubproxy.client;

import com.cholewinskimichal.githubproxy.exceptions.AbstractGithubProxyException;
import com.cholewinskimichal.githubproxy.exceptions.ExceptionCodes;

public class GithubRepositoryNotFoundException extends AbstractGithubProxyException {
    public GithubRepositoryNotFoundException(String message, Exception e) {
        super(message, ExceptionCodes.GITHUB_REPOSITORY_NOT_FOUND, e);
    }
}
