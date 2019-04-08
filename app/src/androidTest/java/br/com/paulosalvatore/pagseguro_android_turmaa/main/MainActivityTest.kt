package br.com.paulosalvatore.pagseguro_android_turmaa.main

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.paulosalvatore.pagseguro_android_turmaa.MainActivity
import br.com.paulosalvatore.pagseguro_android_turmaa.R
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    lateinit var activity: MainActivity

    @Before
    fun setUp() {
        activity = rule.activity
    }

    @Test
    fun loadActivity_shouldShowHelloWorld() {
        val viewById = activity.findViewById<TextView>(R.id.textView)
        assertThat(viewById, notNullValue())
        assertThat(viewById.text.toString(), equalTo("Hello World!"))
    }

    @Test
    fun sendButton_shouldWriteTypedText() {
        onView(withHint("Type some text")).perform(typeText("Paulo Salvatore"))
        onView(withText("Send")).perform(click())
        onView(withText("Hello, Paulo Salvatore!")).check(matches(isDisplayed()))
    }
}
