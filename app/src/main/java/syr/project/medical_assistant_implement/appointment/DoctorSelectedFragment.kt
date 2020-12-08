package syr.project.medical_assistant_implement.appointment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_doctor_selected.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import syr.project.medical_assistant_implement.R

data class AppointmentData(val doctorid: String, val date: String, val time: String){
    constructor():this("", "", "")
}

class DoctorSelectedFragment : Fragment() {
    lateinit var myAdapter: DoctorListAdapter
    private var listener: OnRecyclerInteractionListener? = null
    interface OnRecyclerInteractionListener {
        fun onDoctorClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_doctor_selected, container, false)
        var input1Text = arguments?.getString("input_1_txt")
        var input2Text = arguments?.getString("input_2_txt")
        Log.i(input1Text, "onViewCreated: onViewCreated:")
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var input1Text = arguments?.getString("input_1_txt")
        var input2Text = arguments?.getString("input_2_txt")
        dateChosen.text=input1Text
        timeChosen.text=input2Text

        submit.setOnClickListener{
            val uid = FirebaseAuth.getInstance().uid
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            val profileRef = FirebaseDatabase.getInstance()
                .reference.child("users").child(firebaseUser!!.uid)
            val doctorid=0
            val appointmentData= AppointmentData(
                doctorid.toString(), input1Text.toString(),
                input2Text.toString()
            )
            var key=profileRef.child("appointments").push().key
            profileRef.child("currentAppointment").setValue(key)
            profileRef.child("appointments").child(key!!).setValue(appointmentData)
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.makeAnAppointmentContainer, AppointSuccessFragment())
                .commit()
        }

        dateButton.setOnClickListener{
            // Initialize a new DatePickerFragment
            // Show the date picker dialog
            DatePickerFragment().show(activity!!.supportFragmentManager, "Date Picker")
        }

        timeButton.setOnClickListener{
            TimePickerFragment().show(activity!!.supportFragmentManager, "Time Picker")
        }

        doctorMap.setOnClickListener{
            val intent = Intent(activity, MapActivity::class.java)
            intent.putExtra("action", 0)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DoctorSelectedFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}