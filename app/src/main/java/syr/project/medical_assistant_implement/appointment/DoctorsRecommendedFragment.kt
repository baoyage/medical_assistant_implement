package syr.project.medical_assistant_implement.appointment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_doctors_recommended.*
import syr.project.medical_assistant_implement.R


class DoctorsRecommendedFragment : Fragment() , DoctorListAdapter.MyItemClickListener {
    lateinit var myAdapter: DoctorListAdapter
    private var listener: OnRecyclerInteractionListener? = null
    interface OnRecyclerInteractionListener {
        fun onDoctorClicked()
    }
    override fun onItemClickedFromAdapter(){
        listener?.onDoctorClicked()
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
        return inflater.inflate(R.layout.fragment_doctors_recommended, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter= DoctorListAdapter(view.context)
        DoctorRcyclerView.layoutManager= GridLayoutManager(context,1)
        DoctorRcyclerView.adapter=myAdapter
        myAdapter.setMyItemClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRecyclerInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRecyclerInteractionListener")
        }
//        toolBarTitle!!.text="Movie List"
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            DoctorsRecommendedFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}