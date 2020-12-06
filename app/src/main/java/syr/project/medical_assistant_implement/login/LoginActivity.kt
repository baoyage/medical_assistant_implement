package syr.project.medical_assistant_implement.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import syr.project.medical_assistant_implement.R

class LoginActivity : AppCompatActivity() , LoginFragment.OnFragmentInteractionListener,
    SignupFragment.OnFragmentInteractionListener
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(myToolbar)
        val appBar = supportActionBar
        appBar!!.title = "Login"
        supportFragmentManager.beginTransaction().add(R.id.login_container, LoginFragment()).commit()
    }
    override fun onSignUpRoutine(email: String, passwd: String) {
        supportFragmentManager.beginTransaction().replace(
            R.id.login_container,
            SignupFragment.newInstance(email, passwd)
        ).commit()
    }
    override fun onSignInRoutine() {
        supportFragmentManager.beginTransaction().replace(
            R.id.login_container,
            LoginFragment()
        ).commit()
    }
}