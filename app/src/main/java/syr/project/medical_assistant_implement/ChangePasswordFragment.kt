package syr.project.medical_assistant_implement

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_change_password.*


class ChangePasswordFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit_new_password.setOnClickListener{
            if(password_new.text.toString()!=password_new_confirm.text.toString()){
//                Toast.makeText(context,"Passwords are not the same",Toast.LENGTH_SHORT).show()

            }
            else{
//                Toast.makeText(context,"Correct",Toast.LENGTH_SHORT).show()
                val user = FirebaseAuth.getInstance().currentUser
                user!!.updatePassword(password_new.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        println("Update Success")

                        activity!!.supportFragmentManager.beginTransaction()
                            .replace(R.id.meContainer, ChangeSuccessFragment())
                            .commit()
                    } else {
//
                        Toast.makeText(context,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                        println("Error Update")
                    }
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangePasswordFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}