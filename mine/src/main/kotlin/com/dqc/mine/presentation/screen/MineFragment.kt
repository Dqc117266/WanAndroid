package com.dqc.mine.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import com.dqc.base.presentation.activity.BaseFragment
import com.dqc.base.presentation.compose.composable.DataNotFoundAnim

class MineFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TestView()
            }
        }
    }
}

@Preview
@Composable
private fun TestView() {
    DataNotFoundAnim()
}
