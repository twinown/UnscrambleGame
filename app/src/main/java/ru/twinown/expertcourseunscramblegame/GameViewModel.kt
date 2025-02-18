package ru.twinown.expertcourseunscramblegame

class GameViewModel (private val repository: GameRepository){


//
    fun handleUserInput(text: String): GameUiState {
        repository.saveUserInput(text)
        val shuffledWord = repository.shuffledWord()
        val isSufficient  = text.length == repository.originalWord().length
        return if(isSufficient)
            GameUiState.SufficientInput(shuffledWord) else
        GameUiState.InsufficientInput(shuffledWord)
    }


    fun check(text: String): GameUiState {
        val shuffledWord = repository.shuffledWord()
        val originalWord = repository.originalWord()
        val isCorrect = originalWord.equals(text,true)
        return if (isCorrect)
            GameUiState.Correct(shuffledWord)
        else GameUiState.Incorrect(shuffledWord)
    }

    fun next(): GameUiState {
        repository.next()
        return init()
    }

    fun skip(): GameUiState {
        repository.next()
       return init()
    }

    fun init(): GameUiState {
        val shuffledWord = repository.shuffledWord()
        return GameUiState.Initial(shuffledWord,repository.userInput())
    }

}
