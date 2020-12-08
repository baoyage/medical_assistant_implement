package syr.project.medical_assistant_implement.insurance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_insurance_view_pager.*
import syr.project.medical_assistant_implement.R

private const val ARG_PARAM1 = "insurancename"
private const val ARG_PARAM2 = "posterid"
private const val ARG_PARAM3 = "insuranceoverview"

class InsuranceViewPagerFragment : Fragment() {

    private var insurancename: String? = null
    private var posterid: Int = -1
    private var insuranceoverview: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            insurancename = it.getString(ARG_PARAM1)
            posterid = it.getInt(ARG_PARAM2)
            insuranceoverview = it.getString(ARG_PARAM3)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insurancePoster.setImageResource(posterid)
        insuranceName.text= insurancename
        insuranceOverview.text= insuranceoverview
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insurance_view_pager, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(insurancename: String, posterid: Int, insuranceoverview: String) =
            InsuranceViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, insurancename)
                    putInt(ARG_PARAM2, posterid)
                    putString(ARG_PARAM3, insuranceoverview)
                }
            }
    }
}