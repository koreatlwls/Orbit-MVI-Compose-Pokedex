package koreatlwls.pokedex.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import koreatlwls.pokedex.core.repository.PokemonRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel(), ContainerHost<DetailState, DetailSideEffect> {

    override val container: Container<DetailState, DetailSideEffect> = container(DetailState())

    fun fetchPokemon(name: String) {
        intent {
            pokemonRepository.getPokemonInfo(name)
                .onSuccess { pokemonInfo ->
                    reduce {
                        state.copy(
                            pokemonInfo = pokemonInfo,
                            loading = false
                        )
                    }
                }
                .onFailure { throwable ->
                    postSideEffect(
                        DetailSideEffect.SnackBar(
                            message = throwable.message ?: "something went wrong"
                        )
                    )
                    reduce {
                        state.copy(
                            pokemonInfo = null,
                            loading = false
                        )
                    }
                }
        }
    }

}