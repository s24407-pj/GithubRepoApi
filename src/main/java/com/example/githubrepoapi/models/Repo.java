package com.example.githubrepoapi.models;

import java.util.Map;

public record Repo(String name, boolean fork, Map<String, Object> owner) {
}
