package koreatlwls.pokedex.ui.detail

import android.graphics.Color.parseColor
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import koreatlwls.pokedex.core.model.PokemonInfo
import koreatlwls.pokedex.model.PokemonUi
import koreatlwls.pokedex.util.random
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import pokedex.ui.theme.PdsColor
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    pokemonUi: PokemonUi,
    onBack: () -> Unit,
) {
    BackHandler {
        onBack()
    }

    LaunchedEffect(Unit) {
        detailViewModel.fetchPokemon(pokemonUi.name)
    }

    val state by detailViewModel.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    detailViewModel.collectSideEffect {
        if (it is DetailSideEffect.SnackBar) {
            scope.launch {
                snackBarHostState.showSnackbar("Error가 발생하였습니다.")
                Log.e("DetailScreen", it.message)
            }
        }
    }

    DetailScreen(
        state = state,
        pokemonUi = pokemonUi,
        snackBarHostState = snackBarHostState,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailState,
    pokemonUi: PokemonUi,
    snackBarHostState: SnackbarHostState,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pokedex",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable { onBack() },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                },
                actions = {
                    state.pokemonInfo?.let {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = it.id,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(parseColor("#" + pokemonUi.backgroundColor))
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (state.loading) {
                DetailLoading()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    PokemonTopContent(pokemonUi = pokemonUi)
                    if (state.pokemonInfo != null) {
                        PokemonBottomContent(pokemonInfo = state.pokemonInfo)
                    } else {
                        PokemonBottomEmpty()
                    }
                }
            }
        }
    }
}

@Composable
fun DetailLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun PokemonTopContent(pokemonUi: PokemonUi) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(Color(parseColor("#" + pokemonUi.backgroundColor)))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.height(150.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(URLDecoder.decode(pokemonUi.imageUrl, StandardCharsets.UTF_8.toString()))
                .crossfade(true)
                .build(),
            contentDescription = "pokemon image",
        )
    }
}

@Composable
fun PokemonBottomContent(pokemonInfo: PokemonInfo) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = pokemonInfo.name,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            items(pokemonInfo.types) {
                PokemonTypeContent(name = it.name)
                Spacer(modifier = Modifier.width(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemonInfo.weight,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Weight",
                    fontSize = 18.sp,
                    color = PdsColor.PDS_LIGHT_GRAY.getColor()
                )
            }

            Spacer(modifier = Modifier.width(50.dp))

            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemonInfo.height,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Height",
                    fontSize = 18.sp,
                    color = PdsColor.PDS_LIGHT_GRAY.getColor()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(24.dp)) {
            Text(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = pokemonInfo.exp,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun PokemonTypeContent(name: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.random())
            .padding(
                vertical = 6.dp,
                horizontal = 12.dp
            )
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun PokemonBottomEmpty() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "결과가 없습니다.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
