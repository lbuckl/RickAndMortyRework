package com.molchanov.feature_characters.ui

import com.molchanov.core.domain.viewmodel.AppState
import com.molchanov.domain.characters.*

sealed class CharactersAppState: AppState() {

    //Состояния страницы
    data class Loading(val isLoading: Boolean) : CharactersAppState()
    data class Success(val data: CharacterPage) : CharactersAppState()
    data class Error(val errorMsg: String) : CharactersAppState()

    //Состояния детальной информации
    data class SuccessCharacter(val data: Character) : CharactersAppState()

}