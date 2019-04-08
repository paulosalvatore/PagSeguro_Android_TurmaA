package br.com.paulosalvatore.pagseguro_android_turmaa.robot

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything

open class BaseTestRobot {
    fun fillEditText(resId: Int, text: String) = onView(withId(resId)).perform(
        ViewActions.replaceText(text),
        ViewActions.closeSoftKeyboard()
    )

    fun clickButton(resId: Int) =
        onView((withId(resId))).perform(ViewActions.click())

    fun textView(resId: Int) =
        onView(withId(resId))

    fun matchText(viewInteraction: ViewInteraction, text: String) =
        viewInteraction.check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String) = matchText(textView(resId), text)

    // Para usos futuros, caso necessário
    fun clickListItem(listRes: Int, position: Int) {
        onData(anything())
            .inAdapterView(allOf(withId(listRes)))
            .atPosition(position).perform(ViewActions.click())
    }
}
