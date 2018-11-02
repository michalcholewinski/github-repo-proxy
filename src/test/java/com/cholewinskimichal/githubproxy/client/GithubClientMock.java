package com.cholewinskimichal.githubproxy.client;

import org.apache.commons.io.FileUtils;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class GithubClientMock {
    private static final int MOCK_PORT = 9001;

    private static ClientAndServer server;
    private static GithubClientMock mock;

    private GithubClientMock() {
        server = new ClientAndServer(MOCK_PORT);
    }

    public static final GithubClientMock getMock() {
        if (mock == null) {
            mock = new GithubClientMock();
        }
        return mock;
    }

    public void stop() {
        if (server != null) {
            server.stop();
        }
    }

    public void start() {
        if (server != null && !server.isRunning()) {
            server.startClientAndServer(MOCK_PORT);
        }
    }

    public GithubClientMock expectGithubRepositorySuccess(String username, String repositoryName, String responsePath) throws Exception {
        server.when(request().withMethod(HttpMethod.GET.name())
                        .withPath("/repos/" + username + "/" + repositoryName)
            , Times.unlimited())
            .respond(response()
                         .withBody(body(responsePath))
                         .withStatusCode(HttpStatus.OK.value()));
        return this;
    }

    public GithubClientMock expectGithubRepositoryNotFound(String username, String repositoryName) throws Exception {
        server.when(request().withMethod(HttpMethod.GET.name())
                        .withPath("/repos/" + username + "/" + repositoryName)
            , Times.unlimited())
            .respond(response()
                         .withStatusCode(HttpStatus.NOT_FOUND.value()));
        return this;
    }

    public GithubClientMock expectGithubRepositoryBadRequest(String username, String repositoryName) throws Exception {
        server.when(request().withMethod(HttpMethod.GET.name())
                        .withPath("/repos/" + username + "/" + repositoryName)
            , Times.unlimited())
            .respond(response()
                         .withStatusCode(HttpStatus.BAD_REQUEST.value()));
        return this;
    }

    private String body(String srcFile) throws IOException {
        return FileUtils.readFileToString(new File(srcFile), "utf-8");
    }

}
