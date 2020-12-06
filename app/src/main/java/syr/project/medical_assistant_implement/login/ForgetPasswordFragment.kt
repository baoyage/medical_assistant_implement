package syr.project.medical_assistant_implement.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_forget_password.*
import syr.project.medical_assistant_implement.R


class ForgetPasswordFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forget_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mAuth: FirebaseAuth? = null
        submit_forget_verification.setOnClickListener{
            mAuth = FirebaseAuth.getInstance()

            mAuth!!.sendPasswordResetEmail(email_forget_password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context,"success",Toast.LENGTH_LONG).show()
                        activity!!.supportFragmentManager.beginTransaction()
                            .replace(R.id.login_container, VerificationSentFragment())
                            .commit()
                    } else {
                        Toast.makeText(context,task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForgetPasswordFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}