package com.example.mockk_coroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mockk_coroutines.R
import com.example.mockk_coroutines.data.datasource.GithubApiDataSource
import com.example.mockk_coroutines.data.service.GithubApiService
import com.example.mockk_coroutines.data.repository.Repository
import com.example.mockk_coroutines.data.domain.GetRepositoriesUseCase
import com.example.mockk_coroutines.utils.LiveDataResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {
    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    private val githubService: GithubApiService by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit.create(GithubApiService::class.java)
    }

    private val mainViewModelFactory: MainViewModelFactory = MainViewModelFactory(Dispatchers.Main, Dispatchers.IO, GetRepositoriesUseCase(GithubApiDataSource(this.githubService)))

    private val mainViewModel: MainViewModel by lazy { ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java) }

    private val dataObserver = Observer<LiveDataResult<List<Repository>>> {
        // User data
        when (it?.status) {
            LiveDataResult.STATUS.ERROR -> {

            }
            LiveDataResult.STATUS.SUCCESS -> {

            }
            LiveDataResult.STATUS.LOADING -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mainViewModel.repositoriesLiveData.observe(this, this.dataObserver)

        process.setOnClickListener {
            this.mainViewModel.fetchRepositories("MonikaJethani")
        }

    }
}