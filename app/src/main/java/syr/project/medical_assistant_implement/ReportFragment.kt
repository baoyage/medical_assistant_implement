package syr.project.medical_assistant_implement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import kotlinx.android.synthetic.main.fragment_report.*

class ReportFragment() : Fragment(),
    ReportListAdapter.MyItemClickListener{
    private var listener: OnRecyclerInteractionListener? = null
    lateinit var myAdapter: ReportListAdapter
    val uid = FirebaseAuth.getInstance().uid
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val reportQuery = FirebaseDatabase.getInstance().reference
        .child("users")
        .child(firebaseUser!!.uid)
        .child("reports")

    override fun onItemClickedFromAdapter(position: Int) {
        listener?.onItemClicked(position)
    }

    interface OnRecyclerInteractionListener {
        fun onItemClicked(position: Int)
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
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter= ReportListAdapter(ReportData::class.java,reportQuery)
        reportRcyclerView.layoutManager= GridLayoutManager(context,1)
        myAdapter.setMyItemClickListener(this)
        reportRcyclerView.adapter=myAdapter
        val alphaAdapter = AlphaInAnimationAdapter(myAdapter)
        reportRcyclerView.adapter = ScaleInAnimationAdapter(alphaAdapter).apply {
            // Change the durations.
            setDuration(1000)
            // Change the interpolator.
            setInterpolator(OvershootInterpolator())
            // Disable the first scroll mode.
            setFirstOnly(false)
        }

        reportRcyclerView.itemAnimator = SlideInRightAnimator(OvershootInterpolator()).apply {
            addDuration = 1000
            removeDuration = 100
            moveDuration = 1000
            changeDuration = 100
        }



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ReportFragment.OnRecyclerInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRecyclerInteractionListener")
        }
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
