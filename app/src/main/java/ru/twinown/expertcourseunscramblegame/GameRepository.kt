package ru.twinown.expertcourseunscramblegame

import kotlin.streams.toList

//
interface GameRepository {
    fun shuffledWord(): String
    fun originalWord(): String
    fun next()


    class Base(
        private val shuffleStrategy: ShuffleStrategy = ShuffleStrategy.Base(),
        private val originalList: List<String> = listOf(
            "animal", "auto", "anecdote",
            "alphabet", "all"
        )

    ) : GameRepository {

        //с фейк репы берем методы эти

        private val shuffledList = originalList.map {shuffleStrategy.shuffle(it)}

        private var index = 0

        //метод отдачи данных с репы//текущего слова
        override fun shuffledWord(): String {
            return shuffledList[index]
        }

        override fun originalWord(): String = originalList[index]


        override fun next() {
            if (index + 1 ==originalList.size)
                index = 0
            else index++
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