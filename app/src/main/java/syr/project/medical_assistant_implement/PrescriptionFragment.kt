package syr.project.medical_assistant_implement

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

/*var queryPre = FirebaseDatabase.getInstance()
    .reference
    .child("prescriptions")
    .limitToLast(50)*/

class PrescriptionFragment() : Fragment(),
    PrescriptionListAdapter.MyItemClickListener{
    var idx: Int = 0
    private var listener: OnRecyclerInteractionListener? = null
    lateinit var myAdapter: PrescriptionListAdapter
    val uid = FirebaseAuth.getInstance().uid
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val queryPre = FirebaseDatabase.getInstance().reference.child("users").child(firebaseUser!!.uid).child("prescription")
    //override fun onItemClickedFromAdapter(position: Int) {
    //    idx = position
    //}
    interface OnRecyclerInteractionListener {
        fun onItemClicked(prescription: PrescriptionData, posterid: Int?)
    }
    fun onItemClickedFromRecyclerViewFragment(prescription: PrescriptionData,posterid: Int?) {
        listener?.onItemClicked(prescription,posterid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        setHasOptionsMenu(true)
    }

    override fun onItemClickedFromAdapter(prescription: PrescriptionData, prescriptionid: Int?) {
        onItemClickedFromRecyclerViewFragment(prescription, prescriptionid)
    }

    fun onItemClickedFromRecyclerViewFragment(prescription: PrescriptionData, prescriptionid: Int?) {
        listener?.onItemClicked(prescription, prescriptionid!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prescription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter= PrescriptionListAdapter(PrescriptionData::class.java,queryPre)
        reportRcyclerView.layoutManager= GridLayoutManager(context,1)
//        val myAdapter= MyMovieListAdapter(movieList ,posterTable)
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

        reportRcyclerView.itemAnimator = SlideInLeftAnimator(OvershootInterpolator()).apply {
            addDuration = 1000
            removeDuration = 100
            moveDuration = 1000
            changeDuration = 100
        }

//        myAdapter.sortItemsByTitle()

    }
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnRecyclerInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnRecyclerInteractionListener")
//        }
////        toolBarTitle!!.text="Movie List"
//    }

    override fun onStart() {
        super.onStart()
        myAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        myAdapter.stopListening()
    }

    companion object {

       @JvmStatic
       fun newInstance(param1: String, param2: String) =
           PrescriptionFragment().apply {
               arguments = Bundle().apply {

               }
           }
    }
}




