package koreatlwls.pokedex.ui.result

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import koreatlwls.pokedex.model.PokemonUi
import koreatlwls.pokedex.ui.detail.DetailLoading
import koreatlwls.pokedex.ui.detail.DetailSideEffect
import koreatlwls.pokedex.ui.detail.DetailState
import koreatlwls.pokedex.ui.detail.DetailViewModel
import koreatlwls.pokedex.ui.detail.PokemonBottomContent
import koreatlwls.pokedex.ui.detail.PokemonBottomEmpty
import koreatlwls.pokedex.ui.detail.PokemonTopContent
import koreatlwls.pokedex.util.PaletteGenerator
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ResultScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    name: String,
    onBack: () -> Unit,
) {
    BackHandler {
        onBack()
    }

    LaunchedEffect(Unit) {
        detailViewModel.fetchPokemon(name)
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

    ResultScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    state: DetailState,
    snackBarHostState: SnackbarHostState,
    onBack: () -> Unit,
){
    var imageBackgroundColor by remember { mutableStateOf("#FF000000") }

    val context = LocalContext.current
    LaunchedEffect(state) {
        state.pokemonInfo?.let {
            val bitmap = PaletteGenerator.convertImageUrlToBitmap(
                imageUrl = it.imageUrl,
                context = context
            )

            if (bitmap != null) {
                imageBackgroundColor =
                    PaletteGenerator.extractColorsFromBitmap(bitmap)["lightMuted"] ?: "#FFFFFFFF"
            }
        }
    }

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
                    containerColor = Color(android.graphics.Color.parseColor(imageBackgroundColor))
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
                    if (state.pokemonInfo != null) {
                        PokemonTopContent(
                            pokemonUi = PokemonUi(
                                name = state.pokemonInfo.name,
                                imageUrl = state.pokemonInfo.imageUrl,
                                backgroundColor = imageBackgroundColor.replace("#", "")
                            )
                        )
                        PokemonBottomContent(pokemonInfo = state.pokemonInfo)
                    } else {
                        PokemonBottomEmpty()
                    }
                }
            }
        }
    }
}
