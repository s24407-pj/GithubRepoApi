package com.example.githubrepoapi.models;

import java.util.Map;

public record Branch(String name, Map<String, Object> commit) {
}
