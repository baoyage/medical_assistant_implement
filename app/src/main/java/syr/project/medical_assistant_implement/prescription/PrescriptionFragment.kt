package syr.project.medical_assistant_implement.prescription

import android.content.Context
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.fragment_prescription.*
import syr.project.medical_assistant_implement.R

class PrescriptionFragment() : Fragment(),
    PrescriptionListAdapter.MyItemClickListener {
    private var listener: OnRecyclerInteractionListener? = null
    lateinit var myAdapter: PrescriptionListAdapter
    val uid = FirebaseAuth.getInstance().uid
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val prescriptionQuery = FirebaseDatabase.getInstance().reference
        .child("users")
        .child(firebaseUser!!.uid).
        child("prescriptions")

    override fun onItemClickedFromAdapter(position: Int) {
        listener?.onItemClicked(position)
    }

    interface OnRecyclerInteractionListener {
        fun onItemClicked( position: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prescription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter= PrescriptionListAdapter(PrescriptionData::class.java,prescriptionQuery)
        prescriptionRcyclerView.layoutManager= GridLayoutManager(context,1)
        myAdapter.setMyItemClickListener(this)
        prescriptionRcyclerView.adapter=myAdapter
        val alphaAdapter = AlphaInAnimationAdapter(myAdapter)
        prescriptionRcyclerView.adapter = ScaleInAnimationAdapter(alphaAdapter).apply {
            // Change the durations.
            setDuration(1000)
            // Change the interpolator.
            setInterpolator(OvershootInterpolator())
            // Disable the first scroll mode.
            setFirstOnly(false)
        }

        prescriptionRcyclerView.itemAnimator = SlideInLeftAnimator(OvershootInterpolator()).apply {
            addDuration = 1000
            removeDuration = 100
            moveDuration = 1000
            changeDuration = 100
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRecyclerInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRecyclerInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onStart() {
        super.onStart()
        myAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        myAdapter.stopListening()
    }
}




