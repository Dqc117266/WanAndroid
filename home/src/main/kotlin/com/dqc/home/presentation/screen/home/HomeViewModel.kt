package com.dqc.home.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.dqc.base.domain.result.Result
import com.dqc.base.presentation.nav.NavManager
import com.dqc.base.presentation.viewmodel.BaseAction
import com.dqc.base.presentation.viewmodel.BaseState
import com.dqc.base.presentation.viewmodel.BaseViewModel
import com.dqc.home.domain.model.Article
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState
import com.dqc.home.presentation.screen.home.HomeViewModel.Action
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Error
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Content
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Loading
import com.dqc.home.domain.model.Articles
import com.dqc.home.domain.model.Banner
import com.dqc.home.domain.usecase.GetArticlesUseCase
import com.dqc.home.domain.usecase.GetBannerUseCase
import com.dqc.home.domain.usecase.GetTopArtclesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val navManager: NavManager,
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getBannerUseCase: GetBannerUseCase,
    private val getTopArtclesUseCase: GetTopArtclesUseCase
) : BaseViewModel<UiState, Action>(Loading) {

    private var currentPage: Int = 0
    var isRefreshing by mutableStateOf(false)

    private var job: Job? = null

    fun getArticles(page: Int) {
        if (job != null) {
            job?.cancel()
            job = null
        }

        job = viewModelScope.launch {
            getArticlesUseCase(page).also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.value.datas.isEmpty()) {
                            Action.HomeLoadFailure
                        } else {
                            Action.HomeLoadSuccess(result.value)
                        }
                    }

                    is Result.Failure -> {
                        Action.HomeLoadFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    fun fetchRefreshData() {
        isRefreshing = true

        if (job != null) {
            job?.cancel()
            job = null
        }

        job = viewModelScope.launch {
            try {
                coroutineScope {
                    val bannerDeferred = async { getBannerUseCase() }
                    val topArticlesDeferred = async { getTopArtclesUseCase() }
                    val articlesDeferred = async { getArticlesUseCase(0) }

                    val bannerResult = bannerDeferred.await()
                    val topArticlesResult = topArticlesDeferred.await()
                    val articlesResult = articlesDeferred.await()

                    val action = when {
                        bannerResult is Result.Failure || topArticlesResult is Result.Failure || articlesResult is Result.Failure -> {
                            Action.HomeLoadFailure
                        }

                        articlesResult is Result.Success && topArticlesResult is Result.Success && bannerResult is Result.Success -> {
                            currentPage = 0 // reset to the first page
                            Action.HomeLoadSuccessWithBanner(
                                articlesResult.value,
                                topArticlesResult.value,
                                bannerResult.value
                            )
                        }

                        else -> {
                            Action.HomeLoadFailure
                        }
                    }
                    sendAction(action)
                }
            } catch (e: Exception) {
                sendAction(Action.HomeLoadFailure)
            } finally {
                isRefreshing = false
            }
        }
    }

    fun loadNextPage() {
        if (job != null) {
            job?.cancel()
            job = null
        }

        job = viewModelScope.launch {
            getArticlesUseCase(currentPage).also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.value.datas.isEmpty()) {
                            Action.HomeLoadFailure
                        } else {
                            currentPage++
                            Action.HomeLoadSuccess(result.value)
                        }
                    }

                    is Result.Failure -> {
                        Action.HomeLoadFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    internal sealed interface UiState : BaseState {
        data class Content(val articles: List<Article>, val banner: List<Banner>? = null) : UiState
        object Loading : UiState
        object Error : UiState
    }

    internal sealed interface Action : BaseAction<UiState> {
        class HomeLoadSuccess(private val articles: Articles) : Action {
            override fun reduce(state: UiState): UiState = Content(articles.datas)
        }

        class HomeLoadSuccessWithBanner(
            private val articles: Articles,
            private val topArticles: List<Article>,
            private val banner: List<Banner>
        ) : Action {
            override fun reduce(state: UiState): UiState {
                val datas = topArticles + articles.datas
                return Content(datas, banner)
            }
        }

        object HomeLoadFailure : Action {
            override fun reduce(state: UiState) = Error
        }
    }
}
