package syr.project.medical_assistant_implement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_insurance_view_pager.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "insurancename"
private const val ARG_PARAM2 = "posterid"

/**
 * A simple [Fragment] subclass.
 * Use the [InsuranceViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InsuranceViewPagerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var insurancename: String? = null
    private var posterid: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            insurancename = it.getString(ARG_PARAM1)
            posterid = it.getInt(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insurancePoster.setImageResource(posterid)
        insuranceName.text= insurancename
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insurance_view_pager, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InsuranceViewPagerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(insurancename: String, posterid: Int) =
            InsuranceViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, insurancename)
                    putInt(ARG_PARAM2, posterid)
                }
            }
    }
}