package com.dqc.home.presentation.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dqc.base.domain.result.Result
import com.dqc.base.presentation.nav.NavManager
import com.dqc.base.presentation.viewmodel.BaseAction
import com.dqc.base.presentation.viewmodel.BaseState
import com.dqc.base.presentation.viewmodel.BaseViewModel
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState
import com.dqc.home.presentation.screen.home.HomeViewModel.Action
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Error
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Content
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Loading
import com.dqc.home.domain.model.Articles
import com.dqc.home.domain.usecase.GetArticlesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val navManager: NavManager,
    private val getArticlesUseCase: GetArticlesUseCase
) : BaseViewModel<UiState, Action>(Loading) {


    private var job: Job? = null

    fun getArticles(page: Int) {

        if(job != null) {
            job?.cancel()
            job = null
        }

        job = viewModelScope.launch {
            getArticlesUseCase(page).also { result ->
                val action = when(result) {
                    is Result.Success -> {
                        if (result.value.datas.isEmpty()) {
                            Action.HomeLoadFailure
                        } else {
                            Action.HomeLoadSuccess(result.value)
                        }
                    }
                    is  Result.Failure -> {
                        Action.HomeLoadFailure
                    }
                }
                sendAction(action)
            }
        }

    }

    internal sealed interface UiState : BaseState {
        data class Content(val articles: Articles) : UiState
        object Loading : UiState
        object Error : UiState
    }

    internal sealed interface Action : BaseAction<UiState> {
        class HomeLoadSuccess(private val articles: Articles) : Action {
            override fun reduce(state: UiState): UiState = Content(articles)
        }

        object HomeLoadFailure : Action {
            override fun reduce(state: UiState) = Error
        }

    }

}