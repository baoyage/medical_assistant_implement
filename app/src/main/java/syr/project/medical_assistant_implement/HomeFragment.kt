package syr.project.medical_assistant_implement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), View.OnClickListener {

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        activity?.title = "Home"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeAnAppointment.setOnClickListener(this)
        healthInsurance.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.makeAnAppointment->{
                val intent = Intent(activity, MakeAnAppointmentActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }
            R.id.healthInsurance->{
                val intent = Intent(activity, HealthInsuranceActivity::class.java)
                intent.putExtra("action", 0)
                startActivity(intent)
            }

        }
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}