package syr.project.medical_assistant_implement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_doctor_selected.*


class DoctorSelectedFragment : Fragment() {
//    lateinit var myAdapter:DoctorListAdapter
//    private var listener: DoctorSelectedFragment.OnRecyclerInteractionListener? = null
//    interface OnRecyclerInteractionListener {
//        fun onDoctorClicked()
//    }
//    override fun onItemClickedFromAdapter(){
//        listener?.onDoctorClicked()
//    }


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
        return inflater.inflate(R.layout.fragment_doctor_selected, container, false)
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
        submit.setOnClickListener{
            activity!!.finish()
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
}