package syr.project.medical_assistant_implement

import android.os.Bundle
import android.view.*
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

import kotlinx.android.synthetic.main.fragment_prescription.*

var query = FirebaseDatabase.getInstance()
    .reference
    .child("prescriptions")
    .limitToLast(50)

class PrescriptionFragment() : Fragment(),
    PrescriptionListAdapter.MyItemClickListener{
    var idx: Int = 0
    //    private var listener: OnRecyclerInteractionListener? = null
    lateinit var myAdapter: PrescriptionListAdapter
//    var mL=ArrayList(MovieList().movieList)

    //override fun onItemClickedFromAdapter(position: Int) {
     //   idx = position

    //}

    //    interface OnRecyclerInteractionListener {
//        fun onItemClicked(movie: MovieData,posterid: Int?)
//
//    }
//    fun onItemClickedFromRecyclerViewFragment(movie: MovieData,posterid: Int?) {
//        listener?.onItemClicked(movie,posterid)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(syr.project.medical_assistant_implement.R.layout.fragment_prescription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter= PrescriptionListAdapter(PrescriptionData::class.java,query)
        prescriptionRcyclerView.layoutManager= GridLayoutManager(context,1)
//        val myAdapter= MyMovieListAdapter(movieList ,posterTable)
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




//    @SuppressLint("RestrictedApi")
//    override fun onOverflowMenuClickedFromAdapter(view: View?, position: Int) {
//        val popup = PopupMenu(context!!, view!!)
//        val menuInflater = popup.menuInflater
//        menuInflater.inflate(R.menu.menu_popup, popup.menu)
//        popup.setOnMenuItemClickListener {
//            when(it.itemId){
//                R.id.action_dup -> {
//
//                    myAdapter.duplicateMovie(position)
//                    return@setOnMenuItemClickListener true
//                }
//                R.id.action_rem -> {
//                    myAdapter.deleteMovies(position)
//                    return@setOnMenuItemClickListener true
//                }
//                else ->{
//                    return@setOnMenuItemClickListener false
//                }
//            }
//        }
//        // show icon on the popup menu!!
//        val menuHelper = MenuPopupHelper(this.context!!, popup.menu as MenuBuilder, view)
//        menuHelper.setForceShowIcon(true)
//        menuHelper.gravity = Gravity.END
//        menuHelper.show()
//    }


//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            RecyclerViewFragment(movieList, posterTable).apply {
//                arguments = Bundle().apply {
//
//                }
//            }
//    }
}




