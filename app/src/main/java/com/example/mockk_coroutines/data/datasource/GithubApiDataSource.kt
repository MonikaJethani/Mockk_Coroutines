package com.example.mockk_coroutines.data.datasource

import com.example.mockk_coroutines.data.service.GithubApiService
import com.example.mockk_coroutines.data.repository.GithubRepository

class GithubApiDataSource(private val githubApiService: GithubApiService) :
    GithubRepository {

    override suspend fun fetchRepositories(username: String) =
        this.githubApiService.getUserRepositories(username).execute().body() ?: throw NullPointerException("No body")

}