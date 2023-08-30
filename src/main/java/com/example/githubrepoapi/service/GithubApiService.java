package com.example.githubrepoapi.service;

import com.example.githubrepoapi.exceptions.ResourceNotFoundException;
import com.example.githubrepoapi.models.Branch;
import com.example.githubrepoapi.models.FilteredBranch;
import com.example.githubrepoapi.models.FilteredRepo;
import com.example.githubrepoapi.models.Repo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

@Service
public class GithubApiService {
    private final WebClient webClient;

    public GithubApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    private List<FilteredBranch> getBranches(String username, String branchName) {
        return Arrays.stream(
                        requireNonNull(webClient.get()
                                .uri("repos/" + username + "/" + branchName + "/branches")
                                .header("X-GitHub-Api-Version:2022-11-28")
                                .retrieve()
                                .bodyToMono(Branch[].class)
                                .block()))
                .map(branch -> new FilteredBranch(branch.name(), (String) branch.commit().get("sha")))
                .toList();

    }

    public List<FilteredRepo> getGithubReposByUser(String username) {

        List<Repo> repos = Arrays.stream(
                        requireNonNull(webClient.get()
                                .uri("users/" + username + "/repos")
                                .header("X-GitHub-Api-Version:2022-11-28")
                                .retrieve()
                                .onStatus(
                                        Predicate.isEqual(HttpStatus.NOT_FOUND),
                                        clientResponse -> Mono.just(
                                                new ResourceNotFoundException("GitHub user not found: " + username))
                                )
                                .bodyToMono(Repo[].class)
                                .block()))
                .filter(Predicate.not(Repo::fork))
                .toList();


        List<FilteredRepo> filteredRepos = repos.stream()
                .map(repo -> new FilteredRepo(
                        repo.name(),
                        (String) repo.owner().get("login"),
                        getBranches(username, repo.name())
                ))
                .toList();

        return filteredRepos;
    }


}
