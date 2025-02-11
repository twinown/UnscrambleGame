package ru.twinown.expertcourseunscramblegame


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.twinown.expertcourseunscramblegame.game.GamePage
import java.sql.Blob


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

    private fun ActivityScenarioRule<*>.doWithRecreate(block:()->Unit){
        block.invoke()
        scenario.recreate()
        block.invoke()
    }

    /**
    * UGTC-01
    */
    @Test
    fun caseNumber1() {

        //можно так activityScenarioRule.doWithRecreate(gamePage::assertInitialState)
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput("anima")
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput(text = "l")
        activityScenarioRule.doWithRecreate(gamePage::assertSufficientState)


        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()

        gamePage = GamePage(word= "auto".reversed())
        activityScenarioRule.doWithRecreate(gamePage::assertInsufficientState)


    }

    /**
    * UGTC-02
    */
    @Test
    fun caseNumber2() {

        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word= "auto".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput("aut")
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word= "anecdote".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput("anecdot")
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word= "alphabet".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput("alphabt")
        activityScenarioRule.doWithRecreate {
        gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }

        gamePage.clickSkip()
        gamePage = GamePage(word= "all".reversed())
        activityScenarioRule.doWithRecreate {
            gamePage.assertInitialState()
        }

        gamePage.addInput(text = "al")
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }

        gamePage.removeInputLastLetter()
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "l")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.removeInputLastLetter()
        activityScenarioRule.doWithRecreate {
            gamePage.assertInsufficientState()
        }

        gamePage.addInput(text = "e")
        activityScenarioRule.doWithRecreate {
            gamePage.assertSufficientState()
        }

        gamePage.clickCheck()
        activityScenarioRule.doWithRecreate {
            gamePage.assertIncorrectState()
        }
    }
}