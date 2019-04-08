package br.com.paulosalvatore.pagseguro_android_turmaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.yesButton

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email == "admin@admin.com" && password == "admin") {
                tvNameSurname.text = "Nome Sobrenome"
                longToast("Yay!")
            } else if (email == "" || password == "") {
                alert(R.string.missing_fields) {
                    yesButton {  }
                }.show()
            } else {
                alert(R.string.login_fail) {
                    yesButton {  }
                }.show()
            }
        }
    }
}
