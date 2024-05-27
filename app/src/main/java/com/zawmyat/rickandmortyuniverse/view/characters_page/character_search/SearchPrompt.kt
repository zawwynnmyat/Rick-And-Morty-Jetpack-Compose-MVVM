package com.zawmyat.rickandmortyuniverse.view.characters_page.character_search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.zawmyat.rickandmortyuniverse.R

@Composable
fun SearchPrompt() {

    val focusRequester = remember {
        FocusRequester()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.search_character),
    )

    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever
    )

    val screenWidth = LocalConfiguration.current.screenWidthDp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(focusRequester)
            .clickable {
                focusRequester.freeFocus()
                keyboardController?.hide()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LottieAnimation(
            composition = lottieComposition,
            modifier = Modifier.height((screenWidth / 1.5).dp),
            progress = {
                progress
            }
        )


        Text(
            text = "Type and Search Characters",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}