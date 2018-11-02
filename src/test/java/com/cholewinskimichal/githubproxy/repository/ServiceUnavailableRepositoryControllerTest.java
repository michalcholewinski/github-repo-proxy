package com.cholewinskimichal.githubproxy.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceUnavailableRepositoryControllerTest {

    @LocalServerPort
    int port;

    @Test
    public void shouldReturnServiceUnavailable() throws Exception {
        String username = "michalcholewinski";
        String repositoryNameBadRequest = "any_bad_request";

        //server has not been started

        given().port(port).when()
            .get("api/repositories/{username}/{repoName}", username, repositoryNameBadRequest)
            .then()
            .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
    }


}
