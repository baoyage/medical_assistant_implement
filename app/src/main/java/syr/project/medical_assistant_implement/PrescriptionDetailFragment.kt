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

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_prescription_detail.*
import kotlinx.android.synthetic.main.navi_header.view.*


private const val ARG_MOV2 = "prescriptionid"

class PrescriptionDetailFragment : Fragment() {


    var prescriptionid:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        //setHasOptionsMenu(true)
        arguments?.let {

            prescriptionid = it.getInt(ARG_MOV2)
//            Log.i(prescriptionid.toString()+prescriptionid.toString()+prescriptionid.toString(), "prescriptionidprescriptionidprescriptionid")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prescription_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uid = FirebaseAuth.getInstance().uid
        if(uid != null){
            val firebaseUser = FirebaseAuth.getInstance().currentUser

            val profileRef = FirebaseDatabase.getInstance().reference
                .child("users")
                .child(firebaseUser!!.uid)



            profileRef.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot != null){
                        prescriptionUsername.text=dataSnapshot.child("username").value.toString()
                        var prescriptionDetail=dataSnapshot
                            .child("prescriptions")
                            .child(prescriptionid.toString())

                        prescriptionDate.text = prescriptionDetail.child("prescriptionDate").value.toString()
                        prescriptionDoctor.text=prescriptionDetail.child("doctor").value.toString()
                        prescriptionSpecialty.text=prescriptionDetail.child("specialty").value.toString()

                        Picasso.get().load(prescriptionDetail.child("prescriptionUrl").value.toString()).fit().into(prescriptionImage)

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
        fun newInstance( position: Int) =
            PrescriptionDetailFragment().apply {
                arguments = Bundle().apply {

                    putInt(ARG_MOV2, position)
                }
            }
    }
}