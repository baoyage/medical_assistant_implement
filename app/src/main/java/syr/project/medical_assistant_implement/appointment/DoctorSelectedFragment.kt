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

    //override fun onItemClickedFromAdapter(){
    //    listener?.onDoctorClicked()
    //}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        //dateButton.setOnClickListener(ButtonListener())
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
//        if(dateChosen!=null){
//            dateChosen.text=inputText
//        }

        return rootView

//        return inflater.inflate(R.layout.fragment_doctor_selected, container, false)

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        myAdapter= DoctorListAdapter(view.context)
//        DoctorRcyclerView.layoutManager= GridLayoutManager(context,1)
//        DoctorRcyclerView.adapter=myAdapter
//        myAdapter.setMyItemClickListener(this)
//    }

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
            val doctorid=0 //temporary doctorid
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
//            var inputText = arguments?.getString("input_txt")
//            Log.i(inputText, "onViewCreated: onViewCreated:")
//            dateChosen.text=inputText
//            Log.i(MakeAnAppointmentActivity().ddd, "onViewCreated: onViewCreated:")

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

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            DoctorSelectedFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

//    fun showDatePickerDialog(context: Context) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            val calendar: Calendar = Calendar.getInstance()
//            val dialog = DatePickerDialog(context)
//            dialog.setOnDateSetListener { view, year, month, dayOfMonth ->
//                calendar.set(year, month, dayOfMonth)
//                val format = SimpleDateFormat("yyyy-MM-dd")
//                textView.setText(format.format(calendar.getTime()))
//                //              textView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
//            }
//            val datePicker = dialog.datePicker
//            datePicker.minDate = calendar.getTimeInMillis()
//            dialog.show()
//        }
//    }
}