package ru.twinown.expertcourseunscramblegame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.twinown.expertcourseunscramblegame.R


class GamePage(word: String) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val shuffleWordUi = ShuffleWordUi(
        text = word,  //word не меняется, поэтому он в конструкторе
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val inputUi = InputUi(
        //его свойства будут разбираться уже там внутри
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val skipUi = ButtonUi(
        id = R.id.skipButton,
        textResId = R.string.skip,
        colorHex = "#30F8F5",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        textResId = R.string.next,
        colorHex = "#FF13CC",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    //отдельно , потому что может быть Enabled/Disabled и Visible/Invisible
    //два его свойства можно сразу в методе ассерт сделать
    private val checkUi = CheckButtonUi(
        /*id = R.id.checkButton,
        textResId = R.string.check,
        colorHex = "#549EFF",*/
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    //при состоянии экрана проверяем состояние каждого элемента
    fun assertInitialState() {
        shuffleWordUi.assertTextVisible()
        inputUi.assertInitialSate()
        skipUi.assertVisible()
        checkUi.asserVisibleDisabled()
        nextUi.assertNotVisible()
    }

    //при действиях выбирается нужный элемент и дёргается его действие
    fun addInput(text: String) {
        inputUi.addInput(text = text)
    }

    fun assertInsufficientState() {
        shuffleWordUi.assertTextVisible()
        //видишь, если у вьюхи больше 3-х свойств, то ее свойства описываются там дальше
        inputUi.assertInsufficientState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun assertSufficientState() {
        shuffleWordUi.assertTextVisible()
        inputUi.assertInsufficientState()
        skipUi.assertVisible()
        checkUi.assertVisibleEnabled()
        nextUi.assertNotVisible()

    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertCorrectState() {
        shuffleWordUi.assertTextVisible()
        inputUi.assertCorrectState()
        skipUi.assertNotVisible()
        checkUi.aassertNotVisible()
        nextUi.assertVisible()

    }

    fun clickNext() {
        nextUi.click()
    }


    fun clickSkip() {
        skipUi.click()
    }

    fun assertIncorrectState() {
        shuffleWordUi.assertTextVisible()
        inputUi.assertIncorrectState()
        skipUi.assertVisible()
        checkUi.aassertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun removeInputLastLetter() {
        inputUi.removeInputLastLetter()

    }


}