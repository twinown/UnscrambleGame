package ru.twinown.expertcourseunscramblegame.game

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.twinown.expertcourseunscramblegame.R
import ru.twinown.expertcourseunscramblegame.TextInputLayoutErrorEnabledMatcher
import ru.twinown.expertcourseunscramblegame.TextInputLayoutHasErrorText

//отвечает только за работу с полями
class InputUi(containerIdMatcher: Matcher<View>,
              containerClassTypeMatcher: Matcher<View>) {

    //это твой родительский инпут, понял,да ?
    private val inputLayoutId: Int = R.id.inputLayout

    private val layoutInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputLayout::class.java),
            withId(inputLayoutId),
            containerClassTypeMatcher,
            containerIdMatcher
        )
    )

    //TODO
    //его родитель это интеракшн выше
    private val inputInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.inputEditText)
            //с этими строками код на инишале не работает..так и не понял, почему
            //используй layout inspector
            /*withParent(withId(R.id.inputLayout)),
            withParent(isAssignableFrom(TextInputLayout::class.java))*/
       )
    )

    private val textInputLayoutErrorEnabledMatcherFalse = TextInputLayoutErrorEnabledMatcher(false)


    //то есть на это й функции ты спрашиваешь себя , как должен вести себя твой инпут юай на инишал стейте
    //в матчесе ты и пишешь ожидаемый результат
    //далее типо по такой же схеме
    fun assertInitialState() {
        layoutInteraction.check(matches(isEnabled())).check(
            matches(TextInputLayoutErrorEnabledMatcher(false)))
        inputInteraction.check(matches(isDisplayed()))
        inputInteraction.check(matches(withText("")))
    }

    fun addInput(text: String) {
        inputInteraction.perform(typeText(text), closeSoftKeyboard())
    }


    fun assertInsufficientState() {
        layoutInteraction.check(matches(isEnabled())).check(
            matches(textInputLayoutErrorEnabledMatcherFalse)
        )
    }

    fun assertSufficientState() {
        layoutInteraction.check(matches(isEnabled())).check(
            matches(textInputLayoutErrorEnabledMatcherFalse)
        )
    }

    fun assertCorrectState() {
        layoutInteraction.check(matches(isNotEnabled())).check(
            matches(textInputLayoutErrorEnabledMatcherFalse))
    }

    fun assertIncorrectState() {
        layoutInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(true)))
            .check(matches(TextInputLayoutHasErrorText(R.string.incorrect_message)))
    }

    fun removeInputLastLetter() {
        inputInteraction.perform(click(), pressKey(KeyEvent.KEYCODE_DEL), closeSoftKeyboard())
    }


}
