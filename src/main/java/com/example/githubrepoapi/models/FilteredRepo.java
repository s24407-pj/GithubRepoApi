package com.example.githubrepoapi.models;

import java.util.List;

public record FilteredRepo(String name, String login, List<FilteredBranch> branches) {
}
