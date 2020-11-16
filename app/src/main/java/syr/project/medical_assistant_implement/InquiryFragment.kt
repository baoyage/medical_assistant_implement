package syr.project.medical_assistant_implement

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class InquiryFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
//        activity?.title = "Inquiry"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inquiry, container, false)
    }
    override fun onResume() {
        super.onResume()
//        activity?.title = "Inquiry"
    }

    override fun onDetach() {
        super.onDetach()
        activity?.title = null
    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            InquiryFragment().apply {

            }
    }
}