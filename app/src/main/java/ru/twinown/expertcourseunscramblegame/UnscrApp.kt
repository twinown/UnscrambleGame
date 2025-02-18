package ru.twinown.expertcourseunscramblegame

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class UnscrApp:Application() {

lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("UnscrambleData",Context.MODE_PRIVATE)
    viewModel = GameViewModel(GameRepository.Base(
      IntCache.Base(sharedPreferences,"indexKey",0),
        StringCache.Base(sharedPreferences,"userInputKey",""),
        ShuffleStrategy.Reverse()
    ))
    }
}