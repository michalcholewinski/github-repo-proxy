package com.cholewinskimichal.githubproxy.repository;

import com.cholewinskimichal.githubproxy.exceptions.AbstractGithubProxyException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/repositories")
public class RepositoryController {

    private final RepositoryService service;

    @ApiOperation(value = "Fetch repository with given name for specified user", response = Repository.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Remote repository responds with Bad Request. Passed parameters can be wrong"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 503, message = "Remote service is unreachable")
    })
    @GetMapping("/{username}/{repositoryName}")
    public Repository getRepository(@PathVariable("username") String username,
                                    @PathVariable("repositoryName") String repositoryName) throws AbstractGithubProxyException {
        return service.fetchRepository(username, repositoryName);
    }
}
