package syr.project.medical_assistant_implement.user

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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_change_username.*
import kotlinx.android.synthetic.main.navi_header.view.*
import syr.project.medical_assistant_implement.R

class ChangeUsernameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_change_username, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = FirebaseAuth.getInstance().uid
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val headerView = activity!!.navView.getHeaderView(0)
        val profileUserName = headerView.profileUserName
        val profileRef = FirebaseDatabase.getInstance()
            .reference.child("users").child(firebaseUser!!.uid)
        profileRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot != null){
                    currentUsername.text = dataSnapshot.child("username").value.toString()
                    profileUserName.text = dataSnapshot.child("username").value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        )
        submit_new_username.setOnClickListener{
            profileRef.child("username").setValue(username_new.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        Log.i(username_new.text.toString(), "username_new")
                        activity!!.supportFragmentManager.beginTransaction()
                            .replace(R.id.meContainer, ChangeSuccessFragment())
                            .commit()
                    }
                    else{

                    }
                }
        }






    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangeUsernameFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}