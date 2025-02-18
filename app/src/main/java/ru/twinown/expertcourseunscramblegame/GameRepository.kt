package ru.twinown.expertcourseunscramblegame

import kotlin.streams.toList

//
interface GameRepository {

    fun shuffledWord(): String
    fun originalWord(): String
    fun next()
    fun saveUserInput(value:String)
    fun userInput():String


    class Base(
        private val index:IntCache,
        private val userInput:StringCache,
        private val shuffleStrategy: ShuffleStrategy = ShuffleStrategy.Base(),
        private val originalList: List<String> = listOf(
            "animal", "auto", "anecdote",
            "alphabet", "all"
        )

    ) : GameRepository {

        //с фейк репы берем методы эти

        private val shuffledList = originalList.map {shuffleStrategy.shuffle(it)}


        //метод отдачи данных с репы//текущего слова
        override fun shuffledWord(): String {
            return shuffledList[index.read()]
        }

        override fun originalWord(): String = originalList[index.read()]


        override fun next() {
            if (index.read() + 1 ==originalList.size)
                index.save(0)
            else index.save(index.read())
            userInput.save("")
        }

        override fun saveUserInput(value: String) {
            userInput.save(value)
        }

        override fun userInput(): String {
            return userInput.read()
        }
    }
}
interface ShuffleStrategy{
    fun shuffle (source:String):String
    class Base:ShuffleStrategy{
        override fun shuffle(source: String): String {
            return source
        }
    }

    class Reverse:ShuffleStrategy {
        override fun shuffle(source: String): String {
            return source.reversed()
        }
    }
}