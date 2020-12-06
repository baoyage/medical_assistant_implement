package syr.project.medical_assistant_implement.appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_appoint_success.*
import syr.project.medical_assistant_implement.R


class AppointSuccessFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_appoint_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allSet.setOnClickListener {
            activity!!.finish()
        }


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AppointSuccessFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}