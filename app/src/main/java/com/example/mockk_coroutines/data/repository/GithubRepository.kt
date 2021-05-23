package com.example.mockk_coroutines.data.repository

import com.example.mockk_coroutines.data.repository.ApiRepository

interface GithubRepository {
    suspend fun fetchRepositories(username: String) : List<ApiRepository>
}