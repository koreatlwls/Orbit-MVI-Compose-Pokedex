package koreatlwls.pokedex.ui.main

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import koreatlwls.pokedex.R
import koreatlwls.pokedex.core.model.Pokemon
import koreatlwls.pokedex.util.PaletteGenerator.convertImageUrlToBitmap
import koreatlwls.pokedex.util.PaletteGenerator.extractColorsFromBitmap
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import pokedex.ui.theme.PdsColor
import pokedex.ui.theme.PdsTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    val pokemons = viewModel.pokemon.collectAsLazyPagingItems()

    MainScreen(
        query = query,
        onValueChange = { query = it },
        pokemons = pokemons,
        onClick = onNavigateToDetail,
    )
}

@Composable
fun MainScreen(
    query: String,
    onValueChange: (String) -> Unit,
    pokemons: LazyPagingItems<Pokemon>,
    onClick: (String) -> Unit
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val gridState = rememberLazyGridState()

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    value = query,
                    onValueChange = onValueChange,
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

                Box(modifier = Modifier.fillMaxSize()) {
                    if (pokemons.loadState.refresh is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    } else {
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxWidth(),
                            columns = GridCells.Fixed(2),
                            state = gridState
                        ) {
                            items(pokemons.itemCount) {
                                pokemons[it]?.let { pokemon ->
                                    PokemonCard(
                                        name = pokemon.name,
                                        imageUrl = pokemon.imageUrl,
                                        onClick = onClick
                                    )
                                }
                            }
                            item {
                                if (pokemons.loadState.append is LoadState.Loading) {
                                    CircularProgressIndicator(modifier = Modifier.padding(top = 12.dp))
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}

@Composable
fun PokemonCard(
    name: String,
    imageUrl: String,
    onClick: (String) -> Unit,
) {
    var cardBackgroundColor by remember { mutableStateOf("#FFFFFFFF") }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val bitmap = convertImageUrlToBitmap(
            imageUrl = imageUrl,
            context = context
        )

        if (bitmap != null) {
            cardBackgroundColor = extractColorsFromBitmap(bitmap)["lightMuted"] ?: "#FFFFFFFF"
        }
    }

    Card(
        modifier = Modifier
            .clickable { onClick(name) }
            .padding(12.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(parseColor(cardBackgroundColor)))
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "pokemon image",
            )

            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = name,
                color = Color.White,
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
        MainScreen(
            onNavigateToDetail = {}
        )
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PdsTheme {
        PokemonCard(
            name = "Rizard",
            imageUrl = "",
            onClick = {},
        )
    }
}