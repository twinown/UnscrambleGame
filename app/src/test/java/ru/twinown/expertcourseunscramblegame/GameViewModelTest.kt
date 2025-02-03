package ru.twinown.expertcourseunscramblegame

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    @Test
    fun caseNumber1() {                              //чтоб понять тесты говоришь себе:
        var actual: GameUiState = viewModel.init() //когда я вызыву это
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1") // я ожидаю это
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1")
        expected = GameUiState.InsufficientInput(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1f")
        expected = GameUiState.SufficientInput(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.check(text = "1f")
        expected = GameUiState.Correct(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, actual)
    }

    @Test
    fun caseNumber2() {
        //open app
        //state is initial (some word)
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.Initial(shuffledWord = "f1")
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "1")
        expected = GameUiState.InsufficientInput(shuffledWord = "f2")
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.InsufficientInput(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.SufficientInput(shuffledWord = "f3")
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f4")
        assertEquals(expected, actual)


        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.InsufficientInput(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.SufficientInput(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.check(text = "f1")
        expected = GameUiState.Incorrect(shuffledWord = "f4")
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Initial(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.InsufficientInput(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.SufficientInput(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.check(text = "f1")
        expected = GameUiState.Incorrect(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f")
        expected = GameUiState.InsufficientInput(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f1")
        expected = GameUiState.SufficientInput(shuffledWord = "f5")
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "f12")
        expected = GameUiState.InsufficientInput(shuffledWord = "f5")
        assertEquals(expected, actual)

        // у оганнеса этого нет
       /* actual = viewModel.handleUserInput(text = "f7")
        expected = GameUiState.SufficientInput(shuffledWord = "f6")
        assertEquals(expected, actual)

        actual = viewModel.check(text = "f7")
        expected = GameUiState.Incorrect(shuffledWord = "f6")
        assertEquals(expected, actual)*/
    }
}
private class FakeRepository:GameRepository{

    private val originalList:List<String> = listOf("1f","2f","3f","4f","5f")
    private val shuffledList =originalList.map { it.reversed() }

    private var index = 0
    //метод отдачи данных с репы//текущего слова
    override fun shuffledWord(): String {
        return shuffledList[index]
    }


    //ты спрашивал ,где будем делать проверку..делаем всё же в репозитории//НЕТ
    //ТЫ ПРАВИЛЬНО ДУМАЛ,сравнение делается во вьюмодельке
    //от репы отдаём обратно,видишь
    override fun originalWord ():String = originalList[index]


    override fun next() {
        index++
        if(index==originalList.size){
            index = 0
        }
    }
}