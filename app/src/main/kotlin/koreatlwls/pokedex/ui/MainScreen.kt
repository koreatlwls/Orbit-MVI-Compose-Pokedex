package koreatlwls.pokedex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import koreatlwls.pokedex.R
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import pokedex.ui.theme.PdsColor
import pokedex.ui.theme.PdsTheme

@Composable
fun MainScreen() {
    val state = rememberCollapsingToolbarScaffoldState()
    var query by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop
        )

        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val textSize = (18 + (30 - 18) * state.toolbarState.progress).sp

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .pin()
                )

                Text(
                    text = "Pokedex",
                    modifier = Modifier
                        .road(Alignment.BottomStart, Alignment.BottomStart)
                        .padding(12.dp),
                    color = PdsColor.PDS_WHITE.getColor(),
                    fontSize = textSize,
                    fontWeight = FontWeight.Bold
                )
            }
        ) {
            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    value = query,
                    onValueChange = { query = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    },
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = PdsColor.PDS_LIGHT_WHITE.getColor(),
                        unfocusedContainerColor = PdsColor.PDS_LIGHT_WHITE.getColor(),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth(),
                    columns = GridCells.Fixed(2)
                ) {
                    items(50) {
                        PokemonCard()
                    }
                }
            }

        }
    }

}

@Composable
fun PokemonCard() {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "Charizard",
                color = PdsColor.PDS_WHITE.getColor(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    PdsTheme {
        MainScreen()
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PdsTheme {
        PokemonCard()
    }
}