# GitHub Repository API Project

This project interacts with the GitHub API to fetch and process repository information for a given user.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Technologies](#technologies)

## Features

- Fetches a list of repositories owned by a user from the GitHub API.
- Filters out forked repositories.
- Retrieves branches for each repository.

## Getting Started

To run this project locally, follow these steps:

1. Clone the repository:

```bash
git clone https://github.com/yourusername/github-repo-api.git
cd github-repo-api
```
2. Build and run the Spring Boot application:
```bash
./mvnw spring-boot:run
```

The application should start, and you can access it at http://localhost:8080.

## Usage

Example request:

```http
GET http://localhost:8080/api/v1/{username}
```

## Technologies
- Java 17
- Spring Boot 3
- GitHub API v2022-11-28
