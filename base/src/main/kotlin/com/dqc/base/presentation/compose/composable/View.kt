package com.dqc.base.presentation.compose.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dqc.base.R
import com.dqc.base.common.res.Dimen

@Composable
fun DataNotFoundAnim() {
//    LabeleAnimation(R.string.data_not_found)
}

@Composable
fun UnderConstructionAnim() {

}

@Composable
fun ProgressIndicator() {
    Box {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(Dimen.spaceXXL)
        )
    }
}