package com.zawmyat.rickandmortyuniverse.view.characters_page.character_search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zawmyat.rickandmortyuniverse.R
import com.zawmyat.rickandmortyuniverse.viewmodel.SearchCharacterViewModel

@Composable
fun SearchBarCharacters(
    query: String,
    onQueryChange: (String) -> (Unit),
    onFinishClicked: () -> Unit,
    viewModel: SearchCharacterViewModel,
) {

    val focusRequester = remember {
        FocusRequester()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        keyboardController?.show()
        focusRequester.requestFocus()
    }

    androidx.compose.material.TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = "Search Characters",
                color = Color.White.copy(alpha = 0.8f)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .focusRequester(focusRequester = focusRequester)
            .onFocusChanged {
                if (it.hasFocus) {
                    keyboardController?.show()
                }
            },
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = onFinishClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_backarrow),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        trailingIcon = {
            if(query.isNotEmpty()) {
                IconButton(onClick = {
                    viewModel.queryFlow.value = ""
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White,
            disabledLabelColor = Color.White,

            unfocusedIndicatorColor = Color.White.copy(alpha = 0.3f),
            focusedIndicatorColor = Color.White.copy(alpha = 0.3f),

            cursorColor = Color.White,
            leadingIconColor = Color.White,
            textColor = Color.White,

            placeholderColor = Color.White,
            disabledPlaceholderColor = Color.White,

            backgroundColor = Color.Transparent,
        )
    )


}



