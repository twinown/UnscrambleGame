package ru.twinown.expertcourseunscramblegame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.twinown.expertcourseunscramblegame.R


class GamePage(word: String) {

    //мои объекты сидят в одном и том же контейнере (живут в нем)
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))

    //а это тип моего контейнера
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    //твои вьюхи живут в контейнере конкретного типа с конкретным айдишником

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
        colorHex = "#549EFF",
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

    //при проверке состояния экрана проверяем состояние каждого элемента(с блюпринта,крч, типо , понял,да)
    fun assertInitialState() {
        shuffleWordUi.assertTextVisible()
        inputUi.assertInitialState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
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
        inputUi.assertSufficientState()
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
        checkUi.assertNotVisible()
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
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun removeInputLastLetter() {
        inputUi.removeInputLastLetter()

    }


}