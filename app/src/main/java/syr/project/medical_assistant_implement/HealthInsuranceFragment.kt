package syr.project.medical_assistant_implement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_health_insurance.*
import kotlinx.android.synthetic.main.fragment_specialty.*
import syr.project.medical_assistant_implement.InsuranceListAdapter


class HealthInsuranceFragment : Fragment(),InsuranceListAdapter.MyItemClickListener {
    lateinit var myAdapter:InsuranceListAdapter
    private var listener: OnRecyclerInteractionListener? = null
    interface OnRecyclerInteractionListener {
        fun onInsuranceClicked()
    }

    override fun onItemClickedFromAdapter(){
        listener?.onInsuranceClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter= InsuranceListAdapter(view.context)
        insuranceRecyclerView.layoutManager= GridLayoutManager(context,1)
        insuranceRecyclerView.adapter=myAdapter
        myAdapter.setMyItemClickListener(this)



    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HealthInsuranceFragment.OnRecyclerInteractionListener) {
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_health_insurance, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HealthInsuranceFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}