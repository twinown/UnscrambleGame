package ru.twinown.expertcourseunscramblegame


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.twinown.expertcourseunscramblegame.game.GamePage


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setUp(){
        gamePage = GamePage(word = "animal".reversed() )

    }

    /**
    * UGTC-01
    */
    @Test
    fun caseNumber1() {

        gamePage.assertInitialState()

        gamePage.addInput("anima")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertCorrectState()

        gamePage.clickNext()

        gamePage = GamePage(word= "auto".reversed())
        gamePage.assertInitialState()

    }

    /**
    * UGTC-02
    */
    @Test
    fun caseNumber2() {

        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word= "auto".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("aut")
        gamePage.assertInsufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word= "anecdote".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("anecdot")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickSkip()

        gamePage = GamePage(word= "alphabet".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("alphabt")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.clickSkip()
        gamePage = GamePage(word= "all".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "al")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
    }
}