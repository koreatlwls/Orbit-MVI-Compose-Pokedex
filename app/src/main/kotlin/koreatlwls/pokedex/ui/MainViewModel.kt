package koreatlwls.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import koreatlwls.pokedex.core.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    pokemonRepository: PokemonRepository,
) : ViewModel() {

    val pokemon = pokemonRepository.getPokemons().cachedIn(viewModelScope)

}