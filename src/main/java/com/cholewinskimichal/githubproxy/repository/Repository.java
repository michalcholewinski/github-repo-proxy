package com.cholewinskimichal.githubproxy.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Repository {
    @ApiModelProperty(notes = "Full Name of the repository")
    private String fullName;
    @ApiModelProperty(notes = "Description of the repository")
    private String description;
    @ApiModelProperty(notes = "URL to clone")
    private String cloneUrl;
    @ApiModelProperty(notes = "Number of starts")
    private int stars;
    @ApiModelProperty(notes = "Repository creation time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime creationDate;
}
