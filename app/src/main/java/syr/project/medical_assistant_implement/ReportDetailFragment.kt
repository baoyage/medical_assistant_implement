package syr.project.medical_assistant_implement

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_prescription_detail.*
import kotlinx.android.synthetic.main.fragment_prescription_detail.prescriptionUsername
import kotlinx.android.synthetic.main.fragment_report_detail.*
import java.io.Serializable

private const val ARG_MOV2 = "reportid"

class ReportDetailFragment : Fragment() {

    private var reportid:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        //setHasOptionsMenu(true)
        arguments?.let {
            reportid = it.getInt(ARG_MOV2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = FirebaseAuth.getInstance().uid
        if(uid != null) {
            val firebaseUser = FirebaseAuth.getInstance().currentUser

            val profileRef = FirebaseDatabase.getInstance().reference
                .child("users")
                .child(firebaseUser!!.uid)
            profileRef.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot != null){
                        reportUsername.text=dataSnapshot.child("username").value.toString()
                        var reportDetail=dataSnapshot
                            .child("prescriptions")
                            .child(reportid.toString())

                        reportDate.text = reportDetail.child("reportDate").value.toString()
                        reportDoctor.text=reportDetail.child("doctor").value.toString()
                        reportSpecialty.text=reportDetail.child("specialty").value.toString()

                        Picasso.get().load(reportDetail.child("reportUrl").value.toString()).fit().into(reportImage)

//                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {


                }

            }
            )


        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int) =
            ReportDetailFragment().apply {
                arguments = Bundle().apply {

                    putInt(ARG_MOV2, position)
                }
            }
    }
}