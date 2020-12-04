package syr.project.medical_assistant_implement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_specialty.*

class SpecialtyFragment : Fragment(),SpecialtyListAdapter.MyItemClickListener {
    lateinit var myAdapter:SpecialtyListAdapter
    private var listener: OnRecyclerInteractionListener? = null

    interface OnRecyclerInteractionListener {
        fun onSpecialtyClicked()
    }

    override fun onItemClickedFromAdapter(){
        listener?.onSpecialtyClicked()
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
        return inflater.inflate(R.layout.fragment_specialty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter= SpecialtyListAdapter(view.context)
        prescriptionRcyclerView.layoutManager= GridLayoutManager(context,1)
        prescriptionRcyclerView.adapter=myAdapter
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
        fun newInstance(param1: String, param2: String) =
            SpecialtyFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}