package com.dqc.home.presentation.screen.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dqc.base.presentation.activity.BaseFragment
import com.dqc.base.presentation.compose.composable.DataNotFoundAnim
import com.dqc.base.presentation.compose.composable.ProgressIndicator
import com.dqc.home.R
import com.dqc.home.domain.model.Article
import org.koin.androidx.navigation.koinNavGraphViewModel
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Loading
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Error
import com.dqc.home.presentation.screen.home.HomeViewModel.UiState.Content

class HomeFragment : BaseFragment() {
    private val model: HomeViewModel by koinNavGraphViewModel(R.id.homeNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model.fetchRefreshData()

        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen(model)
            }
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(viewModel: HomeViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.home)) // 设置Toolbar标题
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onBackground
                        )

                    }
                },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        },
        content = { paddingValues ->
            val uiState: UiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

            uiState.let {
                when (it) {
                    Error -> DataNotFoundAnim()
                    Loading -> ProgressIndicator()
                    is Content -> ArticleListView(it.articles, viewModel, paddingValues)
                }
            }
        },
    )
}

@Composable
private fun ArticleListView(articles: List<Article>, viewModel: HomeViewModel, paddingValues: PaddingValues) {
    LazyColumn(
        contentPadding = paddingValues
    ) {
        items(items = articles, key = { it.id }) { article ->
            ListItem(
                modifier = Modifier
                    .clickable(
                        indication = rememberRipple(color = Color.Gray),
                        interactionSource = remember {
                            MutableInteractionSource()
                        }) {}, headlineContent = { Text(text = article.title) })
        }
    }
}

@Composable
private fun TestView() {
    Text(text = "首页", modifier = Modifier.wrapContentSize())
}

