package com.dqc.home.presentation.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dqc.base.common.res.Dimen
import com.dqc.base.presentation.activity.BaseActivity
import com.dqc.base.presentation.activity.BaseFragment
import com.dqc.base.presentation.compose.composable.DataNotFoundAnim
import com.dqc.base.presentation.compose.composable.ProgressIndicator
import com.dqc.base.presentation.ext.hideKeyboard
import com.dqc.base.presentation.ext.showKeyboard
import com.dqc.home.R
import com.dqc.home.domain.model.Article
import org.koin.androidx.navigation.koinNavGraphViewModel
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Loading
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Error
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Content

class HomeFragment : BaseFragment() {
//    private val model: HomeViewModel by koinNavGraphViewModel(R.id.homeNavGraph)

    //    private va
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        model.getArticles(0)

        return ComposeView(requireContext()).apply {
            setContent {
                TestView()
//                HomeScreen()
            }
        }
    }

    companion object {

        const val DELAY_BEFORE_SUBMITTING_QUERY = 500L

        fun configureAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                appBarLayout?.apply {
                    elevation = 0f
                    isVisible = true
                }

                mainAppToolbar?.layoutTransition = null
                appBarLayout?.layoutTransition = null

                configureDefaultAppBar(baseActivity)
            }
        }

        private fun configureDefaultAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                searchTextInputEditText?.hideKeyboard()
                searchLayout?.updateLayoutParams {
                    width = ViewGroup.LayoutParams.WRAP_CONTENT
                }
                searchTextInputLayout?.apply {
                    isVisible = false
                }
                mainAppToolbar?.apply {
                    post {
                        setTitle(R.string.home)
                        logo = null
                    }
                    menu?.clear()
                    inflateMenu(R.menu.menu_toolbar_main)
                    setOnMenuItemClickListener { _ ->
//                        configureSearchAppBar(baseActivity)
                        true
                    }
                    logo = null
                }
            }
        }

        private fun configureSearchAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                searchLayout?.updateLayoutParams {
                    width = ViewGroup.LayoutParams.MATCH_PARENT
                }

                searchTextInputLayout.apply {
                    this?.isVisible = true
                }

                mainAppToolbar.apply {
                    this?.title = null
                    this?.setNavigationOnClickListener {
                        configureDefaultAppBar(
                            baseActivity,
                        )
                    }
                    this?.menu?.clear()
                    this?.logo = null
                }

                searchTextInputEditText?.let {
                    it.post {
                        it.requestFocus()
                        it.showKeyboard()
                    }
                }
            }
        }


    }

}

@Composable
private fun HomeScreen(viewModel: HomeViewModel) {
    val uiState: UiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> ProgressIndicator()
            is Content -> ArticleListView(it.articles.datas, viewModel)
        }
    }
}

@Composable
private fun ArticleListView(articles: List<Article>, viewModel: HomeViewModel) {
    LazyColumn {
        items(items = articles, key = { it.id }) { article ->
            ElevatedCard(
                modifier = Modifier
                    .padding(Dimen.spaceS)
                    .wrapContentSize()
            ) {

                Text(text = article.title)
            }
        }
    }
}

@Composable
private fun TestView() {
    Text(text = "首页", modifier = Modifier.wrapContentSize())
}

