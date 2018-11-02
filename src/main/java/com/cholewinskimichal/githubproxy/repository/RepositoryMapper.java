package com.cholewinskimichal.githubproxy.repository;

import com.cholewinskimichal.githubproxy.client.model.GithubRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {

    Repository toRepository(GithubRepository githubRepository);
}
