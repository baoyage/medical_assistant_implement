package syr.project.medical_assistant_implement

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_change_phone_number.*
import kotlinx.android.synthetic.main.fragment_change_username.*

class ChangePhoneNumberFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = FirebaseAuth.getInstance().uid
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val profileRef = FirebaseDatabase.getInstance()
            .reference.child("users").child(firebaseUser!!.uid)
        profileRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot != null) {
                    currentPhoneNumber.text = dataSnapshot.child("phonenumber").value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        )
        submit_new_phone_number.setOnClickListener {
            profileRef.child("phonenumber").setValue(phone_number_new.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.i(phone_number_new.text.toString(), "phone_number_new")
                        activity!!.supportFragmentManager.beginTransaction()
                            .replace(R.id.meContainer, ChangeSuccessFragment())
                            .commit()
                    } else {

                    }
                }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangePhoneNumberFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}