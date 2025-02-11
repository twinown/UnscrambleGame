package ru.twinown.expertcourseunscramblegame

import android.app.Application

class UnscrApp:Application() {

lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
    viewModel = GameViewModel(GameRepository.Base(ShuffleStrategy.Reverse()))
    }
}