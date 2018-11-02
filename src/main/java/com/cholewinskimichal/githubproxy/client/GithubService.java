package com.cholewinskimichal.githubproxy.client;

import com.cholewinskimichal.githubproxy.client.model.GithubRepository;
import com.cholewinskimichal.githubproxy.exceptions.AbstractGithubProxyException;
import com.cholewinskimichal.githubproxy.exceptions.UnknownException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class GithubService {
    private GithubClient client;

    @Value("${github.url}")
    private String serviceUrl;

    public GithubRepository fetchRepository(String username, String repository) throws AbstractGithubProxyException {
        try {
            Response<GithubRepository> response = client.fetchUsersRepository(username, repository).execute();
            switch (HttpStatus.resolve(response.code())) {
                case OK:
                    return response.body();
                case NOT_FOUND:
                    throw new GithubRepositoryNotFoundException("Github Repository not found", null);
                case BAD_REQUEST:
                    throw new ClientBadRequestException("Github Repository not found", null);
                case SERVICE_UNAVAILABLE:
                    throw new RemoteServiceException("Remote Service Unavailable", null);
                default:
                    throw new UnknownException("Unknown issue. Please contact with admin");

            }
        } catch (IOException e) {
            throw new RemoteServiceException("External Service Error", e);
        }
    }

    @PostConstruct
    public void buildGithubClient() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        this.client = new Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build().create(GithubClient.class);
    }
}
