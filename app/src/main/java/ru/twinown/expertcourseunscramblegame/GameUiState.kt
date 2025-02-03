package ru.twinown.expertcourseunscramblegame

import ru.twinown.expertcourseunscramblegame.databinding.ActivityMainBinding
//
interface GameUiState {
    fun update(binding: ActivityMainBinding):Unit = throw  IllegalStateException("")

    data class InsufficientInput(val shuffledWord: String) : GameUiState {}

    data class Initial(val shuffledWord: String) : GameUiState {}

    data class SufficientInput(val shuffledWord: String) :
        GameUiState {}

   data class Correct(val shuffledWord: String) : GameUiState{}

    data class Incorrect(val shuffledWord: String) : GameUiState{}


}
