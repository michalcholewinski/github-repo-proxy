package com.cholewinskimichal.githubproxy.repository;

import com.cholewinskimichal.githubproxy.client.GithubService;
import com.cholewinskimichal.githubproxy.exceptions.AbstractGithubProxyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RepositoryService {

    private final GithubService githubService;
    private final RepositoryMapper mapper;


    public Repository fetchRepository(String username, String repositoryName) throws AbstractGithubProxyException {
        return mapper.toRepository(githubService.fetchRepository(username, repositoryName));
    }


}
