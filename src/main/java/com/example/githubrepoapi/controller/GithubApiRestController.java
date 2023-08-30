package com.example.githubrepoapi.controller;

import com.example.githubrepoapi.exceptions.UnsupportedMediaTypeException;
import com.example.githubrepoapi.models.FilteredRepo;
import com.example.githubrepoapi.service.GithubApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GithubApiRestController {
    private final GithubApiService githubService;

    public GithubApiRestController(GithubApiService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    @ResponseBody
    public ResponseEntity<List<FilteredRepo>> getAllRepoByUsername(
            @PathVariable("username") String username,
            @RequestHeader("Accept") MediaType acceptHeader) {
        if (acceptHeader.equals(MediaType.APPLICATION_XML)) {
            throw new UnsupportedMediaTypeException("XML is not supported");
        }
        return ResponseEntity.ok(githubService.getGithubReposByUser(username));
    }
}
