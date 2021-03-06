package com.example.mockk_coroutines.data.domain

import com.example.mockk_coroutines.data.repository.GithubRepository
import com.example.mockk_coroutines.data.repository.Repository

class GetRepositoriesUseCase(private val apiRepository: GithubRepository) {
    suspend fun execute(username: String): List<Repository> {
        return this.apiRepository.fetchRepositories(username).map {
            Repository(
                it.id,
                it.name
            )
        }
    }
}