package com.cholewinskimichal.githubproxy.client;

import com.cholewinskimichal.githubproxy.client.model.GithubRepository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubClient {

    @GET("repos/{username}/{repoName}")
    Call<GithubRepository> fetchUsersRepository(@Path("username") String username, @Path("repoName") String repositoryName);
}
