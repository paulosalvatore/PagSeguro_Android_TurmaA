package br.com.paulosalvatore.pagseguro_android_turmaa.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.paulosalvatore.pagseguro_android_turmaa.LoginActivity
import br.com.paulosalvatore.pagseguro_android_turmaa.R
import kotlinx.android.synthetic.main.activity_login.view.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val rule = ActivityTestRule(LoginActivity::class.java)

    lateinit var activity: LoginActivity

    lateinit var robot: LoginTestRobot

    @Before
    fun setUp() {
        activity = rule.activity
        robot = LoginTestRobot(activity)
    }

    @Test
    fun loginSuccess() {
        robot
            .setEmail("admin@admin.com")
            .setPassword("admin")
            .clickLogin()
            .matchText(R.id.tvNameSurname, "Nome Sobrenome")
    }

    @Test
    fun loginMissingEmailPassword() {
        robot
            .clickLogin()
            .matchErrorText(R.string.missing_fields)
    }

    @Test
    fun loginMissingPassword() {
        robot
            .setEmail("admin@admin.com")
            .clickLogin()
            .matchErrorText(R.string.missing_fields)
    }

    @Test
    fun loginWrongPassword() {
        robot
            .setEmail("admin@admin.com")
            .setPassword("wrong")
            .clickLogin()
            .matchErrorText(R.string.login_fail)
    }
}
