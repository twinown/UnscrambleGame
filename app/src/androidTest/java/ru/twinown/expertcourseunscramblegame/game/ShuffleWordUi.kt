package ru.twinown.expertcourseunscramblegame.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.twinown.expertcourseunscramblegame.R

/**
 * Properties:
 * visibility
 */

//думаешь над тем. какими свойствами обладает твоя вьюха
//видишь, тут одно свойство, потому ты сразу назвал ассертвизибл
class ShuffleWordUi(
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {

    //постоянный элемент пишется в интеракторе вот так

    //это твоя вьюха в обёртке для эспрессо!!!
    // чтоб постоянно не писать это всё, выводим в отльеную переменную
    private val interaction: ViewInteraction = onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(R.id.shuffledWordTextView),
            withText(text),
            isAssignableFrom(TextView::class.java)
        )
    )

    //то есть мы проверяем на этой фунцкии, видна ли твоя вюьха
    fun assertTextVisible() {
        interaction.check(matches(isCompletelyDisplayed()))
    }
}
