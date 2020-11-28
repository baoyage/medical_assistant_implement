package syr.project.medical_assistant_implement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_change_success.*

class ChangeSuccessFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetContinue.setOnClickListener{
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.meContainer, HomeFragment())
                .commit()
        }
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChangeSuccessFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}