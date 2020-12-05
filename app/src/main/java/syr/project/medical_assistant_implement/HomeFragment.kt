package syr.project.medical_assistant_implement

import android.content.Context
import android.content.Intent
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_change_username.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.navi_header.view.*
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class HomeFragment : Fragment(), View.OnClickListener {

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        activity?.title = "Home"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeAnAppointment.setOnClickListener(this)
        healthInsurance.setOnClickListener(this)
        prescription.setOnClickListener(this)
        medicalReport.setOnClickListener(this)
        medicalWiki.setOnClickListener(this)
        val uid = FirebaseAuth.getInstance().uid
        if(uid != null) {
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            val profileRef = FirebaseDatabase.getInstance()
                .reference.child("users").child(firebaseUser!!.uid)
            profileRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot != null) {
                        homeUsername.text = dataSnapshot.child("username").value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
            )
        }
        updateData()
        val currentTime=DateTimeFormatter
            .ofPattern("yyyyMMdd HH:mm")
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault())
            .format(Instant.now())
        val currentHour=currentTime.substring(9,11).toInt()
        if(currentHour<12){
            timeGreetings.text="Good morning, "
        }
        else if(currentHour<18){
            timeGreetings.text="Good afternoon, "
        }
        else{
            timeGreetings.text="Good evening, "
        }


//        Log.i(currentTime, "DateTimeFormatterDateTimeFormatterDateTimeFormatter")
//        Log.i(currentTime.substring(9,11), "currentTime.substring(9,10)currentTime.substring(9,10)")

    }

    override fun onResume() {
        super.onResume()
        updateData()
    }
    fun updateData(){
        val uid = FirebaseAuth.getInstance().uid
        if(uid != null){
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            val currentRefKey=FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser!!.uid)

            var currentKey=""


                val profileRef = FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser!!.uid)
            Log.i(profileRef.toString(), "updateData: ")

            profileRef.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot != null){
                        currentKey=dataSnapshot.child("currentAppointment").value.toString()
                        var appointdata=dataSnapshot.child("appointments").child(currentKey)

                        if(appointdata.child("doctorid").value.toString()=="null"){
                            reminder.text=""
                        }
                        else{
                            reminder.text = "There is an upcoming appointment with doctor "+appointdata.child("doctorid").value.toString()+
                                    " on "+
                                    appointdata.child("time").value.toString()+
                                    ", "+
                                    appointdata.child("date").value.toString()
                        }


                        if(reminder.text!=""){
                            noReminder.text=""
                        }
                        Log.i(dataSnapshot.key, "dataSnapshot.keydataSnapshot.keydataSnapshot.keydataSnapshot.key")

//                        Log.i(dataSnapshot.child("useremail").value.toString(), "onStart: qwertytyu")
//                        if(profileEmail.text=="null"){
//                            profileEmail.text="Email"
//                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {


                }

            }
            )
        }
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.makeAnAppointment->{
                val intent = Intent(activity, MakeAnAppointmentActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.healthInsurance->{
                val intent = Intent(activity, HealthInsuranceActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.medicalReport->{
                val intent = Intent(activity, ReportActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.prescription -> {
                val intent = Intent(activity, PrescriptionActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.medicalWiki ->{
                val intent = Intent(activity, WikiActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }

        }
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}