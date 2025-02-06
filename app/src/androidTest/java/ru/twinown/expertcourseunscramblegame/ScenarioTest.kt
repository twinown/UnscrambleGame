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

    //какое активити стартуем
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setUp(){
        //активити и фрагмент заменяется вот такой хернёй с суфф page
        //во фрагмах вьюхи. а в пейдж объектвх юайные объекты (пейдж объект состоит из юайныъ объектов)
        gamePage = GamePage(word = "animal".reversed() )

    }

    /**
    * UGTC-01
    */
    @Test
    fun caseNumber1() {

        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput("anima")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()

        gamePage.clickCheck()
        gamePage.assertCorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertCorrectState()

        gamePage.clickNext()
        gamePage = GamePage(word= "auto".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

    }

    /**
    * UGTC-02
    */
    @Test
    fun caseNumber2() {

        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word= "auto".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput("aut")
        gamePage.assertInsufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word= "anecdote".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput("anecdot")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word= "alphabet".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput("alphabt")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertIncorrectState()

        gamePage.clickSkip()
        gamePage = GamePage(word= "all".reversed())
        gamePage.assertInitialState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

        gamePage.addInput(text = "al")
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "l")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "e")
        gamePage.assertSufficientState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertIncorrectState()
    }
}