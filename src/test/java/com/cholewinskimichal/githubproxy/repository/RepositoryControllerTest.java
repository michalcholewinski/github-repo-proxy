package com.cholewinskimichal.githubproxy.repository;

import com.cholewinskimichal.githubproxy.JsonMatcher;
import com.cholewinskimichal.githubproxy.client.GithubClientMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void startMock() {
        GithubClientMock.getMock().start();
    }

    @After
    public void stopMock() {
        GithubClientMock.getMock().stop();
    }

    @Test
    public void shouldReturnCorrectData() throws Exception {
        String username = "michalcholewinski";
        String repositoryName = "css-training";
        GithubClientMock.getMock()
            .expectGithubRepositorySuccess(username, repositoryName, "src/test/resources/mock/githubRepositoryResponse.json");

        given().port(port).when()
            .get("api/repositories/{username}/{repoName}", username, repositoryName)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(JsonMatcher.matchesJsonFile("src/test/resources/responses/repositoryResponse.json"));
    }


    @Test
    public void shouldReturnNotFoundResponseCode() throws Exception {
        String username = "michalcholewinski";
        String nonexistingRepositoryName = "nonexisting";
        GithubClientMock.getMock()
            .expectGithubRepositoryNotFound(username, nonexistingRepositoryName);

        given().port(port).when()
            .get("api/repositories/{username}/{repoName}", username, nonexistingRepositoryName)
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnBadRequestResponseCode() throws Exception {
        String username = "michalcholewinski";
        String repositoryNameBadRequest = "any_bad_request";
        GithubClientMock.getMock()
            .expectGithubRepositoryBadRequest(username, repositoryNameBadRequest);

        given().port(port).when()
            .get("api/repositories/{username}/{repoName}", username, repositoryNameBadRequest)
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
